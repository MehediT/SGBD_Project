package org.example.run;

public class Query {
    public static String selectAllProgrammers = "SELECT * FROM Programmeur";
    public static String selectProgrammerById = "SELECT * FROM Programmeur WHERE id = ?";
    public static String selectProgrammerByPseudo = "SELECT * FROM programmeur WHERE pseudo = ? LIMIT 1";
    public static String insertProgrammer = "INSERT INTO programmeur (nom, prenom, adresse, pseudo, responsable, hobby, annNaissance, salaire, prime) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    public static String updateProgrammer = "UPDATE programmeur SET nom = ?, prenom = ?, adresse = ?, pseudo = ?, responsable = ?, hobby = ?, annNaissance = ?, salaire = ?, prime = ? WHERE id = ?";
    public static String deleteProgrammer = "DELETE FROM programmeur WHERE id = ?";
}
