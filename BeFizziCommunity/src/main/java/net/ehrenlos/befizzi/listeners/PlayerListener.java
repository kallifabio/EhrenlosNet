package net.ehrenlos.befizzi.listeners;

import net.ehrenlos.befizzi.Community;
import net.ehrenlos.befizzi.manager.MySQLManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class PlayerListener implements Listener {

    static PreparedStatement statement;
    static ResultSet result;

    /**
     * Get Time from Player
     */

    //<editor-fold defaultstate="collapsed" desc="getTime">
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
    //</editor-fold>

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        event.setJoinMessage(Community.getPrefix() + "§2Der Spieler §e" + player.getName() + " §2hat den Server betreten");

        player.sendTitle("§7Willkommen bei der §aBeFizzi Community§e", player.getName(), 25, 30, 25);

        addScoreBoard(player);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        event.setQuitMessage(Community.getPrefix() + "§cDer Spieler §e" + player.getName() + " §chat den Server verlassen");
    }

    private void addScoreBoard(Player player) {
        int time = getTime(player.getUniqueId());
        boolean isHour = (time >= 60);

        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("scoreboard", "dummy");

        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName("§a§lBeFizzi");

        objective.getScore("§8§m---------------").setScore(10);
        objective.getScore("§eDeine Spielzeit").setScore(0);
        if (isHour) {
            time /= 60;
            objective.getScore("§8» §6" + time + " Stunde(n)").setScore(-1);
        } else {
            objective.getScore("§8» §6" + time + " Minute(n)").setScore(-1);
        }

        for (Player all : Bukkit.getOnlinePlayers()) {
            player.setScoreboard(scoreboard);
        }
    }
}
