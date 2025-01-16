package org.example.database;

import org.example.exception.SQLConnectionException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Service de gestion de la connexion à la base de données PostgreSQL.
 * <p>
 * Cette classe gère l'établissement et la fermeture de la connexion à une base de données PostgreSQL spécifique.
 * Declaré en tant que classe statique, elle ne peut pas être instanciée et ses méthodes sont accessibles
 * Elle est Déclarez en global & initialisez en local au besoin
 * </p>
 *
 * @author Mehedi Touré & Adil Chetouni
 * @version 1.0
 * @since   1.0
 */
public class DbService {

    /**
     * URL de connexion à la base de données PostgreSQL.
     * Remplacez l'URL par celle de votre base de données si nécessaire.
     */
    private static final String URL = "jdbc:postgresql://localhost:5432/prog_bd";

    /**
     * Instance de la connexion à la base de données.
     * Cette connexion est partagée par toutes les méthodes de cette classe.
     */
    private static Connection connection;

    /**
     * Obtient une connexion à la base de données PostgreSQL.
     * Si une connexion échoue, une exception {@link SQLConnectionException} est levée.
     *
     * @return La connexion à la base de données.
     * @throws SQLConnectionException Si la connexion à la base de données échoue.
     */
    public static Connection getConnection() {
        try {
            connection = DriverManager.getConnection(URL);
            // System.out.println("Connexion réussie à PostgreSQL !") ;
        } catch (SQLException e) {
            throw new SQLConnectionException("Échec de la connexion à PostgreSQL.");
        }
        return connection;
    }

    /**
     * Ferme la connexion à la base de données.
     * Si la fermeture échoue, l'erreur est imprimée dans la console.
     */
    public static void closeConnection() {
        try {
            connection.close();
            // System.out.println("Connexion fermée.") ;
        } catch (SQLException e) {
            throw new SQLConnectionException("Échec de fermeture de la connexion à PostgreSQL.");
        }
    }
}
