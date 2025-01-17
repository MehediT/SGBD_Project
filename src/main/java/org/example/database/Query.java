package org.example.database;

/**
 * La classe <strong>Query</strong> regroupe l'ensemble des requêtes SQL
 * utilisées pour interagir avec la table <code>programmeur</code>.
 */
public class Query {

    /**
     * Requête SQL pour sélectionner tous les programmeurs.
     * <p>
     * Renvoie toutes les colonnes depuis la table <code>programmeur</code>.
     */
    public static String selectAllProgrammers = "SELECT * FROM Programmeur";

    /**
     * Requête SQL pour sélectionner un programmeur en fonction de son identifiant unique (ID).
     * <p>
     * Utilise un paramètre préparé <code>?</code> pour l'ID.
     */
    public static String selectProgrammerById = "SELECT * FROM Programmeur WHERE id = ?";

    /**
     * Requête SQL pour sélectionner un programmeur selon son pseudo.
     * <p>
     * Limite le résultat à une seule occurrence.
     */
    public static String selectProgrammerByPseudo = "SELECT * FROM programmeur WHERE pseudo = ? LIMIT 1";

    /**
     * Requête SQL pour insérer un nouveau programmeur dans la table <code>programmeur</code>.
     * <p>
     * Les champs insérés sont : <code>nom, prenom, adresse, pseudo, responsable, hobby,
     * annNaissance, salaire, prime</code>.
     */
    public static String insertProgrammer =
            "INSERT INTO programmeur (nom, prenom, adresse, pseudo, responsable, hobby, annNaissance, salaire, prime) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    /**
     * Requête SQL pour mettre à jour un programmeur existant dans la table <code>programmeur</code>.
     * <p>
     * Les champs mis à jour sont : <code>nom, prenom, adresse, pseudo, responsable, hobby,
     * annNaissance, salaire, prime</code>, filtrés par l'ID.
     */
    public static String updateProgrammer =
            "UPDATE programmeur SET nom = ?, prenom = ?, adresse = ?, pseudo = ?, responsable = ?, hobby = ?, "
                    + "annNaissance = ?, salaire = ?, prime = ? WHERE id = ?";

    /**
     * Requête SQL pour supprimer un programmeur en fonction de son identifiant unique (ID).
     */
    public static String deleteProgrammer = "DELETE FROM programmeur WHERE id = ?";
}
