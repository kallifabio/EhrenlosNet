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
                if (args.length < 3) {
                    player.sendMessage(Community.getPrefix() + "§cBitte benutze §e/setstatus <Spieler> <Home> <true | false>");
                }

                if (args.length == 3) {
                    //if (Objects.equals(LocationManager.getPlayer(args[0]), player.getName())) {
                    StatusManager.setStatus(args[0], args[1], Boolean.parseBoolean(args[2]));
                    player.sendMessage(Community.getPrefix() + "§2Du hast den Home §7" + args[1] + " §2von §7" + args[0] + " §2auf §7" + args[2] + " §2gestellt");
                    //} else {
                    //player.sendMessage(Community.getPrefix() + Community.getNoHomeOwner());
                    //}
                }
            } else {
                player.sendMessage(Community.getPrefix() + Community.getNoPermission());
            }
        }
        return false;
    }
}
