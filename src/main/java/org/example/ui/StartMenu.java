package org.example.ui;

import org.example.exception.SQLConnectionException;
import org.example.exception.SQLFailAdd;
import org.example.exception.SQLFailDelete;
import org.example.exception.SQLFailQuery;
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
            choix = getByScannerInt();
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
            programmeurService.update(p);
        }
    }

    private void showProgrammer() {
        System.out.print("Entrez l'ID du programmeur : ");
        int id = scanner.nextInt();
        Programmeur p = programmeurService.findOne(id);
        System.out.println(p);
    }

    private void addProgrammer() {
        Programmeur programmeur = new Programmeur();
        System.out.print("Nom : ");
        programmeur.setNom(scanner.next());
        System.out.print("Prénom : ");
        programmeur.setPrenom(scanner.next());
        System.out.print("Pseudo : ");
        programmeur.setPseudo(scanner.next());
        System.out.print("Responsable : ");
        programmeur.setResponsable(scanner.next());
        System.out.print("Hobby : ");
        programmeur.setHobby(scanner.next());
        System.out.print("Année de naissance : ");
        programmeur.setAnNaissance(getByScannerInt());
        System.out.print("Salaire : ");
        programmeur.setSalaire(getByScannerInt());
        System.out.print("Prime : ");
        programmeur.setPrime(getByScannerInt());

        programmeurService.add(programmeur);
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
            programmeurService.delete(p);
            System.out.println("Programmeur supprimé avec succès.");
        }
    }

    private int getByScannerInt() {
        System.out.println("Quel est votre choix ? : ");
        if (scanner.hasNextInt()) {
            return scanner.nextInt();
        }
        return 0;
    }

    public static void afficherMenu() {
        System.out.println("<<<<<<<<<< Menu >>>>>>>>>>");
        System.out.println("1. Afficher la liste des programmeurs\n");
        System.out.println("2. Afficher un programmeur\n");
        System.out.println("3. Supprimer un programmeur\n");
        System.out.println("4. Ajouter un programmeur\n");
        System.out.println("5. Modifier le salaire\n");
        System.out.println("6. Quitter le programme\n");
    }
}
