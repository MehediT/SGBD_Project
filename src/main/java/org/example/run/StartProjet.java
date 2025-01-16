package org.example.run;

import org.example.model.Historique;
import org.example.model.Programmeur;
import org.example.model.Action;
import org.example.database.ActionsBDD;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * Classe représentant le menu principal de l'application, permettant à l'utilisateur
 * d'interagir avec les données des programmeurs.
 *
 * @author Mehedi Touré & Adil Chetouni
 * @version 1.0
 * @since   1.0
 */
public class StartProjet extends StartProgram {

    /**
     * Gestionnaire d’historique
     */
    private final Historique historique;

    /**
     * Constructeur principal pour initialiser le menu avec le service de programmeurs et le scanner.
     *
     * @param programmeurService Le service permettant d'effectuer des actions sur les programmeurs.
     * @param scanner            Le scanner pour lire les entrées utilisateur.
     * @param historique         L'historique des actions.
     */
    public StartProjet(ActionsBDD programmeurService, Scanner scanner, Historique historique) {
        super(programmeurService, scanner, false);
        this.historique = historique;
    }

    /**
     * Constructeur secondaire pour initialiser le menu avec le service de programmeurs, le scanner et savoir si c'est en mode test.
     *
     * @param programmeurService Le service permettant d'effectuer des actions sur les programmeurs.
     * @param scanner            Le scanner pour lire les entrées utilisateur.
     * @param historique         L'historique des actions.
     * @param isTestMode         Mode test ou non.
     */
    public StartProjet(ActionsBDD programmeurService, Scanner scanner, Historique historique, boolean isTestMode) {
        super(programmeurService, scanner, isTestMode);
        this.historique = historique;
    }

    /**
     * Méthode permettant de modifier le salaire d'un programmeur.
     */
    protected void updateSalaire() {
        System.out.println("<<<<<<<<<< Modification du salaire >>>>>>>>>>\n");
        int id = super.menu.getInputInt("Entrez l'ID du programmeur : ");
        Programmeur p = programmeurService.findOne(id);

        if (p == null) {
            System.out.println("Programmeur non trouvé.");
        } else {
            float salaire = super.menu.getInputFloat("Nouveau salaire : ");
            p.setSalaire(salaire);
            programmeurService.update(p);

            // Ajout d’une action à l’historique
            String currentDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            Action action = new Action(
                    "Modification du salaire du programmeur (ID=" + p.getId() + ")",
                    currentDate,
                    p
            );
            historique.ajouterAction(action);
        }
    }

    /**
     * Méthode permettant de modifier les attributs d'un programmeur.
     */
    protected void updateProgrammer() {
        System.out.println("<<<<<<<<<< Modification d'un programmeur >>>>>>>>>>\n");
        int id = super.menu.getInputInt("Entrez l'ID du programmeur : ");
        Programmeur p = programmeurService.findOne(id);

        if (p == null) {
            System.out.println("Programmeur non trouvé.");
            return;
        }

        int choix;
        boolean isModified = false;

        do {
            Menu.afficherSousMenuModification();
            choix = super.menu.getInputInt("Entrez votre choix : ");

            switch (choix) {
                case 1:
                    String nom = super.menu.getInputString("Nouveau nom : ");
                    p.setNom(nom);
                    isModified = true;
                    break;
                case 2:
                    String prenom = super.menu.getInputString("Nouveau prénom : ");
                    p.setPrenom(prenom);
                    isModified = true;
                    break;
                case 3:
                    String adresse = super.menu.getInputString("Nouvelle adresse : ");
                    p.setAdresse(adresse);
                    isModified = true;
                    break;
                case 4:
                    String pseudo = super.menu.getInputString("Nouveau pseudo : ");
                    p.setPseudo(pseudo);
                    isModified = true;
                    break;
                case 5:
                    int responsable = super.menu.getInputInt("Nouveau responsable : ");
                    p.setResponsable(responsable);
                    isModified = true;
                    break;
                case 6:
                    String hobby = super.menu.getInputString("Nouveau hobby : ");
                    p.setHobby(hobby);
                    isModified = true;
                    break;
                case 7:
                    int anNaissance = super.menu.getInputInt("Nouvelle année de naissance : ");
                    p.setAnNaissance(anNaissance);
                    isModified = true;
                    break;
                case 8:
                    float salaire = super.menu.getInputFloat("Nouveau salaire : ");
                    p.setSalaire(salaire);
                    isModified = true;
                    break;
                case 9:
                    float prime = super.menu.getInputFloat("Nouvelle prime : ");
                    p.setPrime(prime);
                    isModified = true;
                    break;
                case 10:
                    System.out.println("Retour au menu principal.");
                    break;
                default:
                    System.out.println("Choix invalide.");
            }

            // Si au moins un champ a été modifié, on met à jour en base
            if (isModified) {
                programmeurService.update(p);
                isModified = false;

                // Enregistrer l’action dans l’historique
                String currentDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                Action action = new Action(
                        "Modification d'un programmeur (ID=" + p.getId() + "), champ " + choix,
                        currentDate,
                        p
                );
                historique.ajouterAction(action);
            }

        } while (choix != 10);
    }

    /**
     * Méthode permettant d'afficher les détails d'un programmeur.
     */
    protected void showProgrammer() {
        System.out.println("<<<<<<<<<< Affichage d'un programmeur >>>>>>>>>>\n");
        int id = super.menu.getInputInt("Entrez l'ID du programmeur : ");
        Programmeur p = programmeurService.findOne(id);

        // Optionnel : Enregistrer l’action dans l’historique (lecture)
        String currentDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        Action action = new Action(
                "Affichage du programmeur (ID=" + id + ")",
                currentDate
        );
        historique.ajouterAction(action);

        System.out.println(p);
    }

    /**
     * Méthode permettant d'ajouter un nouveau programmeur.
     */
    protected void addProgrammer() {
        System.out.println("<<<<<<<<<< Ajout d'un programmeur >>>>>>>>>>\n");
        Programmeur programmeur = new Programmeur();

        programmeur.setNom(super.menu.getInputString("Nom : "));
        programmeur.setPrenom(super.menu.getInputString("Prénom : "));
        programmeur.setAdresse(super.menu.getInputString("Adresse : "));
        programmeur.setPseudo(super.menu.getInputString("Pseudo : "));
        int resp;
        while (true){
            potentientialResponsable();
            resp = super.menu.getInputInt("Responsable Id (tapper -1 si vous ne voulez pas) : ");
            if (resp == -1)
                break;
            if (programmeurService.findOne(resp) != null)
                System.out.println("Responsable non trouvé.");

        }

        programmeur.setResponsable(resp);

        programmeur.setHobby(super.menu.getInputString("Hobby : "));
        programmeur.setAnNaissance(super.menu.getInputInt("Année de naissance : "));
        programmeur.setSalaire(super.menu.getInputFloat("Salaire : "));
        programmeur.setPrime(super.menu.getInputFloat("Prime : "));

        programmeurService.add(programmeur);

        // Enregistrer l’action dans l’historique
        String currentDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        Action action = new Action(
                "Ajout d'un nouveau programmeur (ID=" + programmeur.getId() + ")",
                currentDate,
                programmeur
        );
        historique.ajouterAction(action);
    }

    /**
     * Méthode permettant d'afficher tous les programmeurs mais les infos juste pour mettre en tant qe responsable.
     */
    private void potentientialResponsable() {
        System.out.println("Potentiels responsables : ");
        List<Programmeur> list = programmeurService.findAll();
        int i = 0;
        for (Programmeur p : list) {
            if (p != null) System.out.print("\tId: " + p.getId() + " Nom: " + p.getNom() + " Prenom: " + p.getPrenom());
            if (i % 2 == 0) System.out.println();
            i++;
        }
    }

    /**
     * Méthode permettant d'afficher tous les programmeurs.
     */
    protected void showProgrammers() {
        System.out.println("<<<<<<<<<< Affichage de la liste des programmeurs >>>>>>>>>>\n");

        programmeurService.findAll().forEach(System.out::println);

        // Optionnel : Historiser l’action (affichage global)
        String currentDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        Action action = new Action("Affichage de tous les programmeurs", currentDate);
        historique.ajouterAction(action);
    }

    /**
     * Méthode permettant de supprimer un programmeur.
     */
    protected void removeProgrammer() {
        System.out.println("<<<<<<<<<< Suppression d'un programmeur >>>>>>>>>>\n");
        int id = super.menu.getInputInt("Entrez l'ID du programmeur : ");
        Programmeur p = programmeurService.findOne(id);
        if (p == null) {
            System.out.println("Programmeur non trouvé.");
        } else {
            programmeurService.delete(p);
            System.out.println("Programmeur supprimé avec succès.");

            // Enregistrer l’action dans l’historique
            String currentDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            Action action = new Action(
                    "Suppression du programmeur (ID=" + p.getId() + ")",
                    currentDate,
                    p
            );
            historique.ajouterAction(action);
        }
    }

    /**
     * Méthode permettant de trouver un programmeur par son pseudo.
     */
    protected void findByPseudo() {
        System.out.println("<<<<<<<<<< Recherche d'un programmeur par pseudo >>>>>>>>>>\n");
        System.out.print("Entrez le pseudo du programmeur : ");
        String pseudo = super.menu.getInputString("Entrez le pseudo du programmeur : ");
        Programmeur p = programmeurService.findByPseudo(pseudo);

        // Optionnel : Historiser la recherche
        String currentDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        Action action = new Action(
                "Recherche d'un programmeur par pseudo (" + pseudo + ")",
                currentDate
        );
        historique.ajouterAction(action);

        if (p == null) {
            System.out.println("Programmeur non trouvé.");
        } else {
            System.out.println(p);
        }
    }

    /**
     * Getter de l’historique.
     */
    public Historique getHistorique() {
        return historique;
    }
}
