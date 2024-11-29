package org.example.service;

import org.example.exception.SQLConnectionException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbService {
    private static final String URL = "jdbc:postgresql://localhost:5432/sgbdproject";
    private static Connection connection;

    public static Connection getConnection() {
        try {
            connection = DriverManager.getConnection(URL);
            System.out.println("Connexion réussie à PostgreSQL !");
        } catch (SQLException e) {
//            e.printStackTrace();
            throw new SQLConnectionException("Échec de la connexion à PostgreSQL.");
        }  
        return connection;
    }

    public static void closeConnection() {
        try {
            connection.close();
            System.out.println("Connexion fermée.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
