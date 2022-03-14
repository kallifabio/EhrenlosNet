package net.ehrenlos.hologram.manager;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HologramsManager {

    public static ConfigManager configManager = new ConfigManager();

    public static List<HologramsManager> holos = new ArrayList<>();

    private static ArmorStand hologram;
    private static String text;
    private static Location location;

    public static ArmorStand getHologram() {
        return hologram;
    }

    public static String getText() {
        return text;
    }

    public static Location getLocation() {
        return location;
    }

    /**
     * Create Line for Hologram
     */

    public void createHologramLine1(String name, String line1) {
        ConfigManager.getHologramData().set(name + ".Line1", line1);
        configManager.saveHologramData();

        if (!ConfigManager.getHologram().exists()) {
            try {
                ConfigManager.getHologram().createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        hologram = Objects.requireNonNull(location.subtract(0.0D, 1.0D, 0.0D).getWorld()).spawn(LocationManager.getLocation(name), ArmorStand.class);
        hologram.setCustomName(ChatColor.translateAlternateColorCodes('&', line1));
        hologram.setCustomNameVisible(true);
        hologram.setGravity(false);
        hologram.setVisible(false);
        holos.add(this);
    }

    public void createHologramLine2(String name, Location location, String line1, String line2) {
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

        hologram = Objects.requireNonNull(location.subtract(0.0D, 1.0D, 0.0D).getWorld()).spawn(LocationManager.getLocation(name), ArmorStand.class);
        hologram.setCustomName(ChatColor.translateAlternateColorCodes('&', line1 + line2));
        hologram.setCustomNameVisible(true);
        hologram.setGravity(false);
        hologram.setVisible(false);
        holos.add(this);
    }

    public void createHologramLine3(String name, String line1, String line2, String line3) {
        ConfigManager.getHologramData().set(name + ".Line1", line1);
        ConfigManager.getHologramData().set(name + ".Line2", line2);
        ConfigManager.getHologramData().set(name + ".Line3", line3);
        configManager.saveHologramData();

        if (!ConfigManager.getHologram().exists()) {
            try {
                ConfigManager.getHologram().createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        hologram = Objects.requireNonNull(location.subtract(0.0D, 1.0D, 0.0D).getWorld()).spawn(LocationManager.getLocation(name), ArmorStand.class);
        hologram.setCustomName(ChatColor.translateAlternateColorCodes('&', line1 + line2 + line3));
        hologram.setCustomNameVisible(true);
        hologram.setGravity(false);
        hologram.setVisible(false);
        holos.add(this);
    }

    public void createHologramLine4(String name, String line1, String line2, String line3, String line4) {
        ConfigManager.getHologramData().set(name + ".Line1", line1);
        ConfigManager.getHologramData().set(name + ".Line2", line2);
        ConfigManager.getHologramData().set(name + ".Line3", line3);
        ConfigManager.getHologramData().set(name + ".Line4", line4);
        configManager.saveHologramData();

        if (!ConfigManager.getHologram().exists()) {
            try {
                ConfigManager.getHologram().createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        hologram = Objects.requireNonNull(location.subtract(0.0D, 1.0D, 0.0D).getWorld()).spawn(LocationManager.getLocation(name), ArmorStand.class);
        hologram.setCustomName(ChatColor.translateAlternateColorCodes('&', line1 + line2 + line3 + line4));
        hologram.setCustomNameVisible(true);
        hologram.setGravity(false);
        hologram.setVisible(false);
        holos.add(this);
    }

    /**
     * Edit Line from Hologram
     */

    public void editHologramLine1(String name, String newline1) {
        getHologramLine1(name);
        ConfigManager.getHologramData().set(name + ".Line1", newline1);
        configManager.saveHologramData();

        hologram.setCustomName(ChatColor.translateAlternateColorCodes('&', newline1));
        hologram.setCustomNameVisible(true);
        hologram.setGravity(false);
        hologram.setVisible(false);
        holos.add(this);
    }
    public void editHologramLine2(String name, String newline2) {
        getHologramLine2(name);
        ConfigManager.getHologramData().set(name + ".Line2", newline2);
        configManager.saveHologramData();

        hologram.setCustomName(ChatColor.translateAlternateColorCodes('&', newline2));
        hologram.setCustomNameVisible(true);
        hologram.setGravity(false);
        hologram.setVisible(false);
        holos.add(this);
    }
    public void editHologramLine3(String name, String newline3) {
        getHologramLine3(name);
        ConfigManager.getHologramData().set(name + ".Line3", newline3);
        configManager.saveHologramData();

        hologram.setCustomName(ChatColor.translateAlternateColorCodes('&', newline3));
        hologram.setCustomNameVisible(true);
        hologram.setGravity(false);
        hologram.setVisible(false);
        holos.add(this);
    }
    public void editHologramLine4(String name, String newline4) {
        getHologramLine4(name);
        ConfigManager.getHologramData().set(name + ".Line4", newline4);
        configManager.saveHologramData();

        hologram.setCustomName(ChatColor.translateAlternateColorCodes('&', newline4));
        hologram.setCustomNameVisible(true);
        hologram.setGravity(false);
        hologram.setVisible(false);
        holos.add(this);
    }

    /**
     * Remove Hologram
     */

    public void removeHologram(String name) {
        ConfigManager.getHologramData().set(name, null);
        configManager.saveHologramData();
    }

    /**
     * Remove Line from Hologram
     */

    public void removeHologramLine1(String name) {
        ConfigManager.getHologramData().set(name + ".Line1", null);
        configManager.saveHologramData();
    }
    public void removeHologramLine2(String name) {
        ConfigManager.getHologramData().set(name + ".Line2", null);
        configManager.saveHologramData();
    }
    public void removeHologramLine3(String name) {
        ConfigManager.getHologramData().set(name + ".Line3", null);
        configManager.saveHologramData();
    }
    public void removeHologramLine4(String name) {
        ConfigManager.getHologramData().set(name + ".Line4", null);
        configManager.saveHologramData();
    }

    /**
     * Get Line from Hologram
     */

    public String getHologramLine1(String name) {
        return ConfigManager.getHologramData().getString(name + ".Line1");
    }

    public String getHologramLine2(String name) {
        return ConfigManager.getHologramData().getString(name + ".Line2");
    }

    public String getHologramLine3(String name) {
        return ConfigManager.getHologramData().getString(name + ".Line3");
    }

    public String getHologramLine4(String name) {
        return ConfigManager.getHologramData().getString(name + ".Line4");
    }
}
