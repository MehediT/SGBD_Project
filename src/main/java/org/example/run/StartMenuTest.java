package org.example.run;
import org.example.service.IProgrammeurService;
import org.example.service.ProgrammeurService;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

public class StartMenuTest {
    public static void main(String[] args) {
        // Simuler les entrées utilisateur pour tester toutes les fonctionnalités

        String addUser1 = """
                4
                John
                Doe
                johndoe
                Manager
                Football
                1985
                55000
                5000
                """;
        String addUser2 = """
                4
                Jane
                Smith
                janesmith
                Lead
                Reading
                1990
                60000
                7000
                """;

        String addUser3 = """
                4
                Alice
                Brown
                aliceb
                Senior
                Chess
                1988
                58000
                6000
                """;
        String show = "\n1";
        String removeId1 = "\n3\n1";
        String showProgrammer2 = "\n2\n2";
        String updateSalaire = "\n5\n1\n70000";
        String end = "\n6";

        String simulatedInput =
                addUser1 +
                addUser2 +
                addUser3 +
                show +
                removeId1 +
                show +
                showProgrammer2 +
                updateSalaire +
                show +
                end;

        // Démarrer le menu
        IProgrammeurService programmeurService = new ProgrammeurService(); // Service fictif
        StartMenu startMenu = new StartMenu(programmeurService, new Scanner(simulatedInput));
        startMenu.run();
    }
}
