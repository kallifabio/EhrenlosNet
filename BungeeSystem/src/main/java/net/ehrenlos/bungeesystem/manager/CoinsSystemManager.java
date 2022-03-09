package net.ehrenlos.bungeesystem.manager;

import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CoinsSystemManager {

    static PreparedStatement statement;
    static ResultSet result;

    /**
     * Register Player, Amount
     */

    //<editor-fold defaultstate="collapsed" desc="register">
    public static void register(ProxiedPlayer proxiedPlayer) {
        try {
            statement = MySQLManager.getStatement("INSERT INTO Coins (Player, Amount) VALUES (?, ?)");
            if (statement != null) {
                statement.setString(1, proxiedPlayer.getName());
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
        return 0;
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
            ProxiedPlayer proxiedPlayer = BungeeCord.getInstance().getPlayer(name);
            register(proxiedPlayer);
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

    /**
     * Add Coins to Player
     */

    //<editor-fold defaultstate="collapsed" desc="addCoins">
    public static void addCoins(String name, int amount) {
        int coins = getCoins(name);
        if (isRegistered(name)) {
            try {
                statement = MySQLManager.getStatement("UPDATE Coins SET Amount = ? WHERE Player = ?");
                if (statement != null) {
                    statement.setInt(1, coins + amount);
                    statement.setString(2, name);
                    statement.executeUpdate();
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            ProxiedPlayer proxiedPlayer = BungeeCord.getInstance().getPlayer(name);
            register(proxiedPlayer);
            try {
                statement = MySQLManager.getStatement("UPDATE Coins SET Amount = ? WHERE Player = ?");
                if (statement != null) {
                    statement.setInt(1, coins + amount);
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

    /**
     * Remove Coins from Player
     */

    //<editor-fold defaultstate="collapsed" desc="removeCoins">
    public static void removeCoins(String name, int amount) {
        int coins = getCoins(name);
        if (isRegistered(name)) {
            try {
                statement = MySQLManager.getStatement("UPDATE Coins SET Amount = ? WHERE Player = ?");
                if (statement != null) {
                    statement.setInt(1, coins - amount);
                    statement.setString(2, name);
                    statement.executeUpdate();
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            ProxiedPlayer proxiedPlayer = BungeeCord.getInstance().getPlayer(name);
            register(proxiedPlayer);
            try {
                statement = MySQLManager.getStatement("UPDATE Coins SET Amount = ? WHERE Player = ?");
                if (statement != null) {
                    statement.setInt(1, coins - amount);
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

    /**
     * Has Player Coins
     */

    //<editor-fold defaultstate="collapsed" desc="hasCoins">
    /*public static boolean hasCoins(ProxiedPlayer proxiedPlayer, int amount) {
        int coins = BungeeSystem.getConfiguration().getInt(proxiedPlayer + ".Coins");
        return coins >= amount;
    }*/
    //</editor-fold>

    //TODO add hasCoins
}
