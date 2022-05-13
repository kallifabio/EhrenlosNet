package net.ehrenlos.befizzi.manager;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigManager {

    public static File location = new File("plugins/Community", "homes.yml");
    public static FileConfiguration locationData = YamlConfiguration.loadConfiguration(location);

    public static FileConfiguration getLocationData() {
        return locationData;
    }

    public static File getLocation() {
        return location;
    }

    public void saveLocationData() {
        try {
            getLocationData().save(location);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(ChatColor.DARK_RED + "§lERROR");
        }
    }
}
