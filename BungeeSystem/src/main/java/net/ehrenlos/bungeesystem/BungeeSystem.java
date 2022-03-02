package net.ehrenlos.bungeesystem;

import net.ehrenlos.bungeesystem.commands.*;
import net.ehrenlos.bungeesystem.listeners.LogInListener;
import net.ehrenlos.bungeesystem.listeners.PlayerDisconnectListener;
import net.ehrenlos.bungeesystem.listeners.ProxyPingListener;
import net.ehrenlos.bungeesystem.listeners.ServerConnectListener;
import net.ehrenlos.bungeesystem.manager.CoinsSystemManager;
import net.ehrenlos.bungeesystem.manager.MySQLManager;
import net.ehrenlos.bungeesystem.manager.OnlineTimeManager;
import net.ehrenlos.bungeesystem.manager.ServerInfoManager;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;
import sun.rmi.runtime.Log;

import java.sql.SQLException;
import java.util.ArrayList;

public class BungeeSystem extends Plugin {

    private static BungeeSystem instance;

    private static String prefix = "§6§lEhrenlosNet §8§l» §r";
    private static String noPerms = "§4Du hast dazu keine Rechte";
    private static String teamPrefix = "§7[§cTeam§7] §r";
    private static String clanPrefix = "§7[§cClan§7] §r";
    private static String partyPrefix = "§7[§cParty§7] §r";

    private final MySQLManager mySQLManager = new MySQLManager();
    private static CoinsSystemManager coinsSystemManager;
    private static OnlineTimeManager onlineTimeManager;
    public static ServerInfoManager serverInfoManager = new ServerInfoManager();
    public static ServerInfoManager schedulerController = new ServerInfoManager();

    public static ArrayList<ProxiedPlayer> staff = new ArrayList<>();
    public static ArrayList<ProxiedPlayer> notify = new ArrayList<>();

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

        OnlineTimeManager.startOnlineTimeTracking();
        ServerInfoManager.startScheduler();
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

        pluginManager.registerListener(this, new PlayerDisconnectListener());
        pluginManager.registerListener(this, new ServerConnectListener());
        pluginManager.registerListener(this, new LogInListener());
        pluginManager.registerListener(this, new ProxyPingListener());
    }

    public static BungeeSystem getInstance() {
        return instance;
    }

    public static String getPrefix() {
        return prefix;
    }

    public static String getNoPerms() {
        return noPerms;
    }

    public static CoinsSystemManager getCoinsSystemManager() {
        return coinsSystemManager;
    }

    public OnlineTimeManager getOnlineTimeManager() {
        return onlineTimeManager;
    }

    public static ArrayList<ProxiedPlayer> getStaff() {
        return staff;
    }

    public static ArrayList<ProxiedPlayer> getNotify() {
        return notify;
    }

    private void unregisterEvents() {
        PluginManager pluginManager = BungeeCord.getInstance().getPluginManager();

        pluginManager.unregisterListener(new PlayerDisconnectListener());
        pluginManager.unregisterListener(new ServerConnectListener());
        pluginManager.unregisterListener(new LogInListener());
        pluginManager.unregisterListener(new ProxyPingListener());
    }

    private void registerCommands() {
        PluginManager pluginManager = BungeeCord.getInstance().getPluginManager();

        pluginManager.registerCommand(this, new coinsCommand("coins"));
        pluginManager.registerCommand(this, new teamchatCommand("tc", "teamchat"));
        pluginManager.registerCommand(this, new onlinetimeCommand("onlinetime"));
        pluginManager.registerCommand(this, new onlinetimetopCommand("onlinetimetop"));
        pluginManager.registerCommand(this, new joinmeCommand("joinme"));
        pluginManager.registerCommand(this, new staffCommand("staff"));
        pluginManager.registerCommand(this, new jumpCommand("jump"));
        pluginManager.registerCommand(this, new pingCommand("ping"));
        pluginManager.registerCommand(this, new broadcastCommand("broadcast", "br"));
        pluginManager.registerCommand(this, new serverinfoCommand("serverinfo"));
    }

    public MySQLManager getMySQLManager() {
        return mySQLManager;
    }

    private void unregisterCommands() {
        PluginManager pluginManager = BungeeCord.getInstance().getPluginManager();

        pluginManager.unregisterCommand(new coinsCommand("coins"));
        pluginManager.unregisterCommand(new teamchatCommand("tc", "teamchat"));
        pluginManager.unregisterCommand(new onlinetimeCommand("onlinetime"));
        pluginManager.unregisterCommand(new onlinetimetopCommand("onlinetimetop"));
        pluginManager.unregisterCommand(new joinmeCommand("joinme"));
        pluginManager.unregisterCommand(new staffCommand("staff"));
        pluginManager.unregisterCommand(new jumpCommand("jump"));
        pluginManager.unregisterCommand(new pingCommand("ping"));
        pluginManager.unregisterCommand(new broadcastCommand("broadcast", "br"));
        pluginManager.unregisterCommand(new serverinfoCommand("serverinfo"));
    }

    public static String getTeamPrefix() {
        return teamPrefix;
    }

    public static String getClanPrefix() {
        return clanPrefix;
    }

    public static String getPartyPrefix() {
        return partyPrefix;
    }

    public static ServerInfoManager getServerInfoManager() {
        return serverInfoManager;
    }

    public static ServerInfoManager getSchedulerController() {
        return schedulerController;
    }
}
