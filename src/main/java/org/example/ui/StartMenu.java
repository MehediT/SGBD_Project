package org.example.ui;

import java.util.Scanner;

public class StartMenu {

    private final IProgrammeurService serviceBDD;

    public StartMenu(IProgrammeurService actionsBDD) {
        this.serviceBDD = actionsBDD;
    }

    public void run(){
        int choix;
        do {
            afficherMenu();
            choix = saisirChoix();
            switch (choix) {
                case 1:
//                    System.out.println("Afficher la liste des programmeurs");
                    serviceBDD.findAll().forEach(System.out::println);
                    break;
                case 2:
                    removeProgrammer();
                    break;
                case 3:
                    System.out.println("Supprimer un programmeur");
                    break;
                case 4:
                    System.out.println("Ajouter un programmeur");
                    break;
                case 5:
                    System.out.println("Modifier le salaire");
                    break;
                case 6:
                    System.out.println("Quitter le programme");
                    break;
                default:
                    System.out.println("Choix invalide");
            }
        } while (choix != 5);
    }

    private void removeProgrammer() {
        System.out.print("Entrez l'ID du programmeur à supprimer : ");
        Scanner scanner = new Scanner(System.in);
        int id = scanner.nextInt();
        boolean success = serviceBDD.supprimerParId(id);
        System.out.println(success ? "Programmeur supprimé avec succès." : "Échec de la suppression.");
    }

    private int saisirChoix() {
        System.out.println("Quel est votre choix ? : ");
        Scanner scanner = new Scanner(System.in);
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
