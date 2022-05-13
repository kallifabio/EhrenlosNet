package net.ehrenlos.befizzi.manager;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class LocationManager {

    private static final ConfigManager configManager = new ConfigManager();

    public static void setLocation(String player, String name, Location location) {
        ConfigManager.getLocationData().set(player + "." + name + ".World", location.getWorld().getName());
        ConfigManager.getLocationData().set(player + "." + name + ".X", location.getX());
        ConfigManager.getLocationData().set(player + "." + name + ".Y", location.getY());
        ConfigManager.getLocationData().set(player + "." + name + ".Z", location.getZ());
        ConfigManager.getLocationData().set(player + "." + name + ".YAW", location.getYaw());
        ConfigManager.getLocationData().set(player + "." + name + ".PITCH", location.getPitch());
        configManager.saveLocationData();

        if (!ConfigManager.getLocation().exists()) {
            try {
                ConfigManager.getLocation().createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static Location getLocation(String player, String name) {
        World world = Bukkit.getWorld(ConfigManager.getLocationData().getString(player + "." + name + ".World"));
        double x = ConfigManager.getLocationData().getDouble(player + "." + name + ".X");
        double y = ConfigManager.getLocationData().getDouble(player + "." + name + ".Y");
        double z = ConfigManager.getLocationData().getDouble(player + "." + name + ".Z");
        Location location = new Location(world, x, y, z);
        location.setYaw(ConfigManager.getLocationData().getInt(player + "." + name + ".YAW"));
        location.setPitch(ConfigManager.getLocationData().getInt(player + "." + name + ".PITCH"));
        return location;
    }

    public static void removeLocation(String player, String name) {
        ConfigManager.getLocationData().set(player + "." + name, null);
        configManager.saveLocationData();
    }
}
