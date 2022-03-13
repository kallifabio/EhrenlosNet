package net.ehrenlos.hologram.manager;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class HologramsManager {

    private static ConfigManager configManager = new ConfigManager();

    public static void createHologram(String name, String line1, String line2) {
        ConfigManager.getHologramData().set(name + ".Line1", line1);
        ConfigManager.getHologramData().set(name + ".Line2", line2);
        configManager.saveHologramData();

        if (!ConfigManager.getHologram().exists()) {
            try {
                ConfigManager.getHologram().createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static String getHologramLine1(String name) {
        return ConfigManager.getHologramData().getString(name + ".Line1");
    }

    public static String getHologramLine2(String name) {
        return ConfigManager.getHologramData().getString(name + ".Line2");
    }
}
