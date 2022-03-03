package net.ehrenlos.bungeesystem.listeners;

import net.ehrenlos.bungeesystem.BungeeSystem;
import net.ehrenlos.bungeesystem.manager.BanManager;
import net.ehrenlos.bungeesystem.manager.MuteManager;
import net.ehrenlos.bungeesystem.manager.MySQLManager;
import net.ehrenlos.bungeesystem.manager.ServerInfoManager;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.event.LoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.util.UUID;

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

        String player = event.getConnection().getName();
        UUID uuid = event.getConnection().getUniqueId();
        try {
            if (!BanManager.playerExistsBanned(uuid.toString()) || !MuteManager.playerExistsMuted(uuid.toString())) {
                MySQLManager.getStatement("INSERT INTO BannedPlayers (Banned, Staff, Player, UUID, End, Reason) VALUES ('0','0','" +
                        player + "','" + uuid + "','-2','0')");
                MySQLManager.getStatement("INSERT INTO MutedPlayers (Muted, Staff, Player, UUID, End, Reason) VALUES ('0','0','" +
                        player + "','" + uuid + "','-2','0')");
            }
            if (BanManager.isBanned(uuid.toString(), player).booleanValue()) {
                long current = System.currentTimeMillis();
                long end = BanManager.getEnd(uuid.toString()).longValue();
                if (current < end || end == -1L) {
                    String message = "§bkallifabio.net %newline% §cDu wurdest vom Netzwerk §egebannt! %newline% §7Dauer §8» §e%time% %newline% §7Grund §8» §e%reason% %newline% §7Gebannt von §8» §e%staff%";
                    message = message.replaceAll("%newline%", "\n");
                    message = message.replaceAll("%time%", BanManager.getRemainingTime(BanManager.getUUID(player)));
                    message = message.replaceAll("%reason%", BanManager.getReason(BanManager.getUUID(player)));
                    message = message.replaceAll("%staff%", BanManager.getTeamBanned(BanManager.getUUID(player)));
                    event.setCancelled(true);
                    event.setCancelReason(message);
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }
}
