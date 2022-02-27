package net.ehrenlos.lobbysystem.listeners;

import net.ehrenlos.lobbysystem.manager.ItemManager;
import net.ehrenlos.lobbysystem.manager.LocationManager;
import net.ehrenlos.lobbysystem.manager.MySQLManager;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.WeatherType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayerListener implements Listener {

    static PreparedStatement statement;
    static ResultSet result;

    /**
     * Register Player, Amount
     */

    //<editor-fold defaultstate="collapsed" desc="register">
    public static void register(Player player) {
        try {
            statement = MySQLManager.getStatement("INSERT INTO Coins (Player, Amount) VALUES (?, ?)");
            if (statement != null) {
                statement.setString(1, player.getName());
                statement.setInt(2, 0);
                statement.executeUpdate();
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //</editor-fold>

    /**
     * Is Player registered
     */

    //<editor-fold defaultstate="collapsed" desc="isRegistered">
    public static boolean isRegistered(String name) {
        try {
            statement = MySQLManager.getStatement("SELECT * FROM Coins WHERE Player = ?");
            if (statement != null) {
                statement.setString(1, name);
                result = statement.executeQuery();
            }
            boolean user = result.next();
            result.close();
            statement.close();
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    //</editor-fold>

    /**
     * Get Coins from Player
     */

    //<editor-fold defaultstate="collapsed" desc="getCoins">
    public static int getCoins(String name) {
        try {
            statement = MySQLManager.getStatement("SELECT * FROM Coins WHERE Player = ?");
            if (statement != null) {
                statement.setString(1, name);
                result = statement.executeQuery();
            }
            result.next();
            int coins = result.getInt("Amount");
            result.close();
            return coins;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
    //</editor-fold>

    /**
     * Set Coins from Player
     */

    //<editor-fold defaultstate="collapsed" desc="setCoins">
    public static void setCoins(String name, int amount) {
        if (isRegistered(name)) {
            try {
                statement = MySQLManager.getStatement("UPDATE Coins SET Amount = ? WHERE Player = ?");
                if (statement != null) {
                    statement.setInt(1, amount);
                    statement.setString(2, name);
                    statement.executeUpdate();
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            Player player = Bukkit.getPlayer(name);
            register(player);
            try {
                statement = MySQLManager.getStatement("UPDATE Coins SET Amount = ? WHERE Player = ?");
                if (statement != null) {
                    statement.setInt(1, amount);
                    statement.setString(2, name);
                    statement.executeUpdate();
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    //</editor-fold>

    Player player;

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        player = event.getPlayer();
        event.setJoinMessage(null);

        player.teleport(LocationManager.getLocation("Spawn"));

        player.getInventory().clear();
        player.getInventory().setItem(0, new ItemManager(Material.COMPASS).setDisplayName("§6Navigator §8| §eRechtsklick").build());

        player.setGameMode(GameMode.SURVIVAL);
        player.setHealth(20D);
        player.setFoodLevel(20);
        player.setPlayerWeather(WeatherType.CLEAR);

        if (!isRegistered(player.getName())) {
            setCoins(player.getName(), 0);
        }

        addScoreBoard(player);
    }

    private void addScoreBoard(Player player) {
        int coins = getCoins(player.getName());

        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("scoreboard", "dummy");

        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName("§6§lEhrenlosNet");

        objective.getScore("§8§m---------------").setScore(10);
        objective.getScore("§eDein Profil").setScore(9);
        objective.getScore("§8» §a" + player.getName()).setScore(8);
        objective.getScore(" ").setScore(7);
        objective.getScore("§eDein Rang").setScore(6);

        if (player.hasPermission("lobbysystem.rang.admin")) {
            objective.getScore("§8» §4Admin").setScore(5);
        } else if (player.hasPermission("lobbysystem.rang.developer")) {
            objective.getScore("§8» §3Developer").setScore(5);
        } else if (player.hasPermission("lobbysystem.rang.moderator")) {
            objective.getScore("§8» §cModerator").setScore(5);
        } else if (player.hasPermission("lobbysystem.rang.builder")) {
            objective.getScore("§8» §2Builder").setScore(5);
        } else if (player.hasPermission("lobbysystem.rang.supporter")) {
            objective.getScore("§8» §9Supporter").setScore(5);
        } else {
            objective.getScore("§8» §8Spieler").setScore(5);
        }

        objective.getScore("§b").setScore(4);
        objective.getScore("§eDeine Coins").setScore(3);
        objective.getScore("§8» §a" + coins).setScore(2);
        objective.getScore("§f").setScore(1);
        objective.getScore("§eTeamspeak§7/§eWebsite").setScore(0);
        objective.getScore("§8» §aehrenlos.net").setScore(-1);

        for (Player all : Bukkit.getOnlinePlayers()) {
            player.setScoreboard(scoreboard);
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        event.setQuitMessage(null);
    }

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent event) {
        event.setCancelled(true);
        event.setFoodLevel(20);
    }
}
