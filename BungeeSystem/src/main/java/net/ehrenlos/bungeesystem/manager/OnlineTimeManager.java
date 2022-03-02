package net.ehrenlos.bungeesystem.manager;

import net.ehrenlos.bungeesystem.BungeeSystem;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Plugin;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

public class OnlineTimeManager {

    static PreparedStatement statement;
    static ResultSet result;

    public static void startOnlineTimeTracking() {
        ProxyServer.getInstance().getScheduler().schedule(BungeeSystem.getInstance(), () -> {
            for (ProxiedPlayer players : ProxyServer.getInstance().getPlayers()) {
                if (players.getServer().getInfo().getName().contains("Lobby") || players.getServer().getInfo().getName().contains("Hub") || players.getServer().getInfo().getName().contains("lobby") || players.getServer().getInfo().getName().contains("hub")) {
                    return;
                }
                updateTime(players);
                continue;
            }
        }, 1L, 1L, TimeUnit.MINUTES);
    }

    public static void updateTime(ProxiedPlayer player) {
        if (isRegistered(player)) {
            MySQLManager.getStatement("UPDATE OnlineTime SET Time = '" + (getTime(player) + 1) + "' WHERE Player = '" + player + "'");
        } else {
            MySQLManager.getStatement("INSERT INTO OnlineTime (Player, Time) VALUES ('" + player + "', '1')");
        }
    }

    public static int getTime(ProxiedPlayer player) {
        if (MySQLManager.isConnected())
            try {
                result = MySQLManager.getResult("SELECT * FROM OnlineTime WHERE Player = '" + player.getName() + "'");
                if (result.next())
                    return result.getInt("Time");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return 0;
    }

    private static boolean isRegistered(ProxiedPlayer player) {
        if (MySQLManager.isConnected())
            try {
                result = MySQLManager.getResult("SELECT * FROM OnlineTime WHERE Player = '" + player.getName() + "'");
                if (result.next())
                    return (result.getInt("Time") != 0);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return false;
    }

    public static void sendTop10(ProxiedPlayer player) {
        try {
            result = MySQLManager.getResult("SELECT * FROM OnlineTime ORDER BY Time DESC LIMIT 10");
            int i = 1;
            player.sendMessage(BungeeSystem.getPrefix() + "§7Onlinezeit §8» §eTop10");
            while (result.next()) {
                String name = result.getString("Player");
                int time = result.getInt("Time");
                boolean isHour = (time >= 60);
                if (isHour) {
                    time /= 60;
                    player.sendMessage(BungeeSystem.getPrefix() + "§f– §e#%rank% §8» §7%target% §8» §e%time% Stunde(n)".replaceAll("%rank%", String.valueOf(i))
                            .replaceAll("%target%", name)
                            .replaceAll("%time%", String.valueOf(time)));
                    i++;
                }
            }
            result.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
