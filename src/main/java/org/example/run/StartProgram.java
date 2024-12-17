package org.example.run;

import org.example.exception.SQLConnectionException;
import org.example.exception.SQLFailAdd;
import org.example.exception.SQLFailDelete;
import org.example.exception.SQLFailQuery;
import org.example.service.IProgrammeurService;

import java.util.Scanner;

public abstract class StartProgram {

    protected final IProgrammeurService programmeurService;
    protected final Scanner scanner;

    /**
     * Constructeur alternatif permettant de fournir un scanner personnalisé.
     *
     * @param programmeurService Le service permettant d'effectuer des actions sur les programmeurs dans la base de données.
     * @param scanner    Le scanner à utiliser pour obtenir des entrées utilisateur.
     */
    public StartProgram(IProgrammeurService programmeurService, Scanner scanner) {
        this.programmeurService = programmeurService;
        this.scanner = scanner;
    }

    /**
     * Méthode principale qui gère l'exécution du menu interactif.
     * Elle affiche le menu, prend les entrées de l'utilisateur et effectue les actions appropriées.
     */
    public void run() {
        int choix;
        do {
            afficherMenu();
            choix = getByScannerInt("Entrez votre choix : ");
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

    protected abstract void removeProgrammer();

    protected abstract void showProgrammer();

    protected abstract void addProgrammer();

    protected abstract void updateSalaire();

    protected abstract void showProgrammers();

    /**
     * Méthode utilitaire pour obtenir un entier à partir de l'entrée de l'utilisateur.
     *
     * @param message Le message à afficher avant de demander l'entrée utilisateur.
     * @return L'entier saisi par l'utilisateur.
     */
    public int getByScannerInt(String message) {
        System.out.println(message);
        while (!scanner.hasNextInt()) {
            System.out.println("Veuillez entrer un nombre.");
            scanner.next();
        }
        return scanner.nextInt();
    }

    /**
     * Affiche le menu principal avec les options disponibles.
     */
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
