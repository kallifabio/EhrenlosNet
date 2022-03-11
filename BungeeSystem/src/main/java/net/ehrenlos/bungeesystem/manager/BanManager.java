package net.ehrenlos.bungeesystem.manager;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BanManager {

    public static boolean playerExistsBanned(final String uuid) {
        try {
            final ResultSet rs = MySQL.getResult("SELECT * FROM BannedPlayers WHERE UUID= '" + uuid + "'");
            if (rs.next()) {
                return rs.getString("UUID") != null;
            }
            rs.close();
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
            final long current = System.currentTimeMillis();
            final long millis = seconds * 1000L;
            end = current + millis;
        }
        removeban(uuid);
        MySQL.update("INSERT INTO BannedPlayers (Banned, Staff, Player, UUID, End, Reason) VALUES ('1','" + team + "','" + username + "','" + uuid + "','" + end + "','" + reason + "')");
        final ProxiedPlayer target = ProxyServer.getInstance().getPlayer(username);
        if (target != null) {
            String message = "MessageHandler.ban_banned";
            message = message.replaceAll("%newline%", "\n");
            message = message.replaceAll("%time%", getRemainingTime(getUUID(target.getName())));
            message = message.replaceAll("%reason%", getReason(getUUID(target.getName())));
            message = message.replaceAll("%staff%", getTeamBanned(getUUID(target.getName())));
            target.disconnect(message);
        }
    }

    public static void unban(final String uuid, final String name) {
        MySQL.update("DELETE FROM BannedPlayers WHERE UUID='" + uuid + "'");
        MySQL.update("INSERT INTO BannedPlayers (Banned, Staff, Player, UUID, End, Reason) VALUES ('0','0','" + name + "','" + uuid + "','-2','0')");
    }

    public static void removeban(final String uuid) {
        MySQL.update("DELETE FROM BannedPlayers WHERE UUID='" + uuid + "'");
    }

    public static Boolean isBanned(final String uuid, final String name) {
        Boolean banned = null;
        try {
            final ResultSet rs = MySQL.getResult("SELECT * FROM BannedPlayers WHERE UUID= '" + uuid + "'");
            if (rs.next() && rs.getInt("Banned") == 0) {
                banned = false;
            } else {
                banned = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return banned;
    }

    public static boolean isBanned(final String uuid) {
        final ResultSet rs = MySQL.getResult("SELECT End FROM BannedPlayers WHERE UUID='" + uuid + "'");
        try {
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String getUUID(final String name) {
        final ResultSet rs = MySQL.getResult("SELECT * FROM BannedPlayers WHERE Player='" + name + "'");
        try {
            if (rs.next()) {
                return rs.getString("UUID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "No UUID";
    }

    public static String getName(final String uuid) {
        final ResultSet rs = MySQL.getResult("SELECT * FROM BannedPlayers WHERE UUID='" + uuid + "'");
        try {
            if (rs.next()) {
                return rs.getString("Player");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "No Name";
    }

    public static String getTeamBanned(final String name) {
        final ResultSet rs = MySQL.getResult("SELECT * FROM BannedPlayers WHERE UUID='" + name + "'");
        try {
            if (rs.next()) {
                return rs.getString("Staff");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getReason(final String uuid) {
        final ResultSet rs = MySQL.getResult("SELECT * FROM BannedPlayers WHERE UUID='" + uuid + "'");
        try {
            if (rs.next()) {
                return rs.getString("Reason");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static Long getEnd(final String uuid) {
        final ResultSet rs = MySQL.getResult("SELECT * FROM BannedPlayers WHERE UUID='" + uuid + "'");
        try {
            if (rs.next()) {
                return rs.getLong("End");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getRemainingTime(final String uuid) {
        final long current = System.currentTimeMillis();
        final long end = getEnd(uuid);
        if (end == -1L) {
            return "MessageHandler.ban_permanent_time";
        }
        long millis = end - current;
        long seconds = 0L;
        long minuts = 0L;
        long hours = 0L;
        long days = 0L;
        long week = 0L;
        while (millis > 1000L) {
            millis -= 1000L;
            ++seconds;
        }
        while (seconds > 60L) {
            seconds -= 60L;
            ++minuts;
        }
        while (minuts > 60L) {
            minuts -= 60L;
            ++hours;
        }
        while (hours > 24L) {
            hours -= 24L;
            ++days;
        }
        while (days > 7L) {
            days -= 7L;
            ++week;
        }
        return "MessageHandler.ban_color" + week + " " + "MessageHandler.ban_temporary_weeks" + " " + days + " " + "MessageHandler.ban_temporary_days" + " " + hours + " " + "MessageHandler.ban_temporary_hours" + " " + minuts + " " + "MessageHandler.ban_temporary_minutes" + " " + seconds + " " + "MessageHandler.ban_temporary_seconds";
    }


}
