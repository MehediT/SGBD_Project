package org.example.run;

import org.example.model.Historique;
import org.example.model.Programmeur;
import org.example.model.Action;
import org.example.service.IProgrammeurService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * Classe représentant le menu principal de l'application, permettant à l'utilisateur
 * d'interagir avec les données des programmeurs.
 */
public class StartMenu extends StartProgram {

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
    public StartMenu(IProgrammeurService programmeurService, Scanner scanner, Historique historique) {
        super(programmeurService, scanner);
        this.historique = historique;
    }

    /**
     * Méthode permettant de modifier le salaire d'un programmeur.
     */
    protected void updateSalaire() {
        System.out.println("<<<<<<<<<< Modification du salaire >>>>>>>>>>\n");
        int id = super.getInputInt("Entrez l'ID du programmeur : ");
        Programmeur p = programmeurService.findOne(id);

        if (p == null) {
            System.out.println("Programmeur non trouvé.");
        } else {
            int salaire = super.getInputInt("Nouveau salaire : ");
            p.setSalaire(salaire);
            programmeurService.update(p);

            // Ajout d’une action à l’historique
            String currentDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            Action action = new Action(
                    "Modification du salaire du programmeur (ID=" + p.getId() + ")",
                    currentDate,
                    p
            );
            historique.addHistorique(action);
        }
    }

    /**
     * Méthode permettant de modifier les attributs d'un programmeur.
     */
    protected void updateProgrammer() {
        System.out.println("<<<<<<<<<< Modification d'un programmeur >>>>>>>>>>\n");
        int id = super.getInputInt("Entrez l'ID du programmeur : ");
        Programmeur p = programmeurService.findOne(id);

        if (p == null) {
            System.out.println("Programmeur non trouvé.");
            return;
        }

        int choix;
        boolean isModified = false;

        do {
            afficherSousMenuModification();
            choix = super.getInputInt("Entrez votre choix : ");

            switch (choix) {
                case 1:
                    String nom = super.getInputString("Nouveau nom : ");
                    p.setNom(nom);
                    isModified = true;
                    break;
                case 2:
                    String prenom = super.getInputString("Nouveau prénom : ");
                    p.setPrenom(prenom);
                    isModified = true;
                    break;
                case 3:
                    String adresse = super.getInputString("Nouvelle adresse : ");
                    p.setAdresse(adresse);
                    isModified = true;
                    break;
                case 4:
                    String pseudo = super.getInputString("Nouveau pseudo : ");
                    p.setPseudo(pseudo);
                    isModified = true;
                    break;
                case 5:
                    String responsable = super.getInputString("Nouveau responsable : ");
                    p.setResponsable(responsable);
                    isModified = true;
                    break;
                case 6:
                    String hobby = super.getInputString("Nouveau hobby : ");
                    p.setHobby(hobby);
                    isModified = true;
                    break;
                case 7:
                    int anNaissance = super.getInputInt("Nouvelle année de naissance : ");
                    p.setAnNaissance(anNaissance);
                    isModified = true;
                    break;
                case 8:
                    int salaire = super.getInputInt("Nouveau salaire : ");
                    p.setSalaire(salaire);
                    isModified = true;
                    break;
                case 9:
                    int prime = super.getInputInt("Nouvelle prime : ");
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
                historique.addHistorique(action);
            }

        } while (choix != 10);
    }

    /**
     * Méthode permettant d'afficher les détails d'un programmeur.
     */
    protected void showProgrammer() {
        System.out.println("<<<<<<<<<< Affichage d'un programmeur >>>>>>>>>>\n");
        int id = super.getInputInt("Entrez l'ID du programmeur : ");
        Programmeur p = programmeurService.findOne(id);

        // Optionnel : Enregistrer l’action dans l’historique (lecture)
        String currentDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        Action action = new Action(
                "Affichage du programmeur (ID=" + id + ")",
                currentDate
        );
        historique.addHistorique(action);

        System.out.println(p);
    }

    /**
     * Méthode permettant d'ajouter un nouveau programmeur.
     */
    protected void addProgrammer() {
        System.out.println("<<<<<<<<<< Ajout d'un programmeur >>>>>>>>>>\n");
        Programmeur programmeur = new Programmeur();

        System.out.print("Nom : ");
        programmeur.setNom(scanner.next());
        System.out.print("Prénom : ");
        programmeur.setPrenom(scanner.next());
        System.out.print("Adresse : ");
        programmeur.setAdresse(scanner.next());
        System.out.print("Pseudo : ");
        programmeur.setPseudo(scanner.next());
        System.out.print("Responsable : ");
        programmeur.setResponsable(scanner.next());
        System.out.print("Hobby : ");
        programmeur.setHobby(scanner.next());

        programmeur.setAnNaissance(super.getInputInt("Année de naissance : "));
        programmeur.setSalaire(super.getInputInt("Salaire : "));
        programmeur.setPrime(super.getInputInt("Prime : "));

        programmeurService.add(programmeur);

        // Enregistrer l’action dans l’historique
        String currentDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        Action action = new Action(
                "Ajout d'un nouveau programmeur (ID=" + programmeur.getId() + ")",
                currentDate,
                programmeur
        );
        historique.addHistorique(action);
    }

    /**
     * Méthode permettant d'afficher tous les programmeurs.
     */
    protected void showProgrammers() {
        System.out.println("<<<<<<<<<< Affichage de la liste des programmeurs >>>>>>>>>>\n");

        // Optionnel : Historiser l’action (affichage global)
        String currentDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        Action action = new Action("Affichage de tous les programmeurs", currentDate);
        historique.addHistorique(action);

        programmeurService.findAll().forEach(System.out::println);
    }

    /**
     * Méthode permettant de supprimer un programmeur.
     */
    protected void removeProgrammer() {
        System.out.println("<<<<<<<<<< Suppression d'un programmeur >>>>>>>>>>\n");
        System.out.print("Entrez l'ID du programmeur : ");
        int id = scanner.nextInt();
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
            historique.addHistorique(action);
        }
    }

    /**
     * Méthode permettant de trouver un programmeur par son pseudo.
     */
    protected void findByPseudo() {
        System.out.println("<<<<<<<<<< Recherche d'un programmeur par pseudo >>>>>>>>>>\n");
        System.out.print("Entrez le pseudo du programmeur : ");
        String pseudo = scanner.next();
        Programmeur p = programmeurService.findByPseudo(pseudo);

        // Optionnel : Historiser la recherche
        String currentDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        Action action = new Action(
                "Recherche d'un programmeur par pseudo (" + pseudo + ")",
                currentDate
        );
        historique.addHistorique(action);

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

    /**
     * Méthode d'affichage du sous-menu de modification (facteur commun).
     */
    public void afficherSousMenuModification() {
        System.out.println("1. Modifier le nom");
        System.out.println("2. Modifier le prénom");
        System.out.println("3. Modifier l'adresse");
        System.out.println("4. Modifier le pseudo");
        System.out.println("5. Modifier le responsable");
        System.out.println("6. Modifier le hobby");
        System.out.println("7. Modifier l'année de naissance");
        System.out.println("8. Modifier le salaire");
        System.out.println("9. Modifier la prime");
        System.out.println("10. Retour");
    }
}
