package org.example;

import org.example.run.StartMenu;
import org.example.service.ProgrammeurService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.printf("SGBD Project\nAuthor: %s, %s\n", "Adil Chetouani", "Mehédi Touré");
        ProgrammeurService programmeurService = new ProgrammeurService();
        // System.out.println(programmeurService.findAll());
        StartMenu startMenu = new StartMenu(programmeurService);
        startMenu.run();
    }
}