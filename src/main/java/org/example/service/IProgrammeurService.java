package org.example.service;

import org.example.model.Programmeur;

import java.util.List;

public interface IProgrammeurService {
    // Méthode pour récupérer tous les programmeurs
    List<Programmeur> findAll();

    // Méthode pour récupérer un programmeur par son ID
    Programmeur findOne(int id);

    // Méthode pour ajouter un nouveau programmeur
    void add(Programmeur programmeur);

    // Méthode pour mettre à jour un programmeur
    void update(int id, Programmeur updatedProgrammeur);

    // Méthode pour supprimer un programmeur
    void delete(int id);
}
