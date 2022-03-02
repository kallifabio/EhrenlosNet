package net.ehrenlos.bungeesystem.listeners;

import net.ehrenlos.bungeesystem.BungeeSystem;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class PlayerDisconnectListener implements Listener {

    @EventHandler
    public void onPlayerDisconnect(PlayerDisconnectEvent event) {
        ProxiedPlayer player = event.getPlayer();
        if (player.hasPermission("bungeesystem.staffnotify") || player.hasPermission("bungeesystem.*")) {
            for (ProxiedPlayer players : ProxyServer.getInstance().getPlayers()) {
                if (player.hasPermission("lobbysystem.rang.admin")) {
                    players.sendMessage(BungeeSystem.getTeamPrefix() + "§7Das Mitglied %prefix% §8| §e%player% §7ist jetzt §coffline§7!".replaceAll("%player%", player.getName()).replaceAll("%prefix%", "§4Admin"));
                } else if (player.hasPermission("lobbysystem.rang.developer")) {
                    players.sendMessage(BungeeSystem.getTeamPrefix() + "§7Das Mitglied %prefix% §8| §e%player% §7ist jetzt §coffline§7!".replaceAll("%player%", player.getName()).replaceAll("%prefix%", "§bDeveloper"));
                } else if (player.hasPermission("lobbysystem.rang.moderator")) {
                    players.sendMessage(BungeeSystem.getTeamPrefix() + "§7Das Mitglied %prefix% §8| §e%player% §7ist jetzt §coffline§7!".replaceAll("%player%", player.getName()).replaceAll("%prefix%", "§cModerator"));
                } else if (player.hasPermission("lobbysystem.rang.builder")) {
                    players.sendMessage(BungeeSystem.getTeamPrefix() + "§7Das Mitglied %prefix% §8| §e%player% §7ist jetzt §coffline§7!".replaceAll("%player%", player.getName()).replaceAll("%prefix%", "§2Builder"));
                } else if (player.hasPermission("lobbysystem.rang.supporter")) {
                    players.sendMessage(BungeeSystem.getTeamPrefix() + "§7Das Mitglied %prefix% §8| §e%player% §7ist jetzt §coffline§7!".replaceAll("%player%", player.getName()).replaceAll("%prefix%", "§9Supporter"));
                }
            }

            if (BungeeSystem.staff.contains(player)) {
                BungeeSystem.staff.remove(player);
            }

            if (BungeeSystem.notify.contains(player)) {
                BungeeSystem.notify.remove(player);
            }
        }
    }
}
