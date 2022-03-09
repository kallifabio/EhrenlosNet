package net.ehrenlos.lobbysystem.listeners;

import net.ehrenlos.lobbysystem.LobbySystem;
import net.ehrenlos.lobbysystem.manager.MySQLManager;
import org.bukkit.Bukkit;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class SignListener implements Listener {

    static PreparedStatement statement;
    static ResultSet result;

    int wincoins;

    Player player;

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
    public static boolean isRegistered(Player player) {
        try {
            statement = MySQLManager.getStatement("SELECT * FROM Coins WHERE Player = ?");
            if (statement != null) {
                statement.setString(1, player.getName());
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
    public static int getCoins(Player player) {
        try {
            statement = MySQLManager.getStatement("SELECT * FROM Coins WHERE Player = ?");
            if (statement != null) {
                statement.setString(1, player.getName());
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
     * Get Win Coins
     */

    //<editor-fold defaultstate="collapsed" desc="getWinCoins">
    public void getWinCoins(Player player) {
        int coins = getCoins(player);
        if (isRegistered(player)) {
            try {
                statement = MySQLManager.getStatement("UPDATE Coins SET Amount = ? WHERE Player = ?");
                if (statement != null) {
                    statement.setInt(1, coins + wincoins);
                    statement.setString(2, player.getName());
                    statement.executeUpdate();
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            register(player);
            try {
                statement = MySQLManager.getStatement("UPDATE Coins SET Amount = ? WHERE Player = ?");
                if (statement != null) {
                    statement.setInt(1, coins + wincoins);
                    statement.setString(2, player.getName());
                    statement.executeUpdate();
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    //</editor-fold>

    @EventHandler
    public void onSignChange(SignChangeEvent event) {
        if (event.getLine(0).equalsIgnoreCase("[coins]")) {
            event.setLine(0, "§6Gut gemacht");
            event.setLine(1, "");
            event.setLine(2, "§0Hole dir deine");
            event.setLine(3, "§0Belohnung ab");
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        player = event.getPlayer();
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (event.getClickedBlock().getState() instanceof Sign) {
                Sign sign = (Sign) event.getClickedBlock().getState();
                if (sign.getLine(0).equalsIgnoreCase("§6Gut gemacht")) {
                    Random random = new Random();
                    wincoins = random.nextInt(50) + 1;
                    getWinCoins(player);
                    player.sendMessage(LobbySystem.getPrefix() + "§7Du hast dir deine Belohnung von §e" + wincoins + " §7Coins abgeholt");
                }
            }
        }

    }
}
