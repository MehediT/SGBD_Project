package org.example.run;

import org.example.model.Programmeur;
import org.example.service.IProgrammeurService;

import java.util.Scanner;

/**
 * Classe représentant le menu principal de l'application, permettant à l'utilisateur d'interagir avec
 * les données des programmeurs (ajouter, supprimer, afficher, mettre à jour).
 * <p>
 * Cette classe offre un menu interactif dans le terminal, permettant de réaliser différentes actions
 * concernant les programmeurs, telles que l'ajout, la suppression ou la modification du salaire.
 * </p>
 */
public class StartMenu extends StartProgram {

    /**
     * Constructeur principal pour initialiser le menu avec le service de programmeurs et le scanner.
     *
     * @param programmeurService Le service permettant d'effectuer des actions sur les programmeurs dans la base de données.
     */
    public StartMenu(IProgrammeurService programmeurService, Scanner scanner) {
        super(programmeurService, scanner);
    }

    /**
     * Méthode permettant de modifier le salaire d'un programmeur en spécifiant son ID.
     */
    
    protected void updateSalaire() {
        System.out.println("<<<<<<<<<< Modification du salaire >>>>>>>>>>\n");
        int id = super.getByScannerInt("Entrez l'ID du programmeur : ");
        Programmeur p = programmeurService.findOne(id);
        if (p == null) {
            System.out.println("Programmeur non trouvé.");
        } else {
            int salaire = super.getByScannerInt("Nouveau salaire : ");
            p.setSalaire(salaire);
            programmeurService.update(p);
        }
    }

    /**
     * Méthode permettant d'afficher les détails d'un programmeur spécifique en fonction de son ID.
     */
    protected void showProgrammer() {
        System.out.println("<<<<<<<<<< Affichage d'un programmeur >>>>>>>>>>\n");
        int id = super.getByScannerInt("Entrez l'ID du programmeur : ");
        Programmeur p = programmeurService.findOne(id);
        System.out.println(p);
    }

    /**
     * Méthode permettant d'ajouter un nouveau programmeur en entrant les informations nécessaires.
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

        programmeur.setAnNaissance(super.getByScannerInt("Année de naissance : "));
        programmeur.setSalaire(super.getByScannerInt("Salaire : "));
        programmeur.setPrime(super.getByScannerInt("Prime : "));

        programmeurService.add(programmeur);
    }

    /**
     * Méthode permettant d'afficher tous les programmeurs.
     */
    protected void showProgrammers() {
        System.out.println("<<<<<<<<<< Affichage de la liste des programmeurs >>>>>>>>>>\n");
        programmeurService.findAll().forEach(System.out::println);
    }

    /**
     * Méthode permettant de supprimer un programmeur en fonction de son ID.
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
        }
    }

    protected void findByPseudo() {
        System.out.println("<<<<<<<<<< Recherche d'un programmeur par pseudo >>>>>>>>>>\n");
        System.out.print("Entrez le pseudo du programmeur : ");
        String pseudo = scanner.next();
        Programmeur p = programmeurService.findByPseudo(pseudo);
        if (p == null) {
            System.out.println("Programmeur non trouvé.");
        } else {
            System.out.println(p);
        }
    }
}
