package net.ehrenlos.hologram;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class HologramSystem extends JavaPlugin {

    private static HologramSystem instance;

    private static String prefix = "§6§lEhrenlosNet §8§l» §r";
    private static String noPerms = "§4Du hast dazu keine Rechte";

    public static HologramSystem getInstance() {
        return instance;
    }

    public static String getPrefix() {
        return prefix;
    }

    public static String getNoPerms() {
        return noPerms;
    }

    private void registerCommands() {

    }

    private void registerEvents() {
        PluginManager pluginManager = Bukkit.getPluginManager();
    }

    @Override
    public void onEnable() {
        instance = this;
        registerCommands();
        registerEvents();

        Bukkit.getConsoleSender().sendMessage(prefix + "§2Das Plugin wurde aktiviert");
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(prefix + "§cDas Plugin wurde deaktiviert");
    }
}
