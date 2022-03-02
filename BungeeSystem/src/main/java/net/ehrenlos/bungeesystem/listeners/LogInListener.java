package net.ehrenlos.bungeesystem.listeners;

import net.ehrenlos.bungeesystem.BungeeSystem;
import net.ehrenlos.bungeesystem.manager.ServerInfoManager;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.event.LoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class LogInListener implements Listener {

    @EventHandler
    public void onLogin(LoginEvent event) {
        BungeeSystem.getServerInfoManager();
        if (!ServerInfoManager.getRegistredUsers().contains(event.getConnection().getUniqueId())) {
            BungeeSystem.getServerInfoManager();
            ServerInfoManager.getRegistredUsers().add(event.getConnection().getUniqueId());
        }

        BungeeSystem.getServerInfoManager();
        if (ProxyServer.getInstance().getPlayers().size() + 1 > ServerInfoManager.getUserRecord()) {
            BungeeSystem.getServerInfoManager();
            BungeeSystem.getServerInfoManager();
            ServerInfoManager.setUserRecord(ServerInfoManager.getUserRecord() + 1);
        }

    }
}
