package net.ehrenlos.befizzi.commands;

import net.ehrenlos.befizzi.Community;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class msgCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Community.getPrefix() + "§cDu musst ein Spieler sein");
            return false;
        }

        final Player player = (Player) sender;

        if (cmd.getName().equalsIgnoreCase("msg")) {
            if (player.hasPermission("community.command.msg")) {
                if (args.length < 2) {
                    player.sendMessage(Community.getPrefix() + "§cBitte benutze §e/msg <Name> <Nachricht>");
                }

                if (args.length >= 2) {
                    String name = args[0];
                    final Player targetPlayer = Bukkit.getServer().getPlayer(name);
                    String msg = "";
                    for (int i = 1; i < args.length; i++) {
                        msg = msg + " " + args[i];
                    }

                    if (targetPlayer != null) {
                        player.sendMessage(Community.getPrefix() + "§cDu §8--> §4" + targetPlayer.getName() + " §8|§e" + msg);
                        targetPlayer.sendMessage(Community.getPrefix() + "§4" + player.getName() + " §8--> §cDir §8|§e" + msg);
                    } else {
                        player.sendMessage(Community.getPrefix() + "§cDer Spieler ist nicht online");
                    }
                }
            } else {
                player.sendMessage(Community.getPrefix() + Community.getNoPermission());
            }
        }
        return false;
    }
}
