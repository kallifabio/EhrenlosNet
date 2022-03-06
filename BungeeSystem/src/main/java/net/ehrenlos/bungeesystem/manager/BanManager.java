package net.ehrenlos.bungeesystem.manager;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BanManager {

    static PreparedStatement statement;
    static ResultSet result;

    public static boolean playerExistsBanned(String name) {
        try {
            result = MySQLManager.getResults("SELECT * FROM BannedPlayers WHERE Player = '" + name + "'");
            if (result.next())
                return (result.getString("Player") != null);
            result.close();
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void ban(final String team, final String uuid, final String username, final String reason, final long seconds) {
        long end = 0L;
        if (seconds == -1L) {
            end = -1L;
        } else {
            long current = System.currentTimeMillis();
            long millis = seconds * 1000L;
            end = current + millis;
        }
        removeban(uuid);
        MySQLManager.getStatement("INSERT INTO BannedPlayers (Banned, Staff, Player, End, Reason) VALUES ('1','" + team + "','" + username + "','" + end + "','" + reason + "')");
        ProxiedPlayer target = ProxyServer.getInstance().getPlayer(username);
        if (target != null) {
            String message = "§6§lEhrenlosNet %newline% §cDu wurdest vom Netzwerk §egebannt! %newline% §7Dauer §8» §e%time% %newline% §7Grund §8» §e%reason% %newline% §7Gebannt von §8» §e%staff%";
            message = message.replaceAll("%newline%", "\n");
            message = message.replaceAll("%time%", getRemainingTime(target.getName()));
            message = message.replaceAll("%reason%", getReason(target.getName()));
            message = message.replaceAll("%staff%", getTeamBanned(target.getName()));
            target.disconnect(message);
        }
    }

    public static void unban(final String uuid, final String name) {
        MySQLManager.update("DELETE FROM BannedPlayers WHERE UUID='" + uuid + "'");
        MySQLManager.update("INSERT INTO BannedPlayers (Banned, Staff, Player, UUID, End, Reason) VALUES ('0','0','" + name + "','" + uuid + "','-2','0')");
    }

    public static void removeban(String name) {
        MySQLManager.getStatement("DELETE FROM BannedPlayers WHERE Player ='" + name + "'");
    }

    public static Boolean isBanned(String name, String player) {
        Boolean banned = null;
        try {
            result = MySQLManager.getResults("SELECT * FROM BannedPlayers WHERE Player = '" + name + "'");
            if (result.next() && result.getInt("Banned") == 0) {
                banned = false;
            } else {
                banned = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return banned;
    }

    public static boolean isBanned(String name) {
        result = MySQLManager.getResults("SELECT End FROM BannedPlayers WHERE Player ='" + name + "'");
        try {
            return result.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String getUUID(final String name) {
        final ResultSet rs = MySQLManager.getResults("SELECT * FROM BannedPlayers WHERE Player='" + name + "'");
        try {
            if (rs.next()) {
                return rs.getString("UUID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "No UUID";
    }

    public static String getName(String name) {
        result = MySQLManager.getResults("SELECT * FROM BannedPlayers WHERE Player ='" + name + "'");
        try {
            if (result.next())
                return result.getString("Player");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "No Name";
    }

    public static String getTeamBanned(String name) {
        result = MySQLManager.getResults("SELECT * FROM BannedPlayers WHERE Player ='" + name + "'");
        try {
            if (result.next())
                return result.getString("Staff");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "No Team Ban";
    }

    public static String getReason(String name) {
        result = MySQLManager.getResults("SELECT * FROM BannedPlayers WHERE Player ='" + name + "'");
        try {
            if (result.next())
                return result.getString("Reason");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "No Reason";
    }

    public static Long getEnd(String name) {
        result = MySQLManager.getResults("SELECT * FROM BannedPlayers WHERE Player ='" + name + "'");
        try {
            if (result.next())
                return Long.valueOf(result.getLong("End"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getRemainingTime(String name) {
        long current = System.currentTimeMillis();
        long end = getEnd(name).longValue();
        if (end == -1L)
            return "§ePERMANENT";
        long millis = end - current;
        long seconds = 0L;
        long minuts = 0L;
        long hours = 0L;
        long days = 0L;
        long week = 0L;
        while (millis > 1000L) {
            millis -= 1000L;
            seconds++;
        }
        while (seconds > 60L) {
            seconds -= 60L;
            minuts++;
        }
        while (minuts > 60L) {
            minuts -= 60L;
            hours++;
        }
        while (hours > 24L) {
            hours -= 24L;
            days++;
        }
        while (days > 7L) {
            days -= 7L;
            week++;
        }
        return "§e" + week + " " + "Woche(n)" + " " + days + " " + "Tag(e)" + " " + hours + " " + "Stunde(n)" + " " + minuts + " " + "Minute(n)" + " " + seconds + " " + "Sekunde(n)";
    }


}
