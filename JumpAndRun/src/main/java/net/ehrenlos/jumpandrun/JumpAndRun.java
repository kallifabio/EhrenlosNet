package net.ehrenlos.jumpandrun;

import net.ehrenlos.jumpandrun.gamestates.GameStates;
import net.ehrenlos.jumpandrun.manager.ConfigManager;
import net.ehrenlos.jumpandrun.manager.GameStatesManager;
import net.ehrenlos.jumpandrun.manager.LocationManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class JumpAndRun extends JavaPlugin {

    private static JumpAndRun instance;

    private static String prefix = "§aJumpAndRun §8§l» §r";
    private static String noPerms = "§4Du hast dazu keine Rechte";

    private static ConfigManager configManager = new ConfigManager();
    private static LocationManager locationManager = new LocationManager();
    private static GameStatesManager gameStatesManager;

    private ArrayList<Player> players;

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

    private void registerCommands() {

    }

    private void registerManager() {
        gameStatesManager = new GameStatesManager(this);
    }

    private void registerEvents() {
        PluginManager pluginManager = Bukkit.getPluginManager();
    }

    @Override
    public void onEnable() {
        instance = this;
        registerCommands();
        registerEvents();
        registerManager();
        gameStatesManager.setGameState(GameStates.LOBBY_STATE);

        Bukkit.getConsoleSender().sendMessage(prefix + "§2Das Plugin wurde aktiviert");
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(prefix + "§4Das Plugin wurde deaktiviert");
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }
}
