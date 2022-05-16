package net.ehrenlos.befizzi.commands;

import net.ehrenlos.befizzi.Community;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class invseeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Community.getPrefix() + "§4Du musst ein Spieler sein");
            return false;
        }

        final Player player = (Player) sender;

        if (cmd.getName().equalsIgnoreCase("invsee")) {
            if (player.hasPermission("community.command.invsee")) {
                if (args.length == 0) {
                    player.sendMessage(Community.getPrefix() + "§cBitte benutze §e/invsee <Spieler>");
                }

                if (args.length == 1) {
                    Player target = Bukkit.getServer().getPlayer(args[0]);
                    if (target != null) {
                        player.openInventory(target.getInventory());
                    }
                }
            } else {
                player.sendMessage(Community.getPrefix() + Community.getNoPermission());
            }
        }
        return false;
    }
}
