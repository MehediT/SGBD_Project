package org.example.run;

import org.example.model.Historique;
import org.example.service.ProgrammeurService;

import java.util.Scanner;

/**
 * Classe de test simulant les interactions utilisateur dans le menu principal de l'application.
 * <p>
 * Cette classe permet de tester toutes les fonctionnalités du menu, telles que l'ajout de programmeurs,
 * l'affichage des programmeurs, la suppression d'un programmeur, la modification du salaire et l'affichage d'un programmeur spécifique.
 * Les entrées utilisateur sont simulées à l'aide d'une chaîne de caractères qui remplace l'entrée standard.
 * </p>
 */
public class StartTest extends StartMenu {

    public StartTest() {
        super(new ProgrammeurService(), initScanner(), new Historique());
    }

    /**
     * Méthode principale pour tester les fonctionnalités du menu.
     * Cette méthode simule les entrées utilisateur et exécute le menu en utilisant un service fictif de programmeurs.
     */
    public static Scanner initScanner() {
        // Simuler les entrées utilisateur pour tester toutes les fonctionnalités

        String addUser1 = "4\nJohn\nDoe\n16bis-rue-de-Paris\njohndoe\nManager\nFootball\n1985\n55000\n5000";
        String addUser2 = "4\nJane\nSmith\n57-avenue-colombe-gorki\njanesmith\nLead\nReading\n1990\n60000\n7000";
        String addUser3 = "4\nAlice\nBrown\n14-boulevard-jane-gorki\naliceb\nSenior\nChess\n1988\n58000\n6000";
        String show = "\n1";
        String removeId1 = "\n3\n1";
        String showProgrammer2 = "\n2\n2";
        String updateSalaire = "\n5\n1\n70000";
        String end = "\n6";

        String simulatedInput = show + "\n" + addUser1 + "\n" + addUser2 + "\n" + addUser3 + "\n" + show + "\n" + removeId1 + "\n" + show + "\n" + showProgrammer2 + "\n" + updateSalaire + "\n" + show + "\n" + end;

        return new Scanner(simulatedInput);
    }
}
