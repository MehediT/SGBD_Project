package org.example.service;

import org.example.exception.*;
import org.example.model.Programmeur;

import java.util.List;

/**
 * Interface de service pour la gestion des programmeurs dans la base de données.
 * <p>
 * Cette interface définit les méthodes nécessaires pour effectuer des opérations CRUD (Créer, Lire, Mettre à jour, Supprimer)
 * sur les objets {@link Programmeur} dans la base de données.
 * Les méthodes peuvent lever des exceptions spécifiques liées aux échecs d'opérations SQL.
 * </p>
 */
public interface IProgrammeurService {

    /**
     * Récupère tous les programmeurs dans la base de données.
     *
     * @return Une liste de programmeurs.
     * @throws SQLFailQuery En cas d'échec lors de l'exécution de la requête.
     * @throws SQLConnectionException En cas de problème avec la connexion à la base de données.
     */
    List<Programmeur> findAll() throws SQLFailQuery, SQLConnectionException;

    /**
     * Récupère un programmeur spécifique par son identifiant (ID).
     *
     * @param id L'identifiant du programmeur à récupérer.
     * @return Le programmeur correspondant à l'ID fourni, ou null si le programmeur n'existe pas.
     * @throws SQLFailQuery En cas d'échec lors de l'exécution de la requête.
     * @throws SQLConnectionException En cas de problème avec la connexion à la base de données.
     */
    Programmeur findOne(int id) throws SQLFailQuery, SQLConnectionException;

    /**
     * Récupère un programmeur spécifique par son identifiant (ID).
     *
     * @param pseudo Pseudo du programmeur à récupérer.
     * @return Le programmeur correspondant au premier pseudo fourni, ou null si le programmeur n'existe pas.
     * @throws SQLFailQuery En cas d'échec lors de l'exécution de la requête.
     * @throws SQLConnectionException En cas de problème avec la connexion à la base de données.
     */
    Programmeur findByPseudo(String pseudo) throws SQLFailQuery, SQLConnectionException;

    /**
     * Ajoute un nouveau programmeur à la base de données.
     *
     * @param programmeur L'objet Programmeur à ajouter.
     * @throws SQLFailAdd En cas d'échec lors de l'ajout du programmeur.
     * @throws SQLConnectionException En cas de problème avec la connexion à la base de données.
     */
    void add(Programmeur programmeur) throws SQLFailAdd, SQLConnectionException;

    /**
     * Met à jour les informations d'un programmeur dans la base de données.
     *
     * @param updatedProgrammeur L'objet Programmeur avec les nouvelles informations.
     * @throws SQLFailUpdate En cas d'échec lors de la mise à jour du programmeur.
     * @throws SQLConnectionException En cas de problème avec la connexion à la base de données.
     */
    void update(Programmeur updatedProgrammeur) throws SQLFailUpdate, SQLConnectionException;

    /**
     * Supprime un programmeur de la base de données.
     *
     * @param p L'objet Programmeur à supprimer.
     * @throws SQLFailDelete En cas d'échec lors de la suppression du programmeur.
     * @throws SQLConnectionException En cas de problème avec la connexion à la base de données.
     */
    void delete(Programmeur p) throws SQLFailDelete, SQLConnectionException;



}
