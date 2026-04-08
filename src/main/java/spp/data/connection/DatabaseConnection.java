/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.data.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Singleton class to manage the MySQL database connection.
 */
public class DatabaseConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/mydb?useSSL=false&serverTimezone=UTC";
    private static final String USER = "spp_user_v1_cpspp4spc26";
    private static final String PASSWORD = "14032026";
    
    private static Connection connection = null;

    private DatabaseConnection() {
        // Constructor privado para evitar que otras clases creen instancias (Singleton)
    }

    /**
     * Gets the active database connection or creates a new one.
     * @return Connection object to the MySQL database, or null if it fails.
     */
    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                // Abre la conexión usando las credenciales
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("INFO: Database connection established successfully to 'mydb'.");
            }
        } catch (SQLException e) {
            System.out.println("FATAL: Could not connect to the database. Details: " + e.getMessage());
        } finally {
            System.out.println("DEBUG: getConnection process evaluated.");
        }
        
        return connection;
    }

    /**
     * Closes the active database connection safely.
     */
    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("INFO: Database connection closed successfully.");
            }
        } catch (SQLException e) {
            System.out.println("ERROR: Could not close the database connection. Details: " + e.getMessage());
        } finally {
            System.out.println("DEBUG: closeConnection process evaluated.");
        }
    }
}
