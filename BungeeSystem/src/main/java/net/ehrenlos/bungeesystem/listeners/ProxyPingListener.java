package net.ehrenlos.bungeesystem.listeners;

import net.ehrenlos.bungeesystem.BungeeSystem;
import net.ehrenlos.bungeesystem.manager.ServerInfoManager;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class ProxyPingListener implements Listener {

    @EventHandler
    public void onProxyPing(ProxyPingEvent event) {
        BungeeSystem.getServerInfoManager();
        if (!ServerInfoManager.getTotalPings().contains(event.getConnection().getAddress().getAddress().toString())) {
            BungeeSystem.getServerInfoManager();
            ServerInfoManager.getTotalPings().add(event.getConnection().getAddress().getAddress().toString());
        }
        BungeeSystem.getServerInfoManager();
        if (!ServerInfoManager.getLast60SecondsPings().contains(event.getConnection().getAddress().getAddress().toString())) {
            BungeeSystem.getServerInfoManager();
            ServerInfoManager.getLast60SecondsPings().add(event.getConnection().getAddress().getAddress().toString());
        }

    }
}
