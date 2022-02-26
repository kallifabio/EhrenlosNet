package net.ehrenlos.lobbysystem.manager;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class LocationManager {

    private static ConfigManager configManager = new ConfigManager();

    public static void setLocation(String name, Location location) {
        ConfigManager.getGetLocationData().set(name + ".World", location.getWorld().getName());
        ConfigManager.getGetLocationData().set(name + ".X", location.getX());
        ConfigManager.getGetLocationData().set(name + ".Y", location.getY());
        ConfigManager.getGetLocationData().set(name + ".Z", location.getZ());
        ConfigManager.getGetLocationData().set(name + ".YAW", location.getYaw());
        ConfigManager.getGetLocationData().set(name + ".PITCH", location.getPitch());
        configManager.saveLocationData();

        if (!ConfigManager.getLocation().exists()) {
            try {
                ConfigManager.getLocation().createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static Location getLocation(String name) {
        World world = Bukkit.getWorld(ConfigManager.getGetLocationData().getString(name + ".World"));
        double x = ConfigManager.getGetLocationData().getDouble(name + ".X");
        double y = ConfigManager.getGetLocationData().getDouble(name + ".Y");
        double z = ConfigManager.getGetLocationData().getDouble(name + ".Z");
        Location location = new Location(world, x, y, z);
        location.setYaw(ConfigManager.getGetLocationData().getInt(name + ".YAW"));
        location.setPitch(ConfigManager.getGetLocationData().getInt(name + ".PITCH"));
        return location;
    }
}
