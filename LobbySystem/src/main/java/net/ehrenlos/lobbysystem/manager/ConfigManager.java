package net.ehrenlos.lobbysystem.manager;

import net.ehrenlos.lobbysystem.utils.FileBuilder;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigManager {

    public static File location = new File("plugins/Lobbysystem", "location.yml");
    public static FileConfiguration getLocationData = YamlConfiguration.loadConfiguration(location);
    private static FileBuilder builder;

    public static FileConfiguration getGetLocationData() {
        return getLocationData;
    }

    public static File getLocation() {
        return location;
    }

    public static FileBuilder getBuilder() {
        return builder;
    }

    public void saveLocationData() {
        try {
            getLocationData.save(location);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(ChatColor.DARK_RED + "§lERROR");
        }
    }
}
