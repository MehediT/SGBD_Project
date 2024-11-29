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

public class ProgrammeurService implements IProgrammeurService {

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
                        rs.getString("responsable"),
                        rs.getString("hobby"),
                        rs.getInt("annNaissance"),
                        rs.getInt("salaire"),
                        rs.getInt("prime")
                );
                programmeurs.add(user);
            }
        } catch (SQLException e) {
//            e.printStackTrace();
            throw new SQLFailQuery("Erreur lors de la récupération des programmeurs.\n" + e.getMessage());
        } finally {
            DbService.closeConnection();
        }
        return programmeurs;
    }

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
                        rs.getString("responsable"),
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

    @Override
    public void add(Programmeur p) throws SQLFailAdd, SQLConnectionException {
        String sql = "INSERT INTO programmeur (nom, prenom, adresse, pseudo, responsable, hobby, annNaissance, salaire, prime) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DbService.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            setStringStatement(p, stmt);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLFailAdd("Erreur lors de l'ajout du programmeur : "
                    + p.getId() + "\n"
                    + p.getNom() + "\n"
                    + p.getPrenom() + "\n"
                    + e.getMessage()
            );
        }finally {
            DbService.closeConnection();
        }

    }

    @Override
    public void update(Programmeur updatedProgrammeur) throws SQLFailUpdate, SQLConnectionException {
        String sql = "UPDATE programmeur SET nom = ?, prenom = ?, adresse = ?, pseudo = ?, responsable = ?, hobby = ?, annNaissance = ?, salaire = ?, prime = ? WHERE id = ?";
        try (Connection conn = DbService.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            setStringStatement(updatedProgrammeur, pstmt);
            pstmt.setInt(1, updatedProgrammeur.getId());

            DbService.closeConnection();
        } catch (SQLException e) {
//            e.printStackTrace();
            throw new SQLFailUpdate("Erreur lors de la mise à jour du programmeur : "
                    + updatedProgrammeur.getId() + "\n"
                    + updatedProgrammeur.getNom() + "\n"
                    + updatedProgrammeur.getPrenom() + "\n"
                    + e.getMessage()
            );
        }
    }

    @Override
    public void delete(Programmeur p) throws SQLFailDelete, SQLConnectionException {
        String sql = "DELETE FROM programmeur WHERE id = ?";
        try (Connection conn = DbService.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, p.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
//            e.printStackTrace();
            throw new SQLFailDelete("Erreur lors de la suppression du programmeur : "
                    + p.getId() + "\n"
                    + p.getNom() + "\n"
                    + p.getPrenom() + "\n"
                    + e.getMessage()
            );
        } finally {
            DbService.closeConnection();
        }
    }

    private void setStringStatement(Programmeur updatedProgrammeur, PreparedStatement stmt) throws SQLException {
        stmt.setInt(1, updatedProgrammeur.getId());
        stmt.setString(2, updatedProgrammeur.getNom());
        stmt.setString(3, updatedProgrammeur.getPrenom());
        stmt.setString(4, updatedProgrammeur.getAdresse());
        stmt.setString(5, updatedProgrammeur.getPseudo());
        stmt.setString(6, updatedProgrammeur.getResponsable());
        stmt.setString(7, updatedProgrammeur.getHobby());
        stmt.setInt(8, updatedProgrammeur.getAnNaissance());
        stmt.setInt(9, updatedProgrammeur.getSalaire());
        stmt.setInt(10, updatedProgrammeur.getPrime());
    }
}
