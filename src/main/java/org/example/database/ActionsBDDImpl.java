package org.example.database;

import org.example.exception.*;
import org.example.model.Programmeur;
import org.example.run.Query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Service permettant d'interagir avec la base de données pour effectuer des opérations CRUD sur les programmeurs.
 * Cette classe implémente l'interface {@link ActionsBDD} et fournit des méthodes pour ajouter, mettre à jour,
 * supprimer et récupérer des programmeurs dans la base de données.
 *
 * @author Mehedi Touré & Adil Chetouni
 * @version 1.0
 * @since   1.0
 */
public class ActionsBDDImpl implements ActionsBDD {

    /**
     * Récupère tous les programmeurs présents dans la base de données.
     *
     * @return Une liste de programmeurs récupérés de la base de données.
     * @throws SQLFailQuery En cas d'erreur lors de la récupération des programmeurs.
     * @throws SQLConnectionException En cas d'erreur avec la connexion à la base de données.
     */
    @Override
    public List<Programmeur> findAll() throws SQLFailQuery, SQLConnectionException {
        List<Programmeur> programmeurs = new ArrayList<>();
        String sql = Query.selectAllProgrammers;
        try (Connection conn = DbService.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Programmeur user = new Programmeur(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("adresse"),
                        rs.getString("pseudo"),
                        rs.getInt("responsable"),
                        rs.getString("hobby"),
                        rs.getInt("annNaissance"),
                        rs.getInt("salaire"),
                        rs.getInt("prime")
                );
                programmeurs.add(user);
            }
        } catch (SQLException e) {
            throw new SQLFailQuery("Erreur lors de la récupération des programmeurs.\n" + e.getMessage());
        } finally {
            DbService.closeConnection();
        }
        return programmeurs;
    }

    /**
     * Récupère un programmeur spécifique par son identifiant (ID).
     *
     * @param id L'identifiant du programmeur à récupérer.
     * @return Le programmeur correspondant à l'ID, ou null si aucun programmeur n'est trouvé.
     * @throws SQLFailQuery En cas d'erreur lors de la récupération du programmeur.
     * @throws SQLConnectionException En cas d'erreur avec la connexion à la base de données.
     */
    @Override
    public Programmeur findOne(int id) throws SQLFailQuery, SQLConnectionException {
        String sql = Query.selectProgrammerById;
        try (Connection conn = DbService.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Programmeur(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("adresse"),
                        rs.getString("pseudo"),
                        rs.getInt("responsable"),
                        rs.getString("hobby"),
                        rs.getInt("annNaissance"),
                        rs.getInt("salaire"),
                        rs.getInt("prime")
                );
            }
        } catch (SQLException e) {
            throw new SQLFailQuery("Erreur lors de la récupération du programmeur.\n" + e.getMessage());
        } finally {
            DbService.closeConnection();
        }
        return null;
    }


    /**
     * Récupère un programmeur spécifique par son pseudo.
     *
     * @param pseudo Pseudo du programmeur à récupérer.
     * @return Le programmeur correspondant à l'ID, ou null si aucun programmeur n'est trouvé.
     * @throws SQLFailQuery En cas d'erreur lors de la récupération du programmeur.
     * @throws SQLConnectionException En cas d'erreur avec la connexion à la base de données.
     */
    @Override
    public Programmeur findByPseudo(String pseudo) throws SQLFailQuery, SQLConnectionException {
        String sql = Query.selectProgrammerByPseudo ;
        try (Connection conn = DbService.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, pseudo);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Programmeur(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("adresse"),
                        rs.getString("pseudo"),
                        rs.getInt("responsable"),
                        rs.getString("hobby"),
                        rs.getInt("annNaissance"),
                        rs.getInt("salaire"),
                        rs.getInt("prime")
                );
            }
        } catch (SQLException e) {
            throw new SQLFailQuery("Erreur lors de la récupération du programmeur par pseudo.\n" + e.getMessage());
        } finally {
            DbService.closeConnection();
        }
        return null;
    }

    /**
     * Ajoute un nouveau programmeur à la base de données.
     *
     * @param programmeur Le programmeur à ajouter.
     * @throws SQLFailAdd En cas d'erreur lors de l'ajout du programmeur.
     * @throws SQLConnectionException En cas d'erreur avec la connexion à la base de données.
     */
    @Override
    public boolean add(Programmeur programmeur) throws SQLFailAdd, SQLConnectionException {
        String sql = Query.insertProgrammer;
        try (Connection conn = DbService.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, programmeur.getNom());
            stmt.setString(2, programmeur.getPrenom());
            stmt.setString(3, programmeur.getAdresse());
            stmt.setString(4, programmeur.getPseudo());
            stmt.setInt(5, programmeur.getResponsable());
            stmt.setString(6, programmeur.getHobby());
            stmt.setInt(7, programmeur.getAnNaissance());
            stmt.setFloat(8, programmeur.getSalaire());
            stmt.setFloat(9, programmeur.getPrime());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLFailAdd("Erreur lors de l'ajout du programmeur : " + e.getMessage());
        } finally {
            DbService.closeConnection();
        }
        return true;
    }

    /**
     * Met à jour un programmeur existant dans la base de données.
     *
     * @param updatedProgrammeur Le programmeur avec les nouvelles données à mettre à jour.
     * @throws SQLFailUpdate          En cas d'erreur lors de la mise à jour du programmeur.
     * @throws SQLConnectionException En cas d'erreur avec la connexion à la base de données.
     */
    @Override
    public void update(Programmeur updatedProgrammeur) throws SQLFailUpdate, SQLConnectionException {
        String sql = Query.updateProgrammer;
        try (Connection conn = DbService.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, updatedProgrammeur.getNom());
            pstmt.setString(2, updatedProgrammeur.getPrenom());
            pstmt.setString(3, updatedProgrammeur.getAdresse());
            pstmt.setString(4, updatedProgrammeur.getPseudo());
            pstmt.setInt(5, updatedProgrammeur.getResponsable());
            pstmt.setString(6, updatedProgrammeur.getHobby());
            pstmt.setInt(7, updatedProgrammeur.getAnNaissance());
            pstmt.setFloat(8, updatedProgrammeur.getSalaire());
            pstmt.setFloat(9, updatedProgrammeur.getPrime());
            pstmt.setInt(10, updatedProgrammeur.getId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLFailUpdate("Erreur lors de la mise à jour du programmeur : " + e.getMessage());
        } finally {
            DbService.closeConnection();
        }
    }

    /**
     * Supprime un programmeur de la base de données.
     *
     * @param p Le programmeur à supprimé.
     * @throws SQLFailDelete          En cas d'erreur lors de la suppression du programmeur.
     * @throws SQLConnectionException En cas d'erreur avec la connexion à la base de données.
     */
    @Override
    public void delete(Programmeur p) throws SQLFailDelete, SQLConnectionException {
        String sql = Query.deleteProgrammer;
        try (Connection conn = DbService.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, p.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLFailDelete("Erreur lors de la suppression du programmeur : " + e.getMessage());
        } finally {
            DbService.closeConnection();
        }
    }
}
