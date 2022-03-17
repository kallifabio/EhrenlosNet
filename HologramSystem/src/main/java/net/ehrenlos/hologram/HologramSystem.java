package net.ehrenlos.hologram;

import net.ehrenlos.hologram.commands.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class HologramSystem extends JavaPlugin {

    private static HologramSystem instance;

    private static final String prefix = "§6§lEhrenlosNet §8§l» §r";
    private static final String noPerms = "§4Du hast dazu keine Rechte";

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
        getCommand("hologramcreate").setExecutor(new hologramcreateCommand());
        getCommand("hologramedit").setExecutor(new hologrameditCommand());
        getCommand("hologramhelp").setExecutor(new hologramhelpCommand());
        getCommand("hologrammremoveline").setExecutor(new hologramremovelineCommand());
        getCommand("hologramremove").setExecutor(new hologramremoveCommand());
        getCommand("holograminfo").setExecutor(new holograminfoCommand());
    }

    private void registerEvents() {
        PluginManager pluginManager = Bukkit.getPluginManager();
    }

    @Override
    public void onEnable() {
        instance = this;
        registerCommands();
        registerEvents();

        Bukkit.getConsoleSender().sendMessage(prefix + "§2Das Plugin §9" + getInstance().getDescription().getName() + " §2wurde aktiviert");
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(prefix + "§cDas Plugin §9" + getInstance().getDescription().getName() + " §cwurde deaktiviert");
    }
}
