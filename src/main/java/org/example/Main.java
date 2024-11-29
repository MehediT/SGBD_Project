package org.example;

import org.example.service.ProgrammeurService;

public class Main {
    public static void main(String[] args) {
        ProgrammeurService programmeurService = new ProgrammeurService();
        System.out.println(programmeurService.findAll());
    }
}