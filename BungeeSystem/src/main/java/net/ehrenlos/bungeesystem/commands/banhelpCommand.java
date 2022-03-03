package net.ehrenlos.bungeesystem.commands;

import net.ehrenlos.bungeesystem.BungeeSystem;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

public class banhelpCommand extends Command {

    public banhelpCommand(String name) {
        super(name);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender.hasPermission("bungeesystem.ban") || sender.hasPermission("bungeesystem.tempban") || sender.hasPermission("bungeesystem.*")) {
            if (args.length == 0) {
                sender.sendMessage("§8§m-----------§7 [§cBanHelp§7] §8§m-------------");
                sender.sendMessage("§e/ban <Player> <Reason> §8- §7Banne einen Spieler §epermanent §7vom Netzwerk!");
                sender.sendMessage("§e/tempban <Player> <Time> <TimeUnit> <Reason> §8- §7Banne einen Spieler §etemporär §7vom Netzwerk!");
                sender.sendMessage("§e/check <Player> §8- §7Checke den §eBan Status §7von einem Spieler!");
                sender.sendMessage(" ");
                sender.sendMessage("§7TimeUnits");
                sender.sendMessage("§eSekunde(n) §8= §7s");
                sender.sendMessage("§eMinute(n) §8= §7m");
                sender.sendMessage("§eStunde(n) §8= §7h");
                sender.sendMessage("§eTag(e) §8= §7d");
                sender.sendMessage("§eWoche(n) §8= §7w");
                sender.sendMessage("§8§m-----------§7 [§cBanHelp§7] §8§m-------------");
            } else {
                sender.sendMessage(BungeeSystem.getPrefix() + "§cBenutze: §e%command%".replaceAll("%command%", "/banhelp"));
            }
        } else {
            sender.sendMessage(BungeeSystem.getPrefix() + BungeeSystem.getNoPerms());
        }
    }
}
