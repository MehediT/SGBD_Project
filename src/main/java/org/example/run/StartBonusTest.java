package org.example.run;

import org.example.model.Historique;
import org.example.database.ActionsBDDImpl;

import java.util.Scanner;

/**
 * Classe de test simulant les interactions utilisateur dans le menu principal de l'application.
 * <p>
 * Cette classe permet de tester toutes les fonctionnalités du menu, telles que l'ajout de programmeurs,
 * l'affichage des programmeurs, la suppression d'un programmeur, la modification du salaire et l'affichage d'un programmeur spécifique.
 * Les entrées utilisateur sont simulées à l'aide d'une chaîne de caractères qui remplace l'entrée standard.
 * </p>
 *
 * @author Mehedi Touré & Adil Chetouni
 * @version 1.0
 * @since   1.0
 */
public class StartBonusTest extends StartProjet {

    public StartBonusTest(ActionsBDDImpl actionsBDDImpl) {
        super(actionsBDDImpl, initScanner(), new Historique(), true);

        System.out.println("Le test automatique à pour but.");
        System.out.println("1. D'afficher la liste des programmeurs.");
        System.out.println("2. D'ajouter 3 programmeurs.");
        System.out.println("3. De supprimer le premier programmeur.");
        System.out.println("4. D'afficher la liste des programmeurs.");
        System.out.println("5. D'afficher le programmeur 2.");
        System.out.println("6. De mettre à jour le salaire du sprogrammeur 1.");
        System.out.println("7. D'afficher la liste des programmeurs.");
        System.out.println("8. De quitter le programme.\n");
    }

    /**
     * Méthode principale pour tester les fonctionnalités du menu.
     * Cette méthode simule les entrées utilisateur et exécute le menu en utilisant un service fictif de programmeurs.
     */
    public static Scanner initScanner() {
        // Simuler les entrées utilisateur pour tester toutes les fonctionnalités

        String addUser1 = "4\nJohn\nDoe\n16bis-rue-de-Paris\njohndoe\n-1\nManager Football\n1985\n99999,99\n50,93";
        String addUser2 = "4\nJane\nSmith\n57-avenue-colombe-gorki\njanesmith\n-1\nLead Reading\n1990\n60,5000\n700,70";
        String addUser3 = "4\nAlice\nBrown\n14-boulevard-jane-gorki\naliceb\n-1\nSenior Chess\n1988\n580,40\n600,4";

        String show = "1";
        String removeId1 = "3\n1";
        String showProgrammer2 = "2\n2";
        String updateSalaire = "5\n1\n70000.0";
        String end = "6";

        String simulatedInput = show + "\n" + addUser1 + "\n" + addUser2 + "\n" + addUser3 + "\n" + show + "\n" + removeId1 + "\n" + show + "\n" + showProgrammer2 + "\n" + updateSalaire + "\n" + show + "\n" + end + "\n";

        return new Scanner(simulatedInput);
    }
}
