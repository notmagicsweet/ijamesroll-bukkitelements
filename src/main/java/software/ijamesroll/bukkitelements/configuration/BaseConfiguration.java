package software.ijamesroll.bukkitelements.configuration;

import lombok.Getter;
import org.bukkit.Color;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import software.ijamesroll.bukkitelements.BukkitElements;

import java.io.File;
import java.util.List;

@Getter
public final class BaseConfiguration {

    private File file;
    private FileConfiguration configuration;
    private Plugin plugin;
    private String fileName;

    public BaseConfiguration(Plugin plugin, String fileName) {
        plugin.saveResource(fileName, false);

        File file = new File(plugin.getDataFolder(), fileName);

        this.fileName = fileName;
        this.plugin = plugin;
        this.configuration = YamlConfiguration.loadConfiguration(file);
        this.file = file;
    }

    public void reloadConfiguration(){
        plugin.saveResource(fileName, false);
        File file = new File(plugin.getDataFolder(), fileName);
        this.configuration = YamlConfiguration.loadConfiguration(file);
        this.file = file;
    }

    public void save(){
        try {
            configuration.save(getFile());
        } catch (Exception exception) {
            BukkitElements.getInstance().getLogger().warning(String.format("| Error save configuration %s. Please contact to JamesRoll.", file.toString()));
        }

    }

    /**
     * Ну нврн следущая куча безполезных и простых методов кому-то пригодиться,
     * иначе буду грустить с:
     */

    public String getString(String path) {
        return configuration.getString(path, "");
    }

    public List<String> getStringList(String path) {
        return configuration.getStringList(path);
    }

    public int getInt(String path) {
        return configuration.getInt(path, 0);
    }

    public double getDouble(String path) {
        return configuration.getDouble(path, 0);
    }

    public long getLong(String path) {
        return configuration.getLong(path, 0);
    }

    public Color getColor(String path) {
        return configuration.getColor(path, Color.YELLOW);
    }

    public ConfigurationSection getSection(String path) {
        return configuration.getConfigurationSection(path);
    }

    public Object get(String path) {
        return configuration.get(path);
    }

    public void setString(String path, String value) {
        configuration.set(path, value);
        save();
    }

    public void set(String path, Object value) {
        configuration.set(path, value);
        save();
    }

    public boolean getBoolean(String path) {
        return configuration.getBoolean(path, false);
    }



}