package net.ehrenlos.bungeesystem.manager;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MuteManager {

    public static boolean playerExistsMuted(final String uuid) {
        try {
            final ResultSet rs = MySQLManager.getResults("SELECT * FROM MutedPlayers WHERE UUID= '" + uuid + "'");
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

    public static void mute(final String team, final String uuid, final String player, final String reason, final long seconds) {
        long end = 0L;
        if (seconds == -1L) {
            end = -1L;
        } else {
            final long current = System.currentTimeMillis();
            final long millis = seconds * 1000L;
            end = current + millis;
        }
        removemute(uuid);
        MySQLManager.update("INSERT INTO MutedPlayers (Muted, Staff, Player, UUID, End, Reason) VALUES ('1','" + team + "','" + player + "','" + uuid + "','" + end + "','" + reason + "')");
        final ProxiedPlayer target = ProxyServer.getInstance().getPlayer(player);
        if (target != null) {
            target.sendMessage("§6§lEhrenlosNet");
            target.sendMessage("§cDu wurdest auf dem Netzwerk §egemutet!");
            target.sendMessage("§7Dauer §8» §e%time%".replaceAll("%time%", getRemainingTime(target.getUniqueId().toString())));
            target.sendMessage("§7Grund §8» §e%reason%".replaceAll("%reason%", getReason(target.getUniqueId().toString())));
            target.sendMessage("§7Gemutet von §8» §e%staff%".replaceAll("%staff%", getTeamMuted(getUUID(target.getName()))));
            target.sendMessage("");
        }
    }

    public static void unmute(final String uuid, final String player) {
        MySQLManager.update("DELETE FROM MutedPlayers WHERE UUID='" + uuid + "'");
        MySQLManager.update("INSERT INTO MutedPlayers (Muted, Staff, Player, UUID, End, Reason) VALUES ('0','0','" + player + "','" + uuid + "','-2','0')");
    }

    public static void removemute(final String uuid) {
        MySQLManager.update("DELETE FROM MutedPlayers WHERE UUID='" + uuid + "'");
    }

    public static Boolean isMuted(final String uuid, final String player) {
        Boolean muted = null;
        try {
            final ResultSet rs = MySQLManager.getResults("SELECT * FROM MutedPlayers WHERE UUID= '" + uuid + "'");
            if (rs.next() && rs.getInt("Muted") == 0) {
                muted = false;
            } else {
                muted = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return muted;
    }

    public static boolean isMuted(final String uuid) {
        final ResultSet rs = MySQLManager.getResults("SELECT End FROM MutedPlayers WHERE UUID='" + uuid + "'");
        try {
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String getUUID(final String player) {
        final ResultSet rs = MySQLManager.getResults("SELECT * FROM MutedPlayers WHERE Player='" + player + "'");
        try {
            if (rs.next()) {
                return rs.getString("UUID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "No UUID";
    }

    public static String getTeamMuted(final String name) {
        final ResultSet rs = MySQLManager.getResults("SELECT * FROM MutedPlayers WHERE UUID='" + name + "'");
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
        final ResultSet rs = MySQLManager.getResults("SELECT * FROM MutedPlayers WHERE UUID='" + uuid + "'");
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
        final ResultSet rs = MySQLManager.getResults("SELECT * FROM MutedPlayers WHERE UUID='" + uuid + "'");
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
            return "§ePERMANENT";
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
        return "§e" + week + " " + "Woche(n)" + " " + days + " " + "Tag(e)" + " " + hours + " " + "Stunde(n)" + " " + minuts + " " + "Minute(n)" + " " + seconds + " " + "Sekunde(n)";
    }
}
