package net.ehrenlos.lobbysystem.commands;

import net.ehrenlos.lobbysystem.LobbySystem;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class clearchatCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(LobbySystem.getPrefix() + "§4Du musst ein Spieler sein");
            return false;
        }

        final Player player = (Player) sender;
        if (!player.hasPermission("lobbysystem.command.clearchat")) {
            player.sendMessage(LobbySystem.getNoPerms());
        } else {
            for (int i = 0; i < 105; ++i) {
                for (Player all : Bukkit.getOnlinePlayers()) {
                    if (!all.hasPermission("lobbysystem.clearchat.bypass")) {
                        all.sendMessage("");
                    }
                }
            }
            Bukkit.broadcastMessage(LobbySystem.getPrefix() + "§cDer Chat wurde von §9" + player.getName() + " §cgeleert");
            return true;
        }
        return false;
    }
}
