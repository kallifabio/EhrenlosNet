package net.ehrenlos.lobbysystem.commands;

import net.ehrenlos.lobbysystem.LobbySystem;
import org.bukkit.Bukkit;
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
        if (player.hasPermission("lobbysystem.command.fly")) {
            if (cmd.getName().equalsIgnoreCase("fly")) {
                if (args.length == 0) {
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
                if (args.length == 1) {
                    String name = args[0];
                    Player target = Bukkit.getServer().getPlayer(name);
                    if (target != null) {
                        if (target.getAllowFlight()) {
                            target.setAllowFlight(false);
                            target.sendMessage(LobbySystem.getPrefix() + "§e" + player.getName() + " §chat für dich den Flugmodus deaktiviert");
                            player.sendMessage(LobbySystem.getPrefix() + "§cDu hast den Flugmodus für §e" + target.getName() + " §cdeaktiviert");
                            return true;
                        }
                        if (!target.getAllowFlight()) {
                            target.setAllowFlight(true);
                            target.sendMessage(LobbySystem.getPrefix() + "§e" + player.getName() + " §2hat für dich den Flugmodus aktiviert");
                            player.sendMessage(LobbySystem.getPrefix() + "§2Du hast den Flugmodus für §e" + target.getName() + " §2aktiviert");
                            return true;
                        }
                    } else {
                        player.sendMessage(LobbySystem.getPrefix() + "§4Der Spieler ist nicht online");
                    }
                }
            }
        } else {
            player.sendMessage(LobbySystem.getPrefix() + LobbySystem.getNoPerms());
        }
        return false;
    }
}
