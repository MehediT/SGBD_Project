package org.example.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbService {
    private static final String URL = "jdbc:postgresql://localhost:5432/sgbdproject";
    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(URL);
                System.out.println("Connexion réussie à PostgreSQL !");
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException("Échec de la connexion à PostgreSQL.");
            }
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Connexion fermée.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
