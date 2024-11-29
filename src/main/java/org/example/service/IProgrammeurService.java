package org.example.service;

import org.example.exception.*;
import org.example.model.Programmeur;

import java.util.List;

public interface IProgrammeurService {
    // Méthode pour récupérer tous les programmeurs
    List<Programmeur> findAll() throws SQLFailQuery, SQLConnectionException;

    // Méthode pour récupérer un programmeur par son ID
    Programmeur findOne(int id) throws SQLFailQuery, SQLConnectionException;

    // Méthode pour ajouter un nouveau programmeur
    void add(Programmeur programmeur) throws SQLFailAdd, SQLConnectionException;

    // Méthode pour mettre à jour un programmeur
    void update(Programmeur updatedProgrammeur) throws SQLFailUpdate, SQLConnectionException;

    // Méthode pour supprimer un programmeur
    void delete(Programmeur p) throws SQLFailDelete, SQLConnectionException;

}
