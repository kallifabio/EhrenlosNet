package net.ehrenlos.befizzi.commands;

import net.ehrenlos.befizzi.Community;
import net.ehrenlos.befizzi.manager.LocationManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class homeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Community.getPrefix() + "§4Du musst ein Spieler sein");
            return false;
        }

        final Player player = (Player) sender;

        if (cmd.getName().equalsIgnoreCase("home")) {
            if (player.hasPermission("community.command.home")) {
                if (args.length == 0) {
                    player.sendMessage(Community.getPrefix() + "§cBitte benutze §e/home <Home>");
                }

                if (args.length == 1) {
                    player.teleport(LocationManager.getLocation(player.getName(), args[0]));
                    player.sendMessage(Community.getPrefix() + "§2Du hast dich zu deinem Home §7" + args[0] + " §2teleportiert");
                }
            } else {
                player.sendMessage(Community.getPrefix() + Community.getNoPermission());
            }
        }
        return false;
    }
}
