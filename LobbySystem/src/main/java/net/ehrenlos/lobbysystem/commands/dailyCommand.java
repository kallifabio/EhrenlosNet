package net.ehrenlos.lobbysystem.commands;

import net.ehrenlos.lobbysystem.LobbySystem;
import net.ehrenlos.lobbysystem.manager.MySQLManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class dailyCommand implements CommandExecutor {

    static PreparedStatement statement;
    static ResultSet result;

    int dailycoins;

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
     * Get Daily Coins
     */

    //<editor-fold defaultstate="collapsed" desc="getDailyCoins">
    public void getDailyCoins(Player player) {
        int coins = getCoins(player);
        if (isRegistered(player)) {
            try {
                statement = MySQLManager.getStatement("UPDATE Coins SET Amount = ? WHERE Player = ?");
                if (statement != null) {
                    statement.setInt(1, coins + dailycoins);
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
                    statement.setInt(1, coins + dailycoins);
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

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(LobbySystem.getPrefix() + "§4Du musst ein Spieler sein");
            return false;
        }

        final Player player = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("daily")) {
            if (args.length == 0) {
                Random random = new Random();
                dailycoins = random.nextInt(500) + 1;
                getDailyCoins(player);
                player.sendMessage(LobbySystem.getPrefix() + "§7Du hast deine täglichen Coins von §e" + dailycoins + " §7abgeholt");
            }

            if (args.length >= 1) {
                player.sendMessage(LobbySystem.getPrefix() + "§cBitte benutze §6/daily");
            }

        }
        return false;
    }
}
