package org.example.service;

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
    public List<Programmeur> findAll() {
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
            e.printStackTrace();
        }
        return programmeurs;
    }

    @Override
    public Programmeur findOne(int id) {
        String sql = "SELECT * FROM programmeurs WHERE id = ?";
        try (Connection conn = DbService.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
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
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Programmeur add(Programmeur programmeur) {
        String sql = "INSERT INTO programmeurs (nom, prenom, adresse, pseudo, responsable, hobby, annNaissance, salaire, prime) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DbService.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, programmeur.getNom());
            pstmt.setString(2, programmeur.getPrenom());
            pstmt.setString(3, programmeur.getAdresse());
            pstmt.setString(4, programmeur.getPseudo());
            pstmt.setString(5, programmeur.getResponsable());
            pstmt.setString(6, programmeur.getHobby());
            pstmt.setInt(7, programmeur.getAnnNaissance());
            pstmt.setInt(8, programmeur.getSalaire());
            pstmt.setInt(9, programmeur.getPrime());

            return pstmt.executeUpdate()==1 ? programmeur : null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Programmeur update(int id, Programmeur updatedProgrammeur) {
        String sql = "UPDATE programmeurs SET nom = ?, prenom = ?, adresse = ?, pseudo = ?, responsable = ?, hobby = ?, annNaissance = ?, salaire = ?, prime = ? WHERE id = ?";
        try (Connection conn = DbService.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, updatedProgrammeur.getNom());
            pstmt.setString(2, updatedProgrammeur.getPrenom());
            pstmt.setString(3, updatedProgrammeur.getAdresse());
            pstmt.setString(4, updatedProgrammeur.getPseudo());
            pstmt.setString(5, updatedProgrammeur.getResponsable());
            pstmt.setString(6, updatedProgrammeur.getHobby());
            pstmt.setInt(7, updatedProgrammeur.getAnnNaissance());
            pstmt.setInt(8, updatedProgrammeur.getSalaire());
            pstmt.setInt(9, updatedProgrammeur.getPrime());
            pstmt.setInt(10, id);



            return pstmt.executeUpdate()==1 ? updatedProgrammeur : null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM programmeurs WHERE id = ?";
        try (Connection conn = DbService.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
