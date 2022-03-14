package net.ehrenlos.nicksystem;

import net.ehrenlos.nicksystem.commands.nickCommand;
import net.ehrenlos.nicksystem.commands.unnickCommand;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class NickSystem extends JavaPlugin {

    private static NickSystem instance;

    private static String prefix = "§6§lEhrenlosNet §8§l» §r";
    private static String noPerms = "§4Du hast dazu keine Rechte";
    private static String nick = "§6§lNickSystem §8§l» §r";

    public static NickSystem getInstance() {
        return instance;
    }

    public static String getPrefix() {
        return prefix;
    }

    public static String getNoPerms() {
        return noPerms;
    }

    public static String getNick() {
        return nick;
    }

    @Override
    public void onEnable() {
        instance = this;
        registerCommands();

        Bukkit.getConsoleSender().sendMessage(nick + "§2Das Plugin §9" + getInstance().getDescription().getName() + " §2wurde aktiviert");
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(nick + "§cDas Plugin §9" + getInstance().getDescription().getName() + " §2wurde deaktiviert");
    }

    private void registerCommands() {
        getCommand("nick").setExecutor(new nickCommand());
        getCommand("unnick").setExecutor(new unnickCommand());
    }
}
