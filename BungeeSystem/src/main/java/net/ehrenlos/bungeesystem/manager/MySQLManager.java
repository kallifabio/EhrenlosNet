package net.ehrenlos.bungeesystem.manager;

import net.ehrenlos.bungeesystem.BungeeSystem;
import net.md_5.bungee.BungeeCord;

import java.sql.*;

public class MySQLManager {

    private static Connection connection;
    private final String host = "web07.bero-webspace.de";
    private final int port = 3306;
    private final String database = "ehrenlos";
    private final String user = "nitschpas";
    private final String password = "ehrenlosdatenbank!";

    public static boolean isConnected() {
        return connection != null;
    }

    //<editor-fold defaultstate="collapsed" desc="getStatement">
    public static PreparedStatement getStatement(String sql) {
        if (isConnected()) {
            PreparedStatement statement;
            try {
                statement = connection.prepareStatement(sql);
                return statement;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="getResult">
    public static ResultSet getResult(String sql) {
        if (isConnected()) {
            PreparedStatement statement;
            ResultSet result;
            try {
                statement = getStatement(sql);
                result = statement.executeQuery();
                return result;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    //</editor-fold>

    public static void update(final String query) {
        if (isConnected()) {
            try {
                MySQLManager.connection.createStatement().executeUpdate(query);
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
    }

    public static ResultSet getResults(final String query) {
        if (isConnected()) {
            try {
                return MySQLManager.connection.createStatement().executeQuery(query);
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }

    //<editor-fold defaultstate="collapsed" desc="openConnection">
    public void openConnection() throws SQLException, ClassNotFoundException {
        if (connection != null && !connection.isClosed()) {
            return;
        }

        synchronized (this) {
            if (connection != null && !connection.isClosed()) {
                return;
            }
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database + "?autoReconnect=true", user, password);
            BungeeCord.getInstance().getConsole().sendMessage(BungeeSystem.getPrefix() + "MySQL ist verbunden");
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="closeConnection">
    public void closeConnection() {
        if (isConnected()) {
            try {
                connection.close();
                BungeeCord.getInstance().getConsole().sendMessage(BungeeSystem.getPrefix() + "MySQL ist geschlossen");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    //</editor-fold>

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getDatabase() {
        return database;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

}
