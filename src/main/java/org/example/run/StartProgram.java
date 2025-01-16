package org.example.run;

import org.example.database.ScriptRunner;
import org.example.exception.SQLConnectionException;
import org.example.exception.SQLFailAdd;
import org.example.exception.SQLFailDelete;
import org.example.exception.SQLFailQuery;
import org.example.database.ActionsBDD;

import java.util.Scanner;

/**
 * La classe abstraite <code>StartProgram</code> gère l'exécution d'un menu interactif
 * permettant à l'utilisateur d'effectuer diverses opérations liées aux programmeurs
 * (affichage, ajout, suppression, mise à jour, etc.).
 *
 * <p>Elle définit des méthodes communes pour l'interaction utilisateur (lecture d'entrées,
 * affichage du menu, etc.) et déclare des méthodes abstraites pour les opérations
 * spécifiques à implémenter dans les classes concrètes héritant de <code>StartProgram</code>.</p>
 *
 * @author Mehedi Touré & Adil Chetouni
 * @version 1.0
 * @since   1.0
 */
public abstract class StartProgram {

    /**
     * Service permettant d'effectuer des actions (CRUD) sur les programmeurs
     * dans la base de données.
     */
    protected final ActionsBDD programmeurService;

    /**
     * Menu interactif permettant à l'utilisateur de choisir une action à effectuer.
     */
    protected final Menu menu;

    /**
     * Constructeur alternatif permettant de fournir un scanner personnalisé.
     *
     * @param programmeurService Le service permettant d'effectuer des actions
     *                           sur les programmeurs dans la base de données.
     * @param scanner            Le scanner à utiliser pour obtenir des entrées utilisateur.
     */
    public StartProgram(ActionsBDD programmeurService, Scanner scanner, boolean isTest) {
        this.programmeurService = programmeurService;
        this.menu = new Menu(scanner, isTest);
    }

    /**
     * Supprime un programmeur (par exemple en demandant l'ID ou le pseudo à l'utilisateur).
     * <p>
     * L'implémentation doit être fournie par les classes filles.
     * </p>
     */
    protected abstract void removeProgrammer();

    /**
     * Affiche en détail un programmeur particulier (sélectionné par l'utilisateur).
     * <p>
     * L'implémentation doit être fournie par les classes filles.
     * </p>
     */
    protected abstract void showProgrammer();

    /**
     * Ajoute un nouveau programmeur en demandant ses informations
     * (nom, prénom, salaire, etc.) à l'utilisateur.
     * <p>
     * L'implémentation doit être fournie par les classes filles.
     * </p>
     */
    protected abstract void addProgrammer();

    /**
     * Met à jour le salaire d'un programmeur, par exemple après
     * une promotion ou un ajustement.
     * <p>
     * L'implémentation doit être fournie par les classes filles.
     * </p>
     */
    protected abstract void updateSalaire();

    /**
     * Met à jour les informations d'un programmeur (nom, prénom, adresse, etc.).
     * <p>
     * L'implémentation doit être fournie par les classes filles.
     * </p>
     */
    protected abstract void updateProgrammer();

    /**
     * Affiche la liste de tous les programmeurs disponibles
     * (peut inclure ID, nom, prénom, etc.).
     * <p>
     * L'implémentation doit être fournie par les classes filles.
     * </p>
     */
    protected abstract void showProgrammers();

    /**
     * Cherche un programmeur dans la base à partir de son pseudo.
     * <p>
     * L'implémentation doit être fournie par les classes filles.
     * </p>
     */
    protected abstract void findByPseudo();

    /**
     * Méthode principale qui gère l'exécution du menu interactif.
     * <ul>
     *     <li>Affiche le menu principal.</li>
     *     <li>Lit et interprète le choix de l'utilisateur.</li>
     *     <li>Appelle la méthode appropriée selon le choix.</li>
     *     <li>Gère les exceptions liées aux opérations SQL (ajout, suppression, requête, etc.).</li>
     * </ul>
     *
     * <p>Continue à se répéter jusqu'à ce que l'utilisateur choisisse de quitter
     * (c'est-à-dire lorsque le choix vaut <code>6</code>).</p>
     */
    public void run() {
        int choix;

        try {
            programmeurService.findAll();
        } catch (Exception e) {
            System.err.println("\nLe programme ne peut pas continuer sans connexion à la base de données.");
            System.err.println(e.getMessage());
            System.err.println("Essaie de creation de table...");
            boolean result = ScriptRunner.SetupDb();
            if (!result) {
                System.err.println("Impossible de créer la table.");
                System.err.println("Programme annulé.");
                System.exit(-1);
            }
        }

        do {
            Menu.afficherMenu();
            choix = menu.getInputInt("Entrez votre choix : ");
            try {
                switch (choix) {
                    case 1:
                        showProgrammers();
                        break;
                    case 2:
                        showProgrammer();
                        break;
                    case 3:
                        removeProgrammer();
                        break;
                    case 4:
                        addProgrammer();
                        break;
                    case 5:
                        updateSalaire();
                        break;
                    case 6:
                        System.out.println("Quitter le programme");
                        break;
                    case 7:
                        findByPseudo();
                        break;
                    case 8:
                        updateProgrammer();
                        break;
                    default:
                        System.out.println("Choix invalide");
                }
            } catch (SQLFailAdd | SQLFailQuery | SQLFailDelete | SQLConnectionException e) {
                System.err.println(e.getMessage());
            } catch (Exception e) {
                System.err.println("Erreur inconnue : " + e.getMessage());
            }
        } while (choix != 6);
    }
}

