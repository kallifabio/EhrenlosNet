package net.ehrenlos.trollsystem;

import net.ehrenlos.trollsystem.commands.trollCommand;
import net.ehrenlos.trollsystem.inventorys.TrollInventory;
import net.ehrenlos.trollsystem.listeners.PlayerListener;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;

public class TrollSystem extends JavaPlugin {

    private static final String prefix = "§9Troll §8| §r";
    private static final String noPermission = "§4Du hast dazu keine Rechte";
    private static TrollSystem instance;
    public final ArrayList<Player> freeze = new ArrayList<>();
    public final HashMap<String, String> trolling = new HashMap<>();

    public static TrollSystem getInstance() {
        return instance;
    }

    public static String getPrefix() {
        return prefix;
    }

    public static String getNoPermission() {
        return noPermission;
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

    private void registerCommands() {
        getCommand("troll").setExecutor(new trollCommand());
    }

    private void registerEvents() {
        PluginManager pluginManager = Bukkit.getPluginManager();

        pluginManager.registerEvents(new TrollInventory(), this);
        pluginManager.registerEvents(new PlayerListener(), this);
    }
}
