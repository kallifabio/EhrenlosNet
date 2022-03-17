package net.ehrenlos.hologram.manager;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class LocationManager {

    private static ConfigManager configManager = new ConfigManager();

    public static void setLocation(String name, Location location) {
        ConfigManager.getHologramData().set(name + ".Position.World", location.getWorld().getName());
        ConfigManager.getHologramData().set(name + ".Position.X", location.getX());
        ConfigManager.getHologramData().set(name + ".Position.Y", location.getY());
        ConfigManager.getHologramData().set(name + ".Position.Z", location.getZ());
        ConfigManager.getHologramData().set(name + ".Position.YAW", location.getYaw());
        ConfigManager.getHologramData().set(name + ".Position.PITCH", location.getPitch());
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
        World world = Bukkit.getWorld(ConfigManager.getHologramData().getString(name + ".Position.World"));
        double x = ConfigManager.getHologramData().getDouble(name + ".Position.X");
        double y = ConfigManager.getHologramData().getDouble(name + ".Position.Y");
        double z = ConfigManager.getHologramData().getDouble(name + ".Position.Z");
        Location location = new Location(world, x, y, z);
        location.setYaw(ConfigManager.getHologramData().getInt(name + ".Position.YAW"));
        location.setPitch(ConfigManager.getHologramData().getInt(name + ".Position.PITCH"));
        return location;
    }

    public static void removeLocation(String name) {
        ConfigManager.getHologramData().set(name + ".Position.World", null);
        ConfigManager.getHologramData().set(name + ".Position.X", null);
        ConfigManager.getHologramData().set(name + ".Position.Y", null);
        ConfigManager.getHologramData().set(name + ".Position.Z", null);
        ConfigManager.getHologramData().set(name + ".Position.YAW", null);
        ConfigManager.getHologramData().set(name + ".Position.PITCH", null);
        configManager.saveHologramData();
    }
}
