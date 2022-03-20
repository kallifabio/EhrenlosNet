package net.ehrenlos.jumpandrun.manager;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class LocationManager {

    private static ConfigManager configManager = new ConfigManager();

    /**
     * Arena
     */

    public static void createArena(String name) {
        ConfigManager.getGetLocationData().set("Arenas." + name, name);
        configManager.saveLocationData();

        if (!ConfigManager.getLocation().exists()) {
            try {
                ConfigManager.getLocation().createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static String getArena(String name) {
        return ConfigManager.getGetLocationData().getString("Arenas");
    }

    public static String getArena() {
        return ConfigManager.getGetLocationData().getString("Arenas");
    }

    public static Location getLobbyLocation() {
        World world = Bukkit.getWorld(ConfigManager.getGetLocationData().getString("LobbySpawn.World"));
        double x = ConfigManager.getGetLocationData().getDouble("LobbySpawn.X");
        double y = ConfigManager.getGetLocationData().getDouble("LobbySpawn.Y");
        double z = ConfigManager.getGetLocationData().getDouble("LobbySpawn.Z");
        Location location = new Location(world, x, y, z);
        location.setYaw(ConfigManager.getGetLocationData().getInt("LobbySpawn.YAW"));
        location.setPitch(ConfigManager.getGetLocationData().getInt("LobbySpawn.PITCH"));
        return location;
    }

    /**
     * Lobby
     */

    public static void setLobbyLocation(Location location) {
        ConfigManager.getGetLocationData().set("LobbySpawn.World", location.getWorld().getName());
        ConfigManager.getGetLocationData().set("LobbySpawn.X", location.getX());
        ConfigManager.getGetLocationData().set("LobbySpawn.Y", location.getY());
        ConfigManager.getGetLocationData().set("LobbySpawn.Z", location.getZ());
        ConfigManager.getGetLocationData().set("LobbySpawn.YAW", location.getYaw());
        ConfigManager.getGetLocationData().set("LobbySpawn.PITCH", location.getPitch());
        configManager.saveLocationData();
    }

    public static boolean getLobbyLocationBoolean() {
        World world = Bukkit.getWorld(ConfigManager.getGetLocationData().getString("LobbySpawn.World"));
        double x = ConfigManager.getGetLocationData().getDouble("LobbySpawn.X");
        double y = ConfigManager.getGetLocationData().getDouble("LobbySpawn.Y");
        double z = ConfigManager.getGetLocationData().getDouble("LobbySpawn.Z");
        Location location = new Location(world, x, y, z);
        location.setYaw(ConfigManager.getGetLocationData().getInt("LobbySpawn.YAW"));
        location.setPitch(ConfigManager.getGetLocationData().getInt("LobbySpawn.PITCH"));
        return false;
    }

    /**
     * Player
     */

    public static void setPlayer1Location(String name, Location location) {
        ConfigManager.getGetLocationData().set("Arenas." + name + ".Player1Spawn.World", location.getWorld().getName());
        ConfigManager.getGetLocationData().set("Arenas." + name + ".Player1Spawn.X", location.getX());
        ConfigManager.getGetLocationData().set("Arenas." + name + ".Player1Spawn.Y", location.getY());
        ConfigManager.getGetLocationData().set("Arenas." + name + ".Player1Spawn.Z", location.getZ());
        ConfigManager.getGetLocationData().set("Arenas." + name + ".Player1Spawn.YAW", location.getYaw());
        ConfigManager.getGetLocationData().set("Arenas." + name + ".Player1Spawn.PITCH", location.getPitch());
        configManager.saveLocationData();
    }

    public static Location getPlayer1Location(String name) {
        World world = Bukkit.getWorld(ConfigManager.getGetLocationData().getString("Arenas." + name + ".Player1Spawn.World"));
        double x = ConfigManager.getGetLocationData().getDouble("Arenas." + name + ".Player1Spawn.X");
        double y = ConfigManager.getGetLocationData().getDouble("Arenas." + name + ".Player1Spawn.Y");
        double z = ConfigManager.getGetLocationData().getDouble("Arenas." + name + ".Player1Spawn.Z");
        Location location = new Location(world, x, y, z);
        location.setYaw(ConfigManager.getGetLocationData().getInt("Arenas." + name + ".Player1Spawn.YAW"));
        location.setPitch(ConfigManager.getGetLocationData().getInt("Arenas." + name + ".Player1Spawn.PITCH"));
        return location;
    }

    public static void setPlayer2Location(String name, Location location) {
        ConfigManager.getGetLocationData().set("Arenas." + name + ".Player2Spawn.World", location.getWorld().getName());
        ConfigManager.getGetLocationData().set("Arenas." + name + ".Player2Spawn.X", location.getX());
        ConfigManager.getGetLocationData().set("Arenas." + name + ".Player2Spawn.Y", location.getY());
        ConfigManager.getGetLocationData().set("Arenas." + name + ".Player2Spawn.Z", location.getZ());
        ConfigManager.getGetLocationData().set("Arenas." + name + ".Player2Spawn.YAW", location.getYaw());
        ConfigManager.getGetLocationData().set("Arenas." + name + ".Player2Spawn.PITCH", location.getPitch());
        configManager.saveLocationData();
    }

    public static Location getPlayer2Location(String name) {
        World world = Bukkit.getWorld(ConfigManager.getGetLocationData().getString("Arenas." + name + ".Player2Spawn.World"));
        double x = ConfigManager.getGetLocationData().getDouble("Arenas." + name + ".Player2Spawn.X");
        double y = ConfigManager.getGetLocationData().getDouble("Arenas." + name + ".Player2Spawn.Y");
        double z = ConfigManager.getGetLocationData().getDouble("Arenas." + name + ".Player2Spawn.Z");
        Location location = new Location(world, x, y, z);
        location.setYaw(ConfigManager.getGetLocationData().getInt("Arenas." + name + ".Player2Spawn.YAW"));
        location.setPitch(ConfigManager.getGetLocationData().getInt("Arenas." + name + ".Player2Spawn.PITCH"));
        return location;
    }

    public static void setPlayer3Location(String name, Location location) {
        ConfigManager.getGetLocationData().set("Arenas." + name + ".Player3Spawn.World", location.getWorld().getName());
        ConfigManager.getGetLocationData().set("Arenas." + name + ".Player3Spawn.X", location.getX());
        ConfigManager.getGetLocationData().set("Arenas." + name + ".Player3Spawn.Y", location.getY());
        ConfigManager.getGetLocationData().set("Arenas." + name + ".Player3Spawn.Z", location.getZ());
        ConfigManager.getGetLocationData().set("Arenas." + name + ".Player3Spawn.YAW", location.getYaw());
        ConfigManager.getGetLocationData().set("Arenas." + name + ".Player3Spawn.PITCH", location.getPitch());
        configManager.saveLocationData();
    }

    public static Location getPlayer3Location(String name) {
        World world = Bukkit.getWorld(ConfigManager.getGetLocationData().getString("Arenas." + name + ".Player3Spawn.World"));
        double x = ConfigManager.getGetLocationData().getDouble("Arenas." + name + ".Player3Spawn.X");
        double y = ConfigManager.getGetLocationData().getDouble("Arenas." + name + ".Player3Spawn.Y");
        double z = ConfigManager.getGetLocationData().getDouble("Arenas." + name + ".Player3Spawn.Z");
        Location location = new Location(world, x, y, z);
        location.setYaw(ConfigManager.getGetLocationData().getInt("Arenas." + name + ".Player3Spawn.YAW"));
        location.setPitch(ConfigManager.getGetLocationData().getInt("Arenas." + name + ".Player3Spawn.PITCH"));
        return location;
    }

    public static void setPlayer4Location(String name, Location location) {
        ConfigManager.getGetLocationData().set("Arenas." + name + ".Player4Spawn.World", location.getWorld().getName());
        ConfigManager.getGetLocationData().set("Arenas." + name + ".Player4Spawn.X", location.getX());
        ConfigManager.getGetLocationData().set("Arenas." + name + ".Player4Spawn.Y", location.getY());
        ConfigManager.getGetLocationData().set("Arenas." + name + ".Player4Spawn.Z", location.getZ());
        ConfigManager.getGetLocationData().set("Arenas." + name + ".Player4Spawn.YAW", location.getYaw());
        ConfigManager.getGetLocationData().set("Arenas." + name + ".Player4Spawn.PITCH", location.getPitch());
        configManager.saveLocationData();
    }

    public static Location getPlayer4Location(String name) {
        World world = Bukkit.getWorld(ConfigManager.getGetLocationData().getString("Arenas." + name + ".Player4Spawn.World"));
        double x = ConfigManager.getGetLocationData().getDouble("Arenas." + name + ".Player4Spawn.X");
        double y = ConfigManager.getGetLocationData().getDouble("Arenas." + name + ".Player4Spawn.Y");
        double z = ConfigManager.getGetLocationData().getDouble("Arenas." + name + ".Player4Spawn.Z");
        Location location = new Location(world, x, y, z);
        location.setYaw(ConfigManager.getGetLocationData().getInt("Arenas." + name + ".Player4Spawn.YAW"));
        location.setPitch(ConfigManager.getGetLocationData().getInt("Arenas." + name + ".Player4Spawn.PITCH"));
        return location;
    }

    public static void setPlayer5Location(String name, Location location) {
        ConfigManager.getGetLocationData().set("Arenas." + name + ".Player5Spawn.World", location.getWorld().getName());
        ConfigManager.getGetLocationData().set("Arenas." + name + ".Player5Spawn.X", location.getX());
        ConfigManager.getGetLocationData().set("Arenas." + name + ".Player5Spawn.Y", location.getY());
        ConfigManager.getGetLocationData().set("Arenas." + name + ".Player5Spawn.Z", location.getZ());
        ConfigManager.getGetLocationData().set("Arenas." + name + ".Player5Spawn.YAW", location.getYaw());
        ConfigManager.getGetLocationData().set("Arenas." + name + ".Player5Spawn.PITCH", location.getPitch());
        configManager.saveLocationData();
    }

    public static Location getPlayer5Location(String name) {
        World world = Bukkit.getWorld(ConfigManager.getGetLocationData().getString("Arenas." + name + ".Player5Spawn.World"));
        double x = ConfigManager.getGetLocationData().getDouble("Arenas." + name + ".Player5Spawn.X");
        double y = ConfigManager.getGetLocationData().getDouble("Arenas." + name + ".Player5Spawn.Y");
        double z = ConfigManager.getGetLocationData().getDouble("Arenas." + name + ".Player5Spawn.Z");
        Location location = new Location(world, x, y, z);
        location.setYaw(ConfigManager.getGetLocationData().getInt("Arenas." + name + ".Player5Spawn.YAW"));
        location.setPitch(ConfigManager.getGetLocationData().getInt("Arenas." + name + ".Player5Spawn.PITCH"));
        return location;
    }

    /**
     * Checkpoint
     */

    public static void setCheckpoint1Location(String name, Location location) {
        ConfigManager.getGetLocationData().set("Arenas." + name + ".Checkpoint1.World", location.getWorld().getName());
        ConfigManager.getGetLocationData().set("Arenas." + name + ".Checkpoint1.X", location.getX());
        ConfigManager.getGetLocationData().set("Arenas." + name + ".Checkpoint1.Y", location.getY());
        ConfigManager.getGetLocationData().set("Arenas." + name + ".Checkpoint1.Z", location.getZ());
        ConfigManager.getGetLocationData().set("Arenas." + name + ".Checkpoint1.YAW", location.getYaw());
        ConfigManager.getGetLocationData().set("Arenas." + name + ".Checkpoint1.PITCH", location.getPitch());
        configManager.saveLocationData();
    }

    public static Location getCheckpoint1Location(String name) {
        World world = Bukkit.getWorld(ConfigManager.getGetLocationData().getString("Arenas." + name + ".Checkpoint1.World"));
        double x = ConfigManager.getGetLocationData().getDouble("Arenas." + name + ".Checkpoint1.X");
        double y = ConfigManager.getGetLocationData().getDouble("Arenas." + name + ".Checkpoint1.Y");
        double z = ConfigManager.getGetLocationData().getDouble("Arenas." + name + ".Checkpoint1.Z");
        Location location = new Location(world, x, y, z);
        location.setYaw(ConfigManager.getGetLocationData().getInt("Arenas." + name + ".Checkpoint1.YAW"));
        location.setPitch(ConfigManager.getGetLocationData().getInt("Arenas." + name + ".Checkpoint1.PITCH"));
        return location;
    }

    public static void setCheckpoint2Location(String name, Location location) {
        ConfigManager.getGetLocationData().set("Arenas." + name + ".Checkpoint2.World", location.getWorld().getName());
        ConfigManager.getGetLocationData().set("Arenas." + name + ".Checkpoint2.X", location.getX());
        ConfigManager.getGetLocationData().set("Arenas." + name + ".Checkpoint2.Y", location.getY());
        ConfigManager.getGetLocationData().set("Arenas." + name + ".Checkpoint2.Z", location.getZ());
        ConfigManager.getGetLocationData().set("Arenas." + name + ".Checkpoint2.YAW", location.getYaw());
        ConfigManager.getGetLocationData().set("Arenas." + name + ".Checkpoint2.PITCH", location.getPitch());
        configManager.saveLocationData();
    }

    public static Location getCheckpoint2Location(String name) {
        World world = Bukkit.getWorld(ConfigManager.getGetLocationData().getString("Arenas." + name + ".Checkpoint2.World"));
        double x = ConfigManager.getGetLocationData().getDouble("Arenas." + name + ".Checkpoint2.X");
        double y = ConfigManager.getGetLocationData().getDouble("Arenas." + name + ".Checkpoint2.Y");
        double z = ConfigManager.getGetLocationData().getDouble("Arenas." + name + ".Checkpoint2.Z");
        Location location = new Location(world, x, y, z);
        location.setYaw(ConfigManager.getGetLocationData().getInt("Arenas." + name + ".Checkpoint2.YAW"));
        location.setPitch(ConfigManager.getGetLocationData().getInt("Arenas." + name + ".Checkpoint2.PITCH"));
        return location;
    }

    public static void setCheckpoint3Location(String name, Location location) {
        ConfigManager.getGetLocationData().set("Arenas." + name + ".Checkpoint3.World", location.getWorld().getName());
        ConfigManager.getGetLocationData().set("Arenas." + name + ".Checkpoint3.X", location.getX());
        ConfigManager.getGetLocationData().set("Arenas." + name + ".Checkpoint3.Y", location.getY());
        ConfigManager.getGetLocationData().set("Arenas." + name + ".Checkpoint3.Z", location.getZ());
        ConfigManager.getGetLocationData().set("Arenas." + name + ".Checkpoint3.YAW", location.getYaw());
        ConfigManager.getGetLocationData().set("Arenas." + name + ".Checkpoint3.PITCH", location.getPitch());
        configManager.saveLocationData();
    }

    public static Location getCheckpoint3Location(String name) {
        World world = Bukkit.getWorld(ConfigManager.getGetLocationData().getString("Arenas." + name + ".Checkpoint3.World"));
        double x = ConfigManager.getGetLocationData().getDouble("Arenas." + name + ".Checkpoint3.X");
        double y = ConfigManager.getGetLocationData().getDouble("Arenas." + name + ".Checkpoint3.Y");
        double z = ConfigManager.getGetLocationData().getDouble("Arenas." + name + ".Checkpoint3.Z");
        Location location = new Location(world, x, y, z);
        location.setYaw(ConfigManager.getGetLocationData().getInt("Arenas." + name + ".Checkpoint3.YAW"));
        location.setPitch(ConfigManager.getGetLocationData().getInt("Arenas." + name + ".Checkpoint3.PITCH"));
        return location;
    }

    public static void setCheckpoint4Location(String name, Location location) {
        ConfigManager.getGetLocationData().set("Arenas." + name + ".Checkpoint4.World", location.getWorld().getName());
        ConfigManager.getGetLocationData().set("Arenas." + name + ".Checkpoint4.X", location.getX());
        ConfigManager.getGetLocationData().set("Arenas." + name + ".Checkpoint4.Y", location.getY());
        ConfigManager.getGetLocationData().set("Arenas." + name + ".Checkpoint4.Z", location.getZ());
        ConfigManager.getGetLocationData().set("Arenas." + name + ".Checkpoint4.YAW", location.getYaw());
        ConfigManager.getGetLocationData().set("Arenas." + name + ".Checkpoint4.PITCH", location.getPitch());
        configManager.saveLocationData();
    }

    public static Location getCheckpoint4Location(String name) {
        World world = Bukkit.getWorld(ConfigManager.getGetLocationData().getString("Arenas." + name + ".Checkpoint4.World"));
        double x = ConfigManager.getGetLocationData().getDouble("Arenas." + name + ".Checkpoint4.X");
        double y = ConfigManager.getGetLocationData().getDouble("Arenas." + name + ".Checkpoint4.Y");
        double z = ConfigManager.getGetLocationData().getDouble("Arenas." + name + ".Checkpoint4.Z");
        Location location = new Location(world, x, y, z);
        location.setYaw(ConfigManager.getGetLocationData().getInt("Arenas." + name + ".Checkpoint4.YAW"));
        location.setPitch(ConfigManager.getGetLocationData().getInt("Arenas." + name + ".Checkpoint4.PITCH"));
        return location;
    }

    public static void setCheckpoint5Location(String name, Location location) {
        ConfigManager.getGetLocationData().set("Arenas." + name + ".Checkpoint5.World", location.getWorld().getName());
        ConfigManager.getGetLocationData().set("Arenas." + name + ".Checkpoint5.X", location.getX());
        ConfigManager.getGetLocationData().set("Arenas." + name + ".Checkpoint5.Y", location.getY());
        ConfigManager.getGetLocationData().set("Arenas." + name + ".Checkpoint5.Z", location.getZ());
        ConfigManager.getGetLocationData().set("Arenas." + name + ".Checkpoint5.YAW", location.getYaw());
        ConfigManager.getGetLocationData().set("Arenas." + name + ".Checkpoint5.PITCH", location.getPitch());
        configManager.saveLocationData();
    }

    public static Location getCheckpoint5Location(String name) {
        World world = Bukkit.getWorld(ConfigManager.getGetLocationData().getString("Arenas." + name + ".Checkpoint5.World"));
        double x = ConfigManager.getGetLocationData().getDouble("Arenas." + name + ".Checkpoint5.X");
        double y = ConfigManager.getGetLocationData().getDouble("Arenas." + name + ".Checkpoint5.Y");
        double z = ConfigManager.getGetLocationData().getDouble("Arenas." + name + ".Checkpoint5.Z");
        Location location = new Location(world, x, y, z);
        location.setYaw(ConfigManager.getGetLocationData().getInt("Arenas." + name + ".Checkpoint5.YAW"));
        location.setPitch(ConfigManager.getGetLocationData().getInt("Arenas." + name + ".Checkpoint5.PITCH"));
        return location;
    }

    /**
     * Builder
     */

    public static void setBuilder(String name, String builder) {
        ConfigManager.getGetLocationData().set("Arenas." + name + ".Builder", builder);
        configManager.saveLocationData();
    }

    public static String getBuilder(String name) {
        return ConfigManager.getGetLocationData().getString("Arenas." + name + ".Builder");
    }

}
