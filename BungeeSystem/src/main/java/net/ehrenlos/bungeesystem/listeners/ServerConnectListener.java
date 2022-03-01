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
                    players.sendMessage("§7Das Mitglied §e%player% §7ist jetzt §aonline§7!".replaceAll("%player%",
                            player.getName()).replaceAll("%prefix%", "Admin"));

                }
            }

        }
    }
}
