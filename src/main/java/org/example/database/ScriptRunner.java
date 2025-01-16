package org.example.database;

import org.example.exception.SQLConnectionException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * La classe <strong>ScriptRunner</strong> permet d'exécuter des scripts SQL
 * pour créer et initialiser la base de données.
 * <p>
 * Elle utilise un {@link Connection} obtenu via {@link DbService#getConnection()}
 * afin de créer un {@link Statement} et exécuter plusieurs commandes SQL :
 * <ul>
 *   <li>Suppression de la table <code>programmeur</code> si elle existe déjà</li>
 *   <li>Création de la table <code>programmeur</code></li>
 *   <li>Insertion d'exemples de données</li>
 * </ul>
 * Après l'exécution, la connexion est fermée via la méthode {@link DbService#closeConnection()}.
 */
public class ScriptRunner {

    /**
     * Initialise la base de données en créant la table <code>programmeur</code>
     * et en insérant quelques données d'exemple.
     * <p>
     * Si les opérations sont réalisées avec succès, la méthode renvoie <code>true</code>.
     * En cas d'échec (erreur de connexion ou erreur d'exécution SQL), elle renvoie <code>false</code>.
     *
     * @return <code>true</code> si la configuration de la base a abouti, <code>false</code> sinon.
     * @throws SQLConnectionException si la connexion à la base de données échoue (classe personnalisée).
     */
    public static boolean SetupDb() throws SQLConnectionException {
        // On récupère la connexion à la base
        Connection connection;
        Statement statement = null;

        try {
            connection = DbService.getConnection();
            statement = connection.createStatement();

            // On exécute instruction par instruction
            statement.executeUpdate("DROP TABLE IF EXISTS programmeur");

            // Création de la table
            statement.executeUpdate("""
                    CREATE TABLE programmeur (
                        id SERIAL PRIMARY KEY,
                        nom VARCHAR(100) NOT NULL,
                        prenom VARCHAR(100) NOT NULL,
                        adresse VARCHAR(255),
                        pseudo VARCHAR(50) NOT NULL,
                        responsable INT DEFAULT -1,
                        hobby VARCHAR(50),
                        annNaissance INT,
                        salaire FLOAT,
                        prime FLOAT DEFAULT 0
                    )
                    """);

            // Insertions (premier batch sans 'responsable')
            statement.executeUpdate("""
                    INSERT INTO programmeur
                        (nom, prenom, adresse, pseudo, hobby, annNaissance, salaire, prime)
                    VALUES
                        ('Dupont', 'Alice', '123 Rue de Paris', 'alice123', 'Lecture', 1990, 52200.12, 540.00),
                        ('Durand', 'Bob', '45 Avenue des Champs', 'bobD45', 'Musique', 1985, 60800.12, 78.00),
                        ('Moreau', 'Charlie', '78 Boulevard Haussmann', 'charlieM', 'Cyclisme', 1995, 5500.19, 6000.58)
                    """);

            // Insertions (second batch avec champ 'responsable')
            statement.executeUpdate("""
                    INSERT INTO programmeur
                        (nom, prenom, adresse, pseudo, responsable, hobby, annNaissance, salaire, prime)
                    VALUES
                        ('Petit', 'David', '22 Rue Lafayette', 'davidP22', 1, 'Cuisine', 1988, 48000.0, 4000.0),
                        ('Martin', 'Emma', '11 Place de la République', 'emmaM11', 3, 'Voyage', 1992, 62000.0, 8000.0)
                    """);

            System.out.println("Script exécuté avec succès ! \nCreation de table réussie. Relancez le programme.\n\n");
            return true;
        } catch (SQLConnectionException e) {
            // Erreur lors de la création ou récupération de la connexion
            System.err.println("Erreur de connexion à la base : " + e.getMessage());
        } catch (SQLException e) {
            // Erreur lors de l'exécution SQL
            System.err.println("Erreur SQL : " + e.getMessage());
        } finally {
            // Fermeture du Statement puis de la connexion
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    System.err.println("Erreur lors de la fermeture du Statement : " + e.getMessage());
                }
            }
            // On ferme la connexion via le DbService
            DbService.closeConnection();
        }
        return false;
    }
}
