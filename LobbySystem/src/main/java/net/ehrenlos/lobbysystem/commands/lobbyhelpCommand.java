package net.ehrenlos.lobbysystem.commands;

import net.ehrenlos.lobbysystem.LobbySystem;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class lobbyhelpCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(LobbySystem.getPrefix() + "§4Du musst ein Spieler sein");
            return false;
        }
        final Player player = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("lobbyhelp")) {
            if (args.length == 0) {
                if (player.hasPermission("lobbysystem.command.lobbyhelp")) {
                    player.sendMessage("§8§m-----------§7 [§6Lobby-Help§7] §8§m-------------");
                    player.sendMessage(LobbySystem.getPrefix() + "§e/fly §8- §7Setze dich in den Flugmodus");
                    player.sendMessage(LobbySystem.getPrefix() + "§e/gamemode §8- §7Ändere deinen Gamemode");
                    player.sendMessage(LobbySystem.getPrefix() + "§e/setlocation §8- §7Setze die Locations");
                    player.sendMessage(LobbySystem.getPrefix() + "§e/daily §8- §7Deine täglichen Coins");
                    player.sendMessage(LobbySystem.getPrefix() + "§e/lobbyhelp §8- §7Zeigt dir diese Hilfe hier");
                    player.sendMessage("§8§m-----------§7 [§6LobbyHelp-Help§7] §8§m-------------");
                } else {
                    player.sendMessage("§8§m-----------§7 [§6Lobby-Help§7] §8§m-------------");
                    player.sendMessage(LobbySystem.getPrefix() + "§e/daily §8- §7Deine täglichen Coins");
                    player.sendMessage(LobbySystem.getPrefix() + "§e/lobbyhelp §8- §7Zeigt dir diese Hilfe hier");
                    player.sendMessage("§8§m-----------§7 [§6LobbyHelp-Help§7] §8§m-------------");
                }
            }

            if (args.length >= 1) {
                player.sendMessage(LobbySystem.getPrefix() + "§cBitte benutze §6/lobbyhelp");
            }

        }
        return false;
    }
}
