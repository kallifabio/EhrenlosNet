package net.ehrenlos.bungeesystem.manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQL {

    public static Connection connection;
    public static String host = "web07.bero-webspace.de";
    public static String port = "3306";
    public static String database = "ehrenlos";
    public static String username = "nitschpas";
    public static String password = "ehrenlosdatenbank!";

    public static void connect() {
        if (!isConnected()) {
            try {
                MySQL.connection = DriverManager.getConnection("jdbc:mysql://" + MySQL.host + ":" + MySQL.port + "/" + MySQL.database + "?autoReconnect=true", MySQL.username, MySQL.password);
                System.out.println("[MySQL] The MySQL connection for the plugin 'BungeeSystem' has been activated!");
            } catch (SQLException exception) {
                exception.printStackTrace();
                System.out.println("[MySQL] An error occurred during the connection for the plugin 'BungeeSystem'! Please check the MySQL-Data in the config.yml!");
            }
        }
    }

    public static void close() {
        if (isConnected()) {
            try {
                MySQL.connection.close();
                System.out.println("[MySQL] The MySQL connection for the plugin 'BungeeSystem' has been deactivated!");
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
    }

    public static boolean isConnected() {
        return MySQL.connection != null;
    }

    public static void update(final String query) {
        if (isConnected()) {
            try {
                MySQL.connection.createStatement().executeUpdate(query);
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
    }

    public static ResultSet getResult(final String query) {
        if (isConnected()) {
            try {
                return MySQL.connection.createStatement().executeQuery(query);
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }
}
