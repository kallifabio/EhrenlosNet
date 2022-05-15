package net.ehrenlos.befizzi.manager;

public class StatusManager {

    private static final ConfigManager configManager = new ConfigManager();

    public static void setStatus(String player, String name, boolean status) {
        ConfigManager.getLocationData().set(player + "." + name + ".Status", status);
        configManager.saveLocationData();
    }

    public static boolean getStatus(String player, String name) {
        return ConfigManager.getLocationData().getBoolean(player + "." + name + ".Status");
    }
}
