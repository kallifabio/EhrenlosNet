package net.ehrenlos.bungeesystem;

import net.ehrenlos.bungeesystem.manager.MySQLManager;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;

import java.sql.SQLException;

public class BungeeSystem extends Plugin {

    private static BungeeSystem instance;

    private static String prefix = "§6§lEhrenlosNet §8§l» §r";
    private static String noPerms = "§4Du hast dazu keine Rechte";

    private final MySQLManager mySQLManager = new MySQLManager();

    public static BungeeSystem getInstance() {
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

        BungeeCord.getInstance().getConsole().sendMessage(prefix + "§aBungeesystem wurde gestartet");

        try {
            BungeeCord.getInstance().getConsole().sendMessage(prefix + "§2Wird mit MySQL verbunden");
            mySQLManager.openConnection();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDisable() {
        unregisterEvents();
        unregisterCommands();

        BungeeCord.getInstance().getConsole().sendMessage(prefix + "§cBungeesystem wurde gestoppt");

        BungeeCord.getInstance().getConsole().sendMessage(prefix + "§2MySQL wird geschlossen");
        mySQLManager.closeConnection();
    }

    private void registerEvents() {
        PluginManager pluginManager = BungeeCord.getInstance().getPluginManager();
    }

    private void registerCommands() {
        PluginManager pluginManager = BungeeCord.getInstance().getPluginManager();
    }

    private void unregisterEvents() {
        PluginManager pluginManager = BungeeCord.getInstance().getPluginManager();
    }

    private void unregisterCommands() {
        PluginManager pluginManager = BungeeCord.getInstance().getPluginManager();
    }

    public MySQLManager getMySQLManager() {
        return mySQLManager;
    }
}
