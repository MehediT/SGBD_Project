package org.example;

import org.example.run.StartMenu;
import org.example.service.ProgrammeurService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.printf("SGBD Project\nAuthor: %s, %s\n", "Adil Chetouani", "Mehédi Touré");

        StartMenu startMenu = new StartMenu(new ProgrammeurService(), new Scanner(System.in));
        startMenu.run();

        // Pour tester le menu decommente les lignes suivantes
        // StartMenu startMenuTest = new StartTest();
        // startMenuTest.run();
    }
}