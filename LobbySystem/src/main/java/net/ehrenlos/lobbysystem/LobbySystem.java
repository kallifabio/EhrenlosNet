package net.ehrenlos.lobbysystem;

import net.ehrenlos.lobbysystem.commands.*;
import net.ehrenlos.lobbysystem.inventorys.*;
import net.ehrenlos.lobbysystem.listeners.EnderpearlListener;
import net.ehrenlos.lobbysystem.listeners.ItemListener;
import net.ehrenlos.lobbysystem.listeners.PlayerListener;
import net.ehrenlos.lobbysystem.listeners.SignListener;
import net.ehrenlos.lobbysystem.manager.ActionbarManager;
import net.ehrenlos.lobbysystem.manager.ConfigManager;
import net.ehrenlos.lobbysystem.manager.LocationManager;
import net.ehrenlos.lobbysystem.manager.MySQLManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

public class LobbySystem extends JavaPlugin {

    private static LobbySystem instance;

    private static String prefix = "§6§lEhrenlosNet §8§l» §r";
    private static String noPerms = "§4Du hast dazu keine Rechte";

    private static ConfigManager configManager = new ConfigManager();
    private static LocationManager locationManager = new LocationManager();
    private static MySQLManager mySQLManager = new MySQLManager();

    public static LobbySystem getInstance() {
        return instance;
    }

    public static String getPrefix() {
        return prefix;
    }

    public static String getNoPerms() {
        return noPerms;
    }

    public static ConfigManager getConfigManager() {
        return configManager;
    }

    public static LocationManager getLocationManager() {
        return locationManager;
    }

    public static MySQLManager getMySQLManager() {
        return mySQLManager;
    }

    private void registerCommands() {
        getCommand("setlocation").setExecutor(new setlocationCommand());
        getCommand("gm").setExecutor(new gamemodeCommand());
        getCommand("fly").setExecutor(new flyCommand());
        getCommand("lobbyhelp").setExecutor(new lobbyhelpCommand());
        getCommand("daily").setExecutor(new dailyCommand());
        getCommand("clearchat").setExecutor(new clearchatCommand());
    }

    private void registerEvents() {
        PluginManager pluginManager = Bukkit.getPluginManager();

        pluginManager.registerEvents(new PlayerListener(), this);
        pluginManager.registerEvents(new ItemListener(), this);
        //pluginManager.registerEvents(new EntityListener(), this);
        pluginManager.registerEvents(new SignListener(), this);
        pluginManager.registerEvents(new EnderpearlListener(), this);
    }

    private void registerInventorys() {
        PluginManager pluginManager = Bukkit.getPluginManager();

        pluginManager.registerEvents(new NavigatorInventory(), this);
        pluginManager.registerEvents(new PlayerHiderInventory(), this);
        pluginManager.registerEvents(new LobbySwitcherInventory(), this);
        pluginManager.registerEvents(new GadgetInventory(), this);
        pluginManager.registerEvents(new HeadsInventory(), this);
        pluginManager.registerEvents(new ParticleInventory(), this);
        pluginManager.registerEvents(new ExtrasInventory(), this);
    }

    private void updateBar() {
        Bukkit.getScheduler().runTaskTimer(this, ActionbarManager::updateActionBar, 20L, 40L);
    }

    @Override
    public void onEnable() {
        instance = this;
        registerEvents();
        registerCommands();
        registerInventorys();

        Bukkit.getConsoleSender().sendMessage(prefix + "§2Das Plugin §9" + getInstance().getDescription().getName() + " §2wurde aktiviert");

        try {
            Bukkit.getConsoleSender().sendMessage(prefix + "§2Wird mit MySQL verbunden");
            mySQLManager.openConnection();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");

        updateBar();
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(prefix + "§cDas Plugin §9" + getInstance().getDescription().getName() + " §cwurde deaktiviert");

        Bukkit.getConsoleSender().sendMessage(prefix + "§2MySQL wird geschlossen");
        mySQLManager.closeConnection();

        getServer().getMessenger().unregisterOutgoingPluginChannel(this, "BungeeCord");
    }
}
