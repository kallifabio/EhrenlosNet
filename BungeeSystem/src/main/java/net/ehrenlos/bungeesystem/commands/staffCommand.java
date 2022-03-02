package net.ehrenlos.bungeesystem.commands;

import net.ehrenlos.bungeesystem.BungeeSystem;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class staffCommand extends Command {

    public staffCommand(String name) {
        super(name);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            sender.sendMessage(BungeeSystem.getPrefix() + "§4Du musst ein Spieler sein");
            return;
        }

        ProxiedPlayer player = (ProxiedPlayer) sender;
        if (player.hasPermission("bungeesystem.staff") || player.hasPermission("bungeesystem.*")) {
            if (args.length == 0) {
                player.sendMessage("§8§m-------§c Team §8§m-------");
                for (ProxiedPlayer players : ProxyServer.getInstance().getPlayers()) {
                    if (player.hasPermission("lobbysystem.rang.admin")) {
                        player.sendMessage("%prefix% §8| §7%player% §8» §e%server%".replaceAll("%player%", players.getName()).replaceAll("%server%", players.getServer().getInfo().getName()).replaceAll("%prefix%", "§4Admin"));
                    } else if (player.hasPermission("lobbysystem.rang.developer")) {
                        player.sendMessage("%prefix% §8| §7%player% §8» §e%server%".replaceAll("%player%", players.getName()).replaceAll("%server%", players.getServer().getInfo().getName()).replaceAll("%prefix%", "§bDeveloper"));
                    } else if (player.hasPermission("lobbysystem.rang.moderator")) {
                        player.sendMessage("%prefix% §8| §7%player% §8» §e%server%".replaceAll("%player%", players.getName()).replaceAll("%server%", players.getServer().getInfo().getName()).replaceAll("%prefix%", "§cModerator"));
                    } else if (player.hasPermission("lobbysystem.rang.builder")) {
                        player.sendMessage("%prefix% §8| §7%player% §8» §e%server%".replaceAll("%player%", players.getName()).replaceAll("%server%", players.getServer().getInfo().getName()).replaceAll("%prefix%", "§2Builder"));
                    } else if (player.hasPermission("lobbysystem.rang.supporter")) {
                        player.sendMessage("%prefix% §8| §7%player% §8» §e%server%".replaceAll("%player%", players.getName()).replaceAll("%server%", players.getServer().getInfo().getName()).replaceAll("%prefix%", "§9Supporter"));
                    }
                }
                player.sendMessage("§8§m-------§c Team §8§m-------");
            } else {
                player.sendMessage(BungeeSystem.getPrefix() + "§cBenutze: §e%command%".replaceAll("%command%", "/staff"));
            }
        } else {
            player.sendMessage(BungeeSystem.getPrefix() + BungeeSystem.getNoPerms());
        }
    }
}

