package net.ehrenlos.befizzi;

import net.ehrenlos.befizzi.commands.*;
import net.ehrenlos.befizzi.listeners.PlayerListener;
import net.ehrenlos.befizzi.listeners.SleepListener;
import net.ehrenlos.befizzi.manager.ActionbarManager;
import net.ehrenlos.befizzi.manager.MySQLManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

public class Community extends JavaPlugin {

    private static final String prefix = "§a§lBeFizzi Community §8§l» §r";
    private static final String noPermission = "§4Du hast dazu keine Rechte";
    private static final MySQLManager mysqlManager = new MySQLManager();
    private static Community instance;

    public static Community getInstance() {
        return instance;
    }

    public static String getPrefix() {
        return prefix;
    }

    public static MySQLManager getMysqlManager() {
        return mysqlManager;
    }

    public static String getNoPermission() {
        return noPermission;
    }

    @Override
    public void onEnable() {
        instance = this;
        registerCommands();
        registerEvents();

        Bukkit.getConsoleSender().sendMessage(prefix + "§2Plugin wurde aktiviert");

        try {
            Bukkit.getConsoleSender().sendMessage(prefix + "§2Wird mit MySQL verbunden");
            mysqlManager.openConnection();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        updateBar();
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(prefix + "§4Plugin wurde deaktiviert");

        Bukkit.getConsoleSender().sendMessage(prefix + "§2MySQL wird geschlossen");
        mysqlManager.closeConnection();
    }

    private void registerCommands() {
        getCommand("sethome").setExecutor(new sethomeCommand());
        getCommand("homes").setExecutor(new homesCommand());
        getCommand("delhome").setExecutor(new delhomeCommand());
        getCommand("home").setExecutor(new homeCommand());
        getCommand("homesystem").setExecutor(new homesystemCommand());
    }

    private void registerEvents() {
        PluginManager pluginManager = Bukkit.getPluginManager();

        pluginManager.registerEvents(new PlayerListener(), this);
        pluginManager.registerEvents(new SleepListener(), this);
    }

    private void updateBar() {
        Bukkit.getScheduler().runTaskTimer(this, ActionbarManager::updateActionBar, 20L, 40L);
    }
}
