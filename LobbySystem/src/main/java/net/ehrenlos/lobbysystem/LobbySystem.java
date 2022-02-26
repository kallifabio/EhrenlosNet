package net.ehrenlos.lobbysystem;

import net.ehrenlos.lobbysystem.commands.flyCommand;
import net.ehrenlos.lobbysystem.commands.gamemodeCommand;
import net.ehrenlos.lobbysystem.commands.setlocationCommand;
import net.ehrenlos.lobbysystem.inventorys.NavigatorInventory;
import net.ehrenlos.lobbysystem.listeners.PlayerListener;
import net.ehrenlos.lobbysystem.manager.ConfigManager;
import net.ehrenlos.lobbysystem.manager.LocationManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class LobbySystem extends JavaPlugin {

    private static LobbySystem instance;

    private static String prefix = "§6§lEhrenlosNet §8§l» §r";
    private static String noPerms = "§4Du hast dazu keine Rechte";

    private static ConfigManager configManager = new ConfigManager();
    private static LocationManager locationManager = new LocationManager();

    public static LobbySystem getInstance() {
        return instance;
    }

    public static String getPrefix() {
        return prefix;
    }

    public static String getNoPerms() {
        return noPerms;
    }

    @Override
    public void onEnable() {
        instance = this;
        registerEvents();
        registerCommands();
        registerInventorys();

        Bukkit.getConsoleSender().sendMessage(prefix + "§2Das Plugin wurde aktiviert");
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(prefix + "§cDas Plugin wurde deaktiviert");
    }

    private void registerCommands() {
        getCommand("setlocation").setExecutor(new setlocationCommand());
        getCommand("gm").setExecutor(new gamemodeCommand());
        getCommand("fly").setExecutor(new flyCommand());
    }

    private void registerEvents() {
        PluginManager pluginManager = Bukkit.getPluginManager();

        pluginManager.registerEvents(new PlayerListener(), this);
    }

    private void registerInventorys() {
        PluginManager pluginManager = Bukkit.getPluginManager();

        pluginManager.registerEvents(new NavigatorInventory(), this);
    }
}
