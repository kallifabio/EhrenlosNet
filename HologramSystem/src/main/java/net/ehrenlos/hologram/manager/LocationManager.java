package net.ehrenlos.hologram.manager;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class LocationManager {

    private static ConfigManager configManager = new ConfigManager();

    public static void setLocation(String name, Location location) {
        ConfigManager.getHologramData().set(name + ".World", location.getWorld().getName());
        ConfigManager.getHologramData().set(name + ".X", location.getX());
        ConfigManager.getHologramData().set(name + ".Y", location.getY());
        ConfigManager.getHologramData().set(name + ".Z", location.getZ());
        ConfigManager.getHologramData().set(name + ".YAW", location.getYaw());
        ConfigManager.getHologramData().set(name + ".PITCH", location.getPitch());
        configManager.saveHologramData();

        if (!ConfigManager.getHologram().exists()) {
            try {
                ConfigManager.getHologram().createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static Location getLocation(String name) {
        World world = Bukkit.getWorld(ConfigManager.getHologramData().getString(name + ".World"));
        double x = ConfigManager.getHologramData().getDouble(name + ".X");
        double y = ConfigManager.getHologramData().getDouble(name + ".Y");
        double z = ConfigManager.getHologramData().getDouble(name + ".Z");
        Location location = new Location(world, x, y, z);
        location.setYaw(ConfigManager.getHologramData().getInt(name + ".YAW"));
        location.setPitch(ConfigManager.getHologramData().getInt(name + ".PITCH"));
        return location;
    }

    public static void removeLocation(String name) {
        ConfigManager.getHologramData().set(name + ".World", null);
        ConfigManager.getHologramData().set(name + ".X", null);
        ConfigManager.getHologramData().set(name + ".Y", null);
        ConfigManager.getHologramData().set(name + ".Z", null);
        ConfigManager.getHologramData().set(name + ".YAW", null);
        ConfigManager.getHologramData().set(name + ".PITCH", null);
        configManager.saveHologramData();
    }
}
