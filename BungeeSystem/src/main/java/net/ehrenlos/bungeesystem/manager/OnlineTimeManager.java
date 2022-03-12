package net.ehrenlos.bungeesystem.manager;

import net.ehrenlos.bungeesystem.BungeeSystem;
import net.ehrenlos.bungeesystem.utils.UUIDFetcher;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class OnlineTimeManager {

    static PreparedStatement statement;
    static ResultSet result;

    public static void startOnlineTimeTracking() {
        ProxyServer.getInstance().getScheduler().schedule(BungeeSystem.getInstance(), () -> {
            for (final ProxiedPlayer players : ProxyServer.getInstance().getPlayers()) {
                if (players.getServer().getInfo().getName().contains("lobby") || players.getServer().getInfo().getName().contains("hub")) {
                    return;
                }
                updateTime(players.getUniqueId());

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
                result = MySQLManager.getResult("SELECT * FROM OnlineTime WHERE UUID = '" + uuid.toString() + "'");
                if (result.next()) {
                    return result.getInt("Time");
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
                result = MySQLManager.getResult("SELECT * FROM OnlineTime WHERE UUID = '" + uuid.toString() + "'");
                if (result.next()) {
                    return result.getInt("Time") != 0;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static void sendTop10(final ProxiedPlayer player) {
        try {
            result = MySQLManager.getResult("SELECT * FROM OnlineTime ORDER BY Time DESC LIMIT 10");
            int i = 1;
            player.sendMessage(BungeeSystem.getPrefix() + "§7Onlinezeit §8» §eTop10");
            while (result.next()) {
                final String uuid = result.getString("UUID");
                int time = result.getInt("Time");
                final boolean isHour = time >= 60;
                if (isHour) {
                    time /= 60;
                    player.sendMessage(BungeeSystem.getPrefix() + "§f■ §e#%rank% §8» §7%target% §8» §e%time% Stunde(n)".replaceAll("%rank%", String.valueOf(i)).replaceAll("%target%", UUIDFetcher.getName(UUID.fromString(uuid))).replaceAll("%time%", String.valueOf(time)));
                    ++i;
                }
            }
            result.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
