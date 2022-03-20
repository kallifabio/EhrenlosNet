package net.ehrenlos.jumpandrun;

import net.ehrenlos.jumpandrun.commands.jumpandrunCommand;
import net.ehrenlos.jumpandrun.commands.setupCommand;
import net.ehrenlos.jumpandrun.commands.startCommand;
import net.ehrenlos.jumpandrun.gamestates.GameStates;
import net.ehrenlos.jumpandrun.listeners.PlayerListener;
import net.ehrenlos.jumpandrun.manager.ConfigManager;
import net.ehrenlos.jumpandrun.manager.GameStatesManager;
import net.ehrenlos.jumpandrun.manager.LocationManager;
import net.ehrenlos.jumpandrun.voting.Maps;
import net.ehrenlos.jumpandrun.voting.Voting;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class JumpAndRun extends JavaPlugin {

    private static JumpAndRun instance;

    private static final String prefix = "§aJumpAndRun §8§l» §r";
    private static final String noPerms = "§4Du hast dazu keine Rechte";

    private static final ConfigManager configManager = new ConfigManager();
    private static final LocationManager locationManager = new LocationManager();
    private static GameStatesManager gameStatesManager;

    private ArrayList<Player> players;
    private ArrayList<Maps> maps;
    private Voting voting;

    public static JumpAndRun getInstance() {
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

    public static GameStatesManager getGameStatesManager() {
        return gameStatesManager;
    }

    private void registerCommands() {
        getCommand("start").setExecutor(new startCommand());
        getCommand("setup").setExecutor(new setupCommand());
        getCommand("jumpandrun").setExecutor(new jumpandrunCommand());
    }

    private void registerManager() {
        gameStatesManager = new GameStatesManager(this);
    }

    private void registerEvents() {
        PluginManager pluginManager = Bukkit.getPluginManager();

        pluginManager.registerEvents(new PlayerListener(), this);
    }

    @Override
    public void onEnable() {
        instance = this;
        registerCommands();
        registerEvents();
        registerManager();
        gameStatesManager.setGameState(GameStates.LOBBY_STATE);

        Bukkit.getConsoleSender().sendMessage(prefix + "§2Das Plugin wurde aktiviert");

        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(prefix + "§4Das Plugin wurde deaktiviert");

        getServer().getMessenger().unregisterOutgoingPluginChannel(this, "BungeeCord");
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public ArrayList<Maps> getMaps() {
        return maps;
    }

    public Voting getVoting() {
        return voting;
    }
}
