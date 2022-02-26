package net.ehrenlos.lobbysystem.commands;

import net.ehrenlos.lobbysystem.LobbySystem;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class flyCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }

        final Player player = (Player) sender;
        if (player.hasPermission("lobbysystem.fly.use")) {
            if (cmd.getName().equalsIgnoreCase("fly")) {
                if (player.getAllowFlight()) {
                    player.setAllowFlight(false);
                    player.sendMessage(LobbySystem.getPrefix() + "§cDu hast den Flugmodus deaktiviert");
                    return true;
                }
                if (!player.getAllowFlight()) {
                    player.setAllowFlight(true);
                    player.sendMessage(LobbySystem.getPrefix() + "§2Du hast den Flugmodus aktiviert");
                    return true;
                }

            }
        } else {
            player.sendMessage(LobbySystem.getNoPerms());
        }
        return false;
    }
}
