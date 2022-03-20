package net.ehrenlos.jumpandrun.commands;

import net.ehrenlos.jumpandrun.JumpAndRun;
import net.ehrenlos.jumpandrun.manager.LocationManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class setupCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(JumpAndRun.getPrefix() + "§4Du musst ein Spieler sein");
            return false;
        }

        final Player player = (Player) sender;
        if (player.hasPermission("jumpandrun.command.setup")) {
            if (cmd.getName().equalsIgnoreCase("setup")) {
                if (args.length < 1) {
                    player.sendMessage(JumpAndRun.getPrefix() + "§cBenutze §6/jumpandrun help");
                }

                if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("lobbyspawn")) {
                        LocationManager.setLobbyLocation(player.getLocation());
                    } else {
                        player.sendMessage(JumpAndRun.getPrefix() + "§cBenutze §6/setup lobbyspawn");
                    }
                }

                if (args.length == 2) {
                    if (args[0].equalsIgnoreCase("create")) {
                        LocationManager.createArena(args[1]);
                    } else {
                        player.sendMessage(JumpAndRun.getPrefix() + "§cBenutze §6/setup create <MapName>");
                    }

                    if (args[0].equalsIgnoreCase("player1spawn")) {
                        LocationManager.setPlayer1Location(args[1], player.getLocation());
                    } else {
                        player.sendMessage(JumpAndRun.getPrefix() + "§cBenutze §6/setup player1spawn <MapName>");
                    }

                    if (args[0].equalsIgnoreCase("player2spawn")) {
                        LocationManager.setPlayer2Location(args[1], player.getLocation());
                    } else {
                        player.sendMessage(JumpAndRun.getPrefix() + "§cBenutze §6/setup player2spawn <MapName>");
                    }

                    if (args[0].equalsIgnoreCase("player3spawn")) {
                        LocationManager.setPlayer3Location(args[1], player.getLocation());
                    } else {
                        player.sendMessage(JumpAndRun.getPrefix() + "§cBenutze §6/setup player3spawn <MapName>");
                    }

                    if (args[0].equalsIgnoreCase("player4spawn")) {
                        LocationManager.setPlayer4Location(args[1], player.getLocation());
                    } else {
                        player.sendMessage(JumpAndRun.getPrefix() + "§cBenutze §6/setup player4spawn <MapName>");
                    }

                    if (args[0].equalsIgnoreCase("player5spawn")) {
                        LocationManager.setPlayer5Location(args[1], player.getLocation());
                    } else {
                        player.sendMessage(JumpAndRun.getPrefix() + "§cBenutze §6/setup player5spawn <MapName>");
                    }
                }

                if (args.length == 3) {
                    if (args[0].equalsIgnoreCase("builder")) {
                        LocationManager.setBuilder(args[1], args[2]);
                    } else {
                        player.sendMessage(JumpAndRun.getPrefix() + "§cBenutze §6/setup builder <MapName> <Builder>");
                    }
                }
            }
        }
        return false;
    }
}
