package net.ehrenlos.lobbysystem.commands;

import net.ehrenlos.lobbysystem.LobbySystem;
import net.ehrenlos.lobbysystem.manager.LocationManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class setlocationCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(LobbySystem.getPrefix() + "Du musst ein Spieler sein");
            return false;
        }

        final Player player = (Player) sender;
        if (player.hasPermission("LobbySystem.command.setlocation")) {
            if (args.length == 0) {
                player.sendMessage("§cBitte benutze §6/setlocation <Spawn, Team, JumpAndRuns>");
            } else if (args.length == 1) {
                if (args[0].equalsIgnoreCase("spawn")) {
                    player.sendMessage(LobbySystem.getPrefix() + "§aDu hast den Spawn §9Spawn §agesetzt");
                    LocationManager.setLocation("Spawn", player.getLocation());
                } else if (args[0].equalsIgnoreCase("team")) {
                    player.sendMessage(LobbySystem.getPrefix() + "§aDu hast den Spawn §9Team §agesetzt");
                    LocationManager.setLocation("Team", player.getLocation());
                } else if (args[0].equalsIgnoreCase("jumpandruns")) {
                    player.sendMessage(LobbySystem.getPrefix() + "§aDu hast den Spawn §9JumpAndRuns §agesetzt");
                    LocationManager.setLocation("JumpAndRuns", player.getLocation());
                } else {
                    player.sendMessage("§cBitte benutze §6/setlocation <Spawn, Team, JumpAndRuns>");
                }
            }
        } else {
            player.sendMessage(LobbySystem.getPrefix() + LobbySystem.getNoPerms());
        }
        return false;
    }
}
