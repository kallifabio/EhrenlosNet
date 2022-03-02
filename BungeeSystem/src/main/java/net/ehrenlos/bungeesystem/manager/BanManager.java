package net.ehrenlos.bungeesystem.manager;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BanManager {

    static PreparedStatement statement;
    static ResultSet result;

    public static boolean playerExistsBanned(String uuid) {
        try {
            result = MySQLManager.getResult("SELECT * FROM BannedPlayers WHERE UUID= '" + uuid + "'");
            if (result.next())
                return (result.getString("UUID") != null);
            result.close();
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void ban(String team, String uuid, String username, String reason, long seconds) {
        long end = 0L;
        if (seconds == -1L) {
            end = -1L;
        } else {
            long current = System.currentTimeMillis();
            long millis = seconds * 1000L;
            end = current + millis;
        }
        removeban(uuid);
        MySQLManager.getStatement("INSERT INTO BannedPlayers (Banned, Staff, Player, UUID, End, Reason) VALUES ('1','" + team + "','" +
                username + "','" + uuid + "','" + end + "','" + reason + "')");
        ProxiedPlayer target = ProxyServer.getInstance().getPlayer(username);
        if (target != null) {
            String message = "§bkallifabio.net %newline% §cDu wurdest vom Netzwerk §egebannt! %newline% §7Dauer §8» §e%time% %newline% §7Grund §8» §e%reason% %newline% §7Gebannt von §8» §e%staff%";
            message = message.replaceAll("%newline%", "\n");
            message = message.replaceAll("%time%", getRemainingTime(getUUID(target.getName())));
            message = message.replaceAll("%reason%", getReason(getUUID(target.getName())));
            message = message.replaceAll("%staff%", getTeamBanned(getUUID(target.getName())));
            target.disconnect(message);
        }
    }

    public static void unban(String uuid, String name) {
        MySQLManager.getStatement("DELETE FROM BannedPlayers WHERE UUID='" + uuid + "'");
        MySQLManager.getStatement("INSERT INTO BannedPlayers (Banned, Staff, Player, UUID, End, Reason) VALUES ('0','0','" + name +
                "','" + uuid + "','-2','0')");
    }

    public static void removeban(String uuid) {
        MySQLManager.getStatement("DELETE FROM BannedPlayers WHERE UUID='" + uuid + "'");
    }

    public static Boolean isBanned(String uuid, String name) {
        Boolean banned = null;
        try {
            result = MySQLManager.getResult("SELECT * FROM BannedPlayers WHERE UUID= '" + uuid + "'");
            if (result.next() && Integer.valueOf(result.getInt("Banned")).intValue() == 0) {
                banned = Boolean.valueOf(false);
            } else {
                banned = Boolean.valueOf(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return banned;
    }

    public static boolean isBanned(String uuid) {
        result = MySQLManager.getResult("SELECT End FROM BannedPlayers WHERE UUID='" + uuid + "'");
        try {
            return result.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String getUUID(String name) {
        result = MySQLManager.getResult("SELECT * FROM BannedPlayers WHERE Player='" + name + "'");
        try {
            if (result.next())
                return result.getString("UUID");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "No UUID";
    }

    public static String getName(String uuid) {
        result = MySQLManager.getResult("SELECT * FROM BannedPlayers WHERE UUID='" + uuid + "'");
        try {
            if (result.next())
                return result.getString("Player");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "No Name";
    }

    public static String getTeamBanned(String name) {
        result = MySQLManager.getResult("SELECT * FROM BannedPlayers WHERE UUID='" + name + "'");
        try {
            if (result.next())
                return result.getString("Staff");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "No Team Ban";
    }

    public static String getReason(String uuid) {
        result = MySQLManager.getResult("SELECT * FROM BannedPlayers WHERE UUID='" + uuid + "'");
        try {
            if (result.next())
                return result.getString("Reason");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "No Reason";
    }

    public static Long getEnd(String uuid) {
        result = MySQLManager.getResult("SELECT * FROM BannedPlayers WHERE UUID='" + uuid + "'");
        try {
            if (result.next())
                return Long.valueOf(result.getLong("End"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getRemainingTime(String uuid) {
        long current = System.currentTimeMillis();
        long end = getEnd(uuid).longValue();
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
