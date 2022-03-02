package net.ehrenlos.bungeesystem.listeners;

import net.ehrenlos.bungeesystem.BungeeSystem;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ServerConnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class ServerConnectListener implements Listener {

    @EventHandler
    public void onServerConnect(ServerConnectEvent event) {
        ProxiedPlayer player = event.getPlayer();

        if ((player.hasPermission("bungeesystem.staffnotify") || player.hasPermission("bungeesystem.*"))) {
            if (!BungeeSystem.staff.contains(player) && !BungeeSystem.notify.contains(player)) {
                BungeeSystem.staff.add(player);
                BungeeSystem.notify.add(player);
                for (ProxiedPlayer players : ProxyServer.getInstance().getPlayers()) {
                    if (player.hasPermission("lobbysystem.rang.admin")) {
                        players.sendMessage(BungeeSystem.getTeamPrefix() + "§7Das Mitglied %prefix% §8| §e%player% §7ist jetzt §aonline§7!".replaceAll("%player%", player.getName()).replaceAll("%prefix%", "§4Admin"));
                    } else if (player.hasPermission("lobbysystem.rang.developer")) {
                        players.sendMessage(BungeeSystem.getTeamPrefix() + "§7Das Mitglied %prefix% §8| §e%player% §7ist jetzt §aonline§7!".replaceAll("%player%", player.getName()).replaceAll("%prefix%", "§bDeveloper"));
                    } else if (player.hasPermission("lobbysystem.rang.moderator")) {
                        players.sendMessage(BungeeSystem.getTeamPrefix() + "§7Das Mitglied %prefix% §8| §e%player% §7ist jetzt §aonline§7!".replaceAll("%player%", player.getName()).replaceAll("%prefix%", "§cModerator"));
                    } else if (player.hasPermission("lobbysystem.rang.builder")) {
                        players.sendMessage(BungeeSystem.getTeamPrefix() + "§7Das Mitglied %prefix% §8| §e%player% §7ist jetzt §aonline§7!".replaceAll("%player%", player.getName()).replaceAll("%prefix%", "§2Builder"));
                    } else if (player.hasPermission("lobbysystem.rang.supporter")) {
                        players.sendMessage(BungeeSystem.getTeamPrefix() + "§7Das Mitglied %prefix% §8| §e%player% §7ist jetzt §aonline§7!".replaceAll("%player%", player.getName()).replaceAll("%prefix%", "§9Supporter"));
                    }
                }
            }

        }
    }
}
