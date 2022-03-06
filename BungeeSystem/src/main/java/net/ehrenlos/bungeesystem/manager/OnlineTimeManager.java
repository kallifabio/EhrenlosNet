package net.ehrenlos.bungeesystem.manager;

import net.ehrenlos.bungeesystem.BungeeSystem;
import net.ehrenlos.bungeesystem.utils.UUIDFetcher;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class OnlineTimeManager {

    public static void startOnlineTimeTracking() {
        ProxyServer.getInstance().getScheduler().schedule(BungeeSystem.getInstance(), () -> {
            for (final ProxiedPlayer players : ProxyServer.getInstance().getPlayers()) {
                if (players.getServer().getInfo().getName().contains("Lobby") || players.getServer().getInfo().getName().contains("Hub") || players.getServer().getInfo().getName().contains("lobby") || players.getServer().getInfo().getName().contains("hub")) {
                    return;
                }
                OnlineTimeManager.updateTime(players.getUniqueId());
            }
        }, 1L, 1L, TimeUnit.MINUTES);
    }


    public static void updateTime(final UUID uuid) {
        if (isRegistered(uuid)) {
            MySQLManager.update("UPDATE OnlineTime SET Time = '" + (getTime(uuid) + 1) + "' WHERE UUID = '" + uuid.toString() + "'");
        } else {
            MySQLManager.update("INSERT INTO OnlineTime (UUID, Time) VALUES ('" + uuid.toString() + "', '1')");
        }
    }

    public static int getTime(final UUID uuid) {
        if (MySQLManager.isConnected()) {
            try {
                final ResultSet rs = MySQLManager.getResult("SELECT * FROM OnlineTime WHERE UUID = '" + uuid.toString() + "'");
                if (rs.next()) {
                    return rs.getInt("Time");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    private static boolean isRegistered(final UUID uuid) {
        if (MySQLManager.isConnected()) {
            try {
                final ResultSet rs = MySQLManager.getResult("SELECT * FROM OnlineTime WHERE UUID = '" + uuid.toString() + "'");
                if (rs.next()) {
                    return rs.getInt("Time") != 0;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static void sendTop10(final ProxiedPlayer player) {
        try {
            final ResultSet rs = MySQLManager.getResult("SELECT * FROM OnlineTime ORDER BY Time DESC LIMIT 10");
            int i = 1;
            player.sendMessage("MessageHandler.onlinetime_top10_header");
            while (rs.next()) {
                final String uuid = rs.getString("UUID");
                int time = rs.getInt("Time");
                final boolean isHour = time >= 60;
                if (isHour) {
                    time /= 60;
                    player.sendMessage("MessageHandler.onlinetime_top10_lines".replaceAll("%rank%", String.valueOf(i)).replaceAll("%target%", UUIDFetcher.getName(UUID.fromString(uuid))).replaceAll("%time%", String.valueOf(time)));
                    ++i;
                }
            }
            rs.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
