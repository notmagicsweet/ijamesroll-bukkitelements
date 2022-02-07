package software.ijamesroll.bukkitelements.module;

import com.google.common.base.Joiner;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import software.ijamesroll.bukkitelements.BukkitElements;
import software.ijamesroll.bukkitelements.module.annotation.ModuleInformation;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.logging.Logger;
/**
 * © JamesRoll [ ijamesroll.software ] - JamesBukkitElements
 * This product is distributed under the MIT license
 * (https://github.com/ijamesroll/ijamesroll-bukkitelements/LICENSE)
 *
 * The author does not bear any responsibility for this development.
 * It is also prohibited to delete this copyright message.
 *
 * Author contacts: https://vk.com/ijamesroll, admin@ijamesroll.software, https://t.me/ijamesroll
 */
public class ModuleManager {
    public static final ModuleManager INSTANCE = new ModuleManager();
    public static final Map<String, ElementsModule> MODULE_MAP = new HashMap<>();

    /**
     * Не проверял, но по идее данная гадость будет работать.
     */
    public void loadModules() throws ClassNotFoundException, IOException, InstantiationException, IllegalAccessException {
        File directory = BukkitElements.getInstance().getModuleFolder();
        Logger logger = BukkitElements.getInstance().getLogger();
        if (directory.exists() && directory.isDirectory()) {
            for (File file : Objects.requireNonNull(directory.listFiles())) {
                JarFile jarFile = new JarFile(file);
                Enumeration<JarEntry> enumeration = jarFile.entries();
                while (enumeration.hasMoreElements()) {
                    JarEntry jarEntry = enumeration.nextElement();

                    if (jarEntry.isDirectory() || !jarEntry.getName().endsWith(".class")) {
                        continue;
                    }

                    String className = jarEntry.getName().substring(0, jarEntry.getName().length() - 6);
                    className = className.replace('/', '.');

                    if (!className.contains(file.getName().replace(".jar", ""))) {
                        continue;
                    }

                    URLClassLoader urlClassLoader = new URLClassLoader(new URL[]
                            {file.toURI().toURL()});

                    Class<?> moduleClass = urlClassLoader.loadClass(className);

                    ElementsModule module = (ElementsModule) moduleClass.newInstance();

                    ModuleInformation moduleInformation = moduleClass.getDeclaredAnnotation(ModuleInformation.class);

                    logger.info(String.format("[ModuleManager] :: Loading %s version v%s by authors %s",
                            moduleInformation.name(),
                            moduleInformation.version(),
                            Joiner.on(", ").join(moduleInformation.authors())));

                    module.handleEnable();

                    logger.info(String.format("[ModuleManager] :: Enabled module %s version v%s by authors %s",
                            moduleInformation.name(),
                            moduleInformation.version(),
                            Joiner.on(", ").join(moduleInformation.authors())));

                    module.setEnabled(true);
                    module.setInformation(moduleInformation);
                    MODULE_MAP.put(moduleInformation.name().toLowerCase(), module);
                }
            }
        }

        logger.info(String.format("[ModuleManager] :: BukkitElements has loaded with %s modules", MODULE_MAP.size()));
    }

    // Мама, я говнокодер)
    public ElementsModule getModule(String name) throws NullPointerException {
        String moduleName = name.toLowerCase(Locale.ROOT);
        if (MODULE_MAP.containsKey(moduleName)) {
            MODULE_MAP.get(moduleName);
        }
        throw new NullPointerException();
    }

    public void reloadModules() throws Exception {
        MODULE_MAP.values().forEach(this::disableModule);
        loadModules();
    }

    public void disableModule(ElementsModule module) {
        BukkitElements.getInstance().getLogger().info(String.format("[ModuleManager] :: Unloading %s version v%s by authors %s",
                module.getInformation().name(), module.getInformation().version(),
                Joiner.on(", ").join(module.getInformation().authors())));
        module.setEnabled(false);
        module.handleDisable();
        module.getListeners().forEach(HandlerList::unregisterAll);
        module.getListeners().clear();
        MODULE_MAP.remove(module.getInformation().name().toLowerCase());
    }

    public void registerListener(Listener listener, ElementsModule module){
        Bukkit.getPluginManager().registerEvents(listener, BukkitElements.getInstance().getPlugin());
        module.getListeners().add(listener);
    }
}
