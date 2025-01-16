package org.example.service;

import org.example.exception.*;
import org.example.model.Programmeur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Service permettant d'interagir avec la base de données pour effectuer des opérations CRUD sur les programmeurs.
 * Cette classe implémente l'interface {@link IProgrammeurService} et fournit des méthodes pour ajouter, mettre à jour,
 * supprimer et récupérer des programmeurs dans la base de données.
 */
public class ProgrammeurService implements IProgrammeurService {

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
        String sql = "SELECT * FROM programmeur";
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
        String sql = "SELECT * FROM programmeur WHERE id = ?";
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
        String sql = "SELECT * FROM programmeur WHERE pseudo = ? LIMIT 1";
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

    @Override
    public boolean add(Programmeur programmeur) throws SQLFailAdd, SQLConnectionException {
        String sql = "INSERT INTO programmeur (nom, prenom, adresse, pseudo, responsable, hobby, annNaissance, salaire, prime) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DbService.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, programmeur.getNom());
            stmt.setString(2, programmeur.getPrenom());
            stmt.setString(3, programmeur.getAdresse());
            stmt.setString(4, programmeur.getPseudo());
            stmt.setInt(5, programmeur.getResponsable());
            stmt.setString(6, programmeur.getHobby());
            stmt.setInt(7, programmeur.getAnNaissance());
            stmt.setInt(8, programmeur.getSalaire());
            stmt.setInt(9, programmeur.getPrime());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLFailAdd("Erreur lors de l'ajout du programmeur : " + e.getMessage());
        } finally {
            DbService.closeConnection();
        }
        return true;
    }


    /**
     * Ajoute un nouveau programmeur à la base de données.
     *
     * @param p Le programmeur à ajouter.
     * @throws SQLFailAdd En cas d'erreur lors de l'ajout du programmeur.
     * @throws SQLConnectionException En cas d'erreur avec la connexion à la base de données.
     */


    /**
     * Met à jour un programmeur existant dans la base de données.
     *
     * @param updatedProgrammeur Le programmeur avec les nouvelles données à mettre à jour.
     * @throws SQLFailUpdate En cas d'erreur lors de la mise à jour du programmeur.
     * @throws SQLConnectionException En cas d'erreur avec la connexion à la base de données.
     */
    @Override
    public boolean update(Programmeur updatedProgrammeur) throws SQLFailUpdate, SQLConnectionException {
        String sql = "UPDATE programmeur SET nom = ?, prenom = ?, adresse = ?, pseudo = ?, responsable = ?, hobby = ?, annNaissance = ?, salaire = ?, prime = ? WHERE id = ?";
        try (Connection conn = DbService.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, updatedProgrammeur.getNom());
            pstmt.setString(2, updatedProgrammeur.getPrenom());
            pstmt.setString(3, updatedProgrammeur.getAdresse());
            pstmt.setString(4, updatedProgrammeur.getPseudo());
            pstmt.setInt(5, updatedProgrammeur.getResponsable());
            pstmt.setString(6, updatedProgrammeur.getHobby());
            pstmt.setInt(7, updatedProgrammeur.getAnNaissance());
            pstmt.setInt(8, updatedProgrammeur.getSalaire());
            pstmt.setInt(9, updatedProgrammeur.getPrime());
            pstmt.setInt(10, updatedProgrammeur.getId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLFailUpdate("Erreur lors de la mise à jour du programmeur : " + e.getMessage());
        } finally {
            DbService.closeConnection();
        }
        return true;
    }

    /**
     * Supprime un programmeur de la base de données.
     *
     * @param p Le programmeur à supprimé.
     * @throws SQLFailDelete En cas d'erreur lors de la suppression du programmeur.
     * @throws SQLConnectionException En cas d'erreur avec la connexion à la base de données.
     */
    @Override
    public boolean delete(Programmeur p) throws SQLFailDelete, SQLConnectionException {
        String sql = "DELETE FROM programmeur WHERE id = ?";
        try (Connection conn = DbService.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, p.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLFailDelete("Erreur lors de la suppression du programmeur : " + e.getMessage());
        } finally {
            DbService.closeConnection();
        }
        return true;
    }

    /**
     * Remplie un {@link PreparedStatement} avec les informations d'un programmeur pour une mise à jour.
     *
     * @param updatedProgrammeur Le programmeur avec les nouvelles données.
     * @param stmt Le {@link PreparedStatement} à remplir.
     * @throws SQLException Si une erreur se produit lors du remplissage du statement.
     */
    private void setStringStatement(Programmeur updatedProgrammeur, PreparedStatement stmt) throws SQLException {
        stmt.setInt(1, updatedProgrammeur.getId());
        stmt.setString(2, updatedProgrammeur.getNom());
        stmt.setString(3, updatedProgrammeur.getPrenom());
        stmt.setString(4, updatedProgrammeur.getAdresse());
        stmt.setString(5, updatedProgrammeur.getPseudo());
        stmt.setInt(6, updatedProgrammeur.getResponsable());
        stmt.setString(7, updatedProgrammeur.getHobby());
        stmt.setInt(8, updatedProgrammeur.getAnNaissance());
        stmt.setInt(9, updatedProgrammeur.getSalaire());
        stmt.setInt(10, updatedProgrammeur.getPrime());
    }
}
