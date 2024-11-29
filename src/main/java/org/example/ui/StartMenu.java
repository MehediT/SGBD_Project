package org.example.ui;

import org.example.model.Programmeur;
import org.example.service.IProgrammeurService;

import java.util.Scanner;

public class StartMenu {

    private final IProgrammeurService programmeurService;
    private final Scanner scanner = new Scanner(System.in);

    public StartMenu(IProgrammeurService actionsBDD) {
        this.programmeurService = actionsBDD;
    }

    public void run(){
        int choix;
        do {
            afficherMenu();
            choix = saisirChoix();
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
                default:
                    System.out.println("Choix invalide");
            }
        } while (choix != 6);
    }

    private void updateSalaire() {
        System.out.print("Entrez l'ID du programmeur : ");
        int id = scanner.nextInt();
        Programmeur p = programmeurService.findOne(id);
        if (p == null){
            System.out.println("Programmeur non trouvé.");
        }else {
            System.out.print("Nouveau salaire : ");
            int salaire = scanner.nextInt();
            p.setSalaire(salaire);
            p = programmeurService.update(id, p);
            if (p == null){
                System.out.println("Échec de la mise à jour du salaire.");
            }else {
                System.out.println("Programmeur supprimé avec succès.");
            }
        }
    }
    private void showProgrammer() {
        System.out.print("Entrez l'ID du programmeur : ");
        int id = scanner.nextInt();
        Programmeur p = programmeurService.findOne(id);
        if (p == null){
            System.out.println("Programmeur non trouvé.");
        }else {
            System.out.println(p);
        }
    }

    private void addProgrammer() {
        System.out.print("Nom : ");
        String nom = scanner.next();
        System.out.print("Prénom : ");
        String prenom = scanner.next();
        System.out.print("Prénom : ");
        String adresse = scanner.next();
        System.out.print("Pseudo : ");
        String pseudo = scanner.next();
        System.out.print("Responsable : ");
        String responsable = scanner.next();
        System.out.print("Hobby : ");
        String hobby = scanner.next();
        System.out.print("Année de naissance : ");
        int anNaissance = scanner.nextInt();
        System.out.print("Salaire : ");
        int salaire = scanner.nextInt();
        System.out.print("Prime : ");
        int prime = scanner.nextInt();

        Programmeur p = new Programmeur(0, nom, prenom, adresse, pseudo, responsable, hobby, anNaissance, salaire, prime);

        System.out.println(programmeurService.add(p) != null ? "Programmeur ajouté avec succès." : "Échec de l'ajout.");
    }

    private void showProgrammers() {
        programmeurService.findAll().forEach(System.out::println);
    }

    private void removeProgrammer() {
        System.out.print("Entrez l'ID du programmeur : ");
        int id = scanner.nextInt();
        Programmeur p = programmeurService.findOne(id);
        if (p == null){
            System.out.println("Programmeur non trouvé.");
        }else {
            programmeurService.delete(id);
            System.out.println("Programmeur supprimé avec succès.");
        }
    }

    private int saisirChoix() {
        System.out.println("Quel est votre choix ? : ");
        if (scanner.hasNextInt()) {
            return scanner.nextInt();
        }
        return 0;
    }

    public void afficherMenu() {
        System.out.println("<<<<<<<<<< Menu >>>>>>>>>>");
        System.out.println("1. Afficher la liste des programmeurs\n");
        System.out.println("2. Afficher un programmeur\n");
        System.out.println("3. Supprimer un programmeur\n");
        System.out.println("4. Ajouter un programmeur\n");
        System.out.println("5. Modifier le salaire\n");
        System.out.println("6. Quitter le programme\n");
    }
}
