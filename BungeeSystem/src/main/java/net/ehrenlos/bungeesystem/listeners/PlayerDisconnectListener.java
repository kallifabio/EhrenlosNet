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
                if ((players.hasPermission("bungeesystem.staffnotify") || players.hasPermission("bungeesystem.*")))
                    players.sendMessage("§7Das Mitglied §e%player% §7ist jetzt §coffline§7!".replaceAll("%player%", player.getName()).replaceAll("%prefix%", "Admin"));
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
