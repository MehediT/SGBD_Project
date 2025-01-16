package org.example.run;

import java.util.Scanner;

/**
 * Classe permettant de gérer la saisie de l'utilisateur et le déclenchement des actions
 * correspondant au choix saisi.
 *
 * @author Mehedi Touré & Adil Chetouni
 * @version 1.0
 */
public class Menu{
    /**
     * Flux de lecture pour les entrées utilisateur.
     */
    protected final Scanner scanner;

    private boolean isTest;

    /**
     * Constructeur par défaut initialisant le scanner pour les entrées utilisateur.
     */
    public Menu(Scanner scanner, boolean isTest) {
        this.scanner = scanner;
        this.isTest = isTest;
    }

    /**
     * Affiche un sous-menu pour la modification des différentes informations d'un programmeur
     * (nom, prénom, adresse, pseudo, etc.).
     */
    public static void afficherSousMenuModification() {
        System.out.println("\n<<<<<<<<<< Sous-menu Modification >>>>>>>>>>");
        System.out.println("1. Modifier le nom");
        System.out.println("2. Modifier le prénom");
        System.out.println("3. Modifier l'adresse");
        System.out.println("4. Modifier le pseudo");
        System.out.println("5. Modifier le responsable");
        System.out.println("6. Modifier le hobby");
        System.out.println("7. Modifier l'année de naissance");
        System.out.println("8. Modifier le salaire");
        System.out.println("9. Modifier la prime");
        System.out.println("10. Retour au menu principal");
    }

    /**
     * Affiche le menu principal avec les options disponibles (1 à 8),
     * ainsi que la possibilité de quitter (option 6).
     */
    public static void afficherMenu() {
        System.out.println("<<<<<<<<<< Menu >>>>>>>>>>");
        System.out.println("1. Afficher la liste des programmeurs\n");
        System.out.println("2. Afficher un programmeur\n");
        System.out.println("3. Supprimer un programmeur\n");
        System.out.println("4. Ajouter un programmeur\n");
        System.out.println("5. Modifier le salaire\n");
        System.out.println("6. Quitter le programme\n");

        System.out.println("Bonus");
        System.out.println("7. Afficher un programmeur via son pseudo");
        System.out.println("8. Modifier un programmeur");
    }

    /**
     * Méthode utilitaire pour obtenir un entier à partir de l'entrée de l'utilisateur.
     * <ul>
     *     <li>Affiche d'abord un message passé en paramètre.</li>
     *     <li>Valide que l'entrée est bien un entier.</li>
     *     <li>Retourne cet entier.</li>
     * </ul>
     *
     * @param message Le message à afficher avant de demander l'entrée utilisateur.
     * @return L'entier saisi par l'utilisateur.
     */
    public int getInputInt(String message) {
        System.out.print(message);
        while (!scanner.hasNextInt()) {
            System.out.print("Veuillez entrer un nombre valide." +message);
            scanner.next();
        }
        int input = scanner.nextInt();
        scanner.nextLine(); // Pour consommer le retour à la ligne
        if(isTest) {
            System.out.print(input+"\n");
        }
        return input;
    }

    /**
     * Méthode utilitaire pour obtenir un réel à partir de l'entrée de l'utilisateur.
     * <ul>
     *     <li>Affiche d'abord un message passé en paramètre.</li>
     *     <li>Valide que l'entrée est bien un réel.</li>
     *     <li>Retourne ce réel.</li>
     * </ul>
     *
     * @param message Le message à afficher avant de demander l'entrée utilisateur.
     * @return Le réel saisi par l'utilisateur.
     */
    public float getInputFloat(String message) {
        System.out.print(message);
        while (!scanner.hasNextFloat()) {
            System.out.print("Veuillez entrer un valeur valide." +message);
            scanner.next();
        }
        float input = scanner.nextFloat();
        scanner.nextLine(); // Pour consommer le retour à la ligne
        if(isTest) {
            System.out.print(input+"\n");
        }
        return input;
    }

    /**
     * Méthode utilitaire pour obtenir une chaîne de caractères à partir
     * de l'entrée de l'utilisateur.
     * <ul>
     *     <li>Affiche d'abord un message passé en paramètre.</li>
     *     <li>Valide que l'entrée n'est pas vide.</li>
     *     <li>Retourne la chaîne saisie.</li>
     * </ul>
     *
     * @param message Le message à afficher avant de demander l'entrée utilisateur.
     * @return La chaîne de caractères saisie par l'utilisateur (non vide).
     */
    public String getInputString(String message) {
        System.out.print(message);
        String input = scanner.nextLine();
        while (input.trim().isEmpty()) {
            System.out.println("L'entrée ne peut pas être vide. Veuillez réessayer.");
            System.out.print(message);
            input = scanner.nextLine();
        }
        if(isTest) {
            System.out.print(input+"\n");
        }
        return input;
    }
}
