package org.example.ui;

import javax.swing.*;
import java.awt.*;

public class SwingExample extends JFrame {
    public SwingExample() {
        super("My Swing App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create UI components
        JButton btnShowMessage = new JButton("Show Message");
        JLabel label = new JLabel("SGBD Project - Adil & Mehédi");

        // Add event listener
        btnShowMessage.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Hello from Swing!");
        });

        // Arrange components in the frame
        setLayout(new FlowLayout());
        add(label);
        add(btnShowMessage);

        // Size and display
        setSize(800, 400);
        setVisible(true);
    }

    public static void main(String[] args) {
        // Run on the Event Dispatch Thread
        SwingUtilities.invokeLater(SwingExample::new);
    }
}
