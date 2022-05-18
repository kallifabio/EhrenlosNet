package net.ehrenlos.befizzi.commands;

import net.ehrenlos.befizzi.Community;
import net.ehrenlos.befizzi.manager.StatusManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class setstatusCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Community.getPrefix() + "§4Du musst ein Spieler sein");
            return false;
        }

        final Player player = (Player) sender;

        if (cmd.getName().equalsIgnoreCase("setstatus")) {
            if (player.hasPermission("community.commands.setstatus")) {
                if (args.length < 2) {
                    player.sendMessage(Community.getPrefix() + "§cBitte benutze §e/setstatus <Home> <true (open) | false (private)>");
                }

                if (args.length == 2) {
                    StatusManager.setStatus(player.getName(), args[0], Boolean.parseBoolean(args[1]));
                    player.sendMessage(Community.getPrefix() + "§2Du hast dein Home §7" + args[0] + " §2auf §7" + args[1] + " §2gestellt");
                }
            } else {
                player.sendMessage(Community.getPrefix() + Community.getNoPermission());
            }
        }
        return false;
    }
}
