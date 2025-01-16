package org.example;

import org.example.model.Historique;
import org.example.run.StartBonusInterface;
import org.example.run.StartProjet;
import org.example.run.StartBonusTest;
import org.example.database.ActionsBDDImpl;

import java.util.Scanner;

public class Start {
    public static void main(String[] args) {
        System.out.printf("SGBD Project\nAuthor: %s, %s\n", "Adil Chetouani", "Mehédi Touré");

        int choix = 0;
        while (choix != 4){
            System.out.println("1. Projet");
            System.out.println("2. Bonus Test");
            System.out.println("3. Bonus Interface");
            System.out.println("4. Quitter");
            System.out.print("Votre choix: ");
            Scanner scanner = new Scanner(System.in);
            choix = scanner.nextInt();
            switch (choix){
                case 1:
                    projet();
                    break;
                case 2:
                    bonusTest();
                    break;
                case 3:
                    bonusInterface();
                    break;
                case 4:
                    System.out.println("Au revoir");
                    break;
                default:
                    System.out.println("Choix invalide");
            }
        }
    }

    private static void projet() {
        StartProjet start = new StartProjet(new ActionsBDDImpl(), new Scanner(System.in), new Historique());
        start.run();
    }

    private static void bonusTest() {
        StartBonusTest start = new StartBonusTest(new ActionsBDDImpl());
        start.run();
        System.out.println("Bonus Test terminé\n");
    }

    private static void bonusInterface() {
        // Test de l'interface graphique
        StartBonusInterface.run();
    }
}