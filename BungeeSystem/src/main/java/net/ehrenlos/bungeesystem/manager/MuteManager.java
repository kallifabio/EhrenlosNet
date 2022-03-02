package net.ehrenlos.bungeesystem.manager;

import net.ehrenlos.bungeesystem.BungeeSystem;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MuteManager {

    static PreparedStatement statement;
    static ResultSet result;

    public static boolean playerExistsMuted(String uuid) {
        try {
            result = MySQLManager.getResult("SELECT * FROM MutedPlayers WHERE UUID= '" + uuid + "'");
            if (result.next())
                return (result.getString("UUID") != null);
            result.close();
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void mute(String team, String uuid, String player, String reason, long seconds) {
        long end = 0L;
        if (seconds == -1L) {
            end = -1L;
        } else {
            long current = System.currentTimeMillis();
            long millis = seconds * 1000L;
            end = current + millis;
        }
        removemute(uuid);
        MySQLManager.getStatement("INSERT INTO MutedPlayers (Muted, Staff, Player, UUID, End, Reason) VALUES ('1','" + team + "','" +
                player + "','" + uuid + "','" + end + "','" + reason + "')");
        ProxiedPlayer target = ProxyServer.getInstance().getPlayer(player);
        if (target != null) {
            target.sendMessage("");
            target.sendMessage("§bkallifabio.net");
            target.sendMessage("§cDu wurdest auf dem Netzwerk §egemutet!");
            target.sendMessage("§7Dauer §8» §e%time%".replaceAll("%time%",
                    getRemainingTime(target.getUniqueId().toString())));
            target.sendMessage("§7Grund §8» §e%reason%".replaceAll("%reason%",
                    getReason(target.getUniqueId().toString())));
            target.sendMessage("§7Gemutet von §8» §e%staff%".replaceAll("%staff%",
                    getTeamMuted(getUUID(target.getName()))));
            target.sendMessage("");
        }
    }

    public static void unmute(String uuid, String player) {
        MySQLManager.getStatement("DELETE FROM MutedPlayers WHERE UUID='" + uuid + "'");
        MySQLManager.getStatement("INSERT INTO MutedPlayers (Muted, Staff, Player, UUID, End, Reason) VALUES ('0','0','" + player +
                "','" + uuid + "','-2','0')");
    }

    public static void removemute(String uuid) {
        MySQLManager.getStatement("DELETE FROM MutedPlayers WHERE UUID='" + uuid + "'");
    }

    public static Boolean isMuted(String uuid, String player) {
        Boolean muted = null;
        try {
            result = MySQLManager.getResult("SELECT * FROM MutedPlayers WHERE UUID= '" + uuid + "'");
            if (result.next() && Integer.valueOf(result.getInt("Muted")).intValue() == 0) {
                muted = Boolean.valueOf(false);
            } else {
                muted = Boolean.valueOf(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return muted;
    }

    public static boolean isMuted(String uuid) {
        result = MySQLManager.getResult("SELECT End FROM MutedPlayers WHERE UUID='" + uuid + "'");
        try {
            return result.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String getUUID(String player) {
        result = MySQLManager.getResult("SELECT * FROM MutedPlayers WHERE Player='" + player + "'");
        try {
            if (result.next())
                return result.getString("UUID");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "No UUID";
    }

    public static String getTeamMuted(String name) {
        result = MySQLManager.getResult("SELECT * FROM MutedPlayers WHERE UUID='" + name + "'");
        try {
            if (result.next())
                return result.getString("Staff");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getReason(String uuid) {
        result = MySQLManager.getResult("SELECT * FROM MutedPlayers WHERE UUID='" + uuid + "'");
        try {
            if (result.next())
                return result.getString("Reason");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static Long getEnd(String uuid) {
        result = MySQLManager.getResult("SELECT * FROM MutedPlayers WHERE UUID='" + uuid + "'");
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
