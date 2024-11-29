package org.example;

import org.example.service.ProgrammeurService;

import org.example.ui.StartMenu;

public class Main {
    public static void main(String[] args) {
        System.out.printf("SGBD Project\nAuthor: %s, %s\n", "Adil Chetouani", "Mehédi Touré");

//        StartMenu startMenu = new StartMenu();
//        startMenu.run();
        ProgrammeurService programmeurService = new ProgrammeurService();
        System.out.println(programmeurService.findAll());
    }
}