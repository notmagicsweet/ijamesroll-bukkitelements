package software.ijamesroll.bukkitelements;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

/**
 * О да, именно Loader, ведь этот класс не будет выступать не для чего более :)
 * Не знаю с каких пор дети типо меня пишут такие разработки, но я могу попробовать,
 * и грех лишать себя такой возможности.
 */
public class BukkitElementsLoader extends JavaPlugin {

    @Override
    public void onEnable() {
        BukkitElements elements = new BukkitElements(getLogger());
        try {
            elements.getModuleManager().loadModules();
        } catch (Exception e) {
            getLogger().warning("[ModuleManager] :: Error loading modules.");
        }
    }
}
