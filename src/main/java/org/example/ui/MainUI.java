package org.example.ui;

import org.example.model.Action;
import org.example.model.Historique;
import org.example.model.Programmeur;
import org.example.service.IProgrammeurService;
import org.example.service.ProgrammeurService;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MainUI extends JFrame {
    private final IProgrammeurService serviceProgrammeur;
    private final Historique historique;

    private JTable tableHistorique; // Table pour afficher l'historique
    private JScrollPane panneauDefilementHistorique;

    public MainUI(IProgrammeurService serviceProgrammeur, Historique historique) {
        this.serviceProgrammeur = serviceProgrammeur;
        this.historique = historique;

        setTitle("Projet SGBD");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Ajouter la barre de menu
        setJMenuBar(creerBarreDeMenu());

        // Ajouter les panneaux
        add(creerPanneauListeProgrammeurs(), BorderLayout.CENTER);
        add(creerPanneauHistorique(), BorderLayout.SOUTH);

        setVisible(true);
    }

    private JMenuBar creerBarreDeMenu() {
        JMenuBar barreDeMenu = new JMenuBar();

        JMenuItem ajouterProgrammeur = new JMenuItem("Ajouter un Programmeur");
        ajouterProgrammeur.addActionListener(e -> afficherDialogueAjoutProgrammeur());
        barreDeMenu.add(ajouterProgrammeur);

        JMenuItem modifierProgrammeur = new JMenuItem("Modifier un Programmeur");
        modifierProgrammeur.addActionListener(e -> afficherDialogueModificationProgrammeur());
        barreDeMenu.add(modifierProgrammeur);

        JMenuItem supprimerProgrammeur = new JMenuItem("Supprimer un Programmeur");
        supprimerProgrammeur.addActionListener(e -> afficherDialogueSuppressionProgrammeur());
        barreDeMenu.add(supprimerProgrammeur);

        JMenuItem rafraichirListe = new JMenuItem("Rafraîchir la Liste");
        rafraichirListe.addActionListener( e ->
            {
                rafraichirListeProgrammeurs();
                rafraichirHistorique();
            }
        );
        barreDeMenu.add(rafraichirListe);

        JMenuItem quitter = new JMenuItem("Quitter");
        quitter.addActionListener(e -> System.exit(0));
        barreDeMenu.add(quitter);

        return barreDeMenu;
    }

    private JPanel creerPanneauListeProgrammeurs() {
        JPanel panneau = new JPanel(new BorderLayout());
        panneau.add(new JLabel("Liste des Programmeurs", JLabel.CENTER), BorderLayout.NORTH);

        String[] colonnes = {"ID", "Nom", "Prénom", "Adresse", "Pseudo", "Salaire"};
        List<Programmeur> programmeurs = serviceProgrammeur.findAll();
        Object[][] donnees = new Object[programmeurs.size()][colonnes.length];

        for (int i = 0; i < programmeurs.size(); i++) {
            Programmeur p = programmeurs.get(i);
            donnees[i] = new Object[]{p.getId(), p.getNom(), p.getPrenom(), p.getAdresse(), p.getPseudo(), p.getSalaire()};
        }

        JTable table = new JTable(donnees, colonnes);
        JScrollPane panneauDefilement = new JScrollPane(table);

        panneau.add(panneauDefilement, BorderLayout.CENTER);
        return panneau;
    }

    private JPanel creerPanneauHistorique() {
        JPanel panneau = new JPanel(new BorderLayout());
        panneau.add(new JLabel("Historique", JLabel.CENTER), BorderLayout.NORTH);

        String[] colonnes = {"Action", "Date", "Détails"};
        Object[][] donnees = obtenirDonneesHistorique();

        tableHistorique = new JTable(donnees, colonnes);
        panneauDefilementHistorique = new JScrollPane(tableHistorique);
        panneauDefilementHistorique.setPreferredSize(new Dimension(350, 250));

        panneau.add(panneauDefilementHistorique, BorderLayout.CENTER);
        return panneau;
    }

    private Object[][] obtenirDonneesHistorique() {
        List<Action> actions = historique.getHistorique();
        Object[][] donnees = new Object[actions.size()][3];

        for (int i = 0; i < actions.size(); i++) {
            Action action = actions.get(i);
            donnees[i][0] = action.getActionName();
            donnees[i][1] = action.getActionDate();
            donnees[i][2] = action.getDetails();
        }

        return donnees;
    }

    private void afficherDialogueAjoutProgrammeur() {
        JPanel panneau = new JPanel(new GridLayout(0, 2));
        JTextField champNom = new JTextField();
        JTextField champPrenom = new JTextField();
        JTextField champAdresse = new JTextField();
        JTextField champPseudo = new JTextField();
        JTextField champSalaire = new JTextField();

        panneau.add(new JLabel("Nom :"));
        panneau.add(champNom);
        panneau.add(new JLabel("Prénom :"));
        panneau.add(champPrenom);
        panneau.add(new JLabel("Adresse :"));
        panneau.add(champAdresse);
        panneau.add(new JLabel("Pseudo :"));
        panneau.add(champPseudo);
        panneau.add(new JLabel("Salaire :"));
        panneau.add(champSalaire);

        int resultat = JOptionPane.showConfirmDialog(this, panneau, "Ajouter un Programmeur", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (resultat == JOptionPane.OK_OPTION) {
            if (champNom.getText().isEmpty() || champPrenom.getText().isEmpty() ||
                    champAdresse.getText().isEmpty() || champPseudo.getText().isEmpty() ||
                    champSalaire.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Tous les champs sont requis !", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                int salaire = Integer.parseInt(champSalaire.getText());

                Programmeur programmeur = new Programmeur();
                programmeur.setNom(champNom.getText());
                programmeur.setPrenom(champPrenom.getText());
                programmeur.setAdresse(champAdresse.getText());
                programmeur.setPseudo(champPseudo.getText());
                programmeur.setSalaire(salaire);

                serviceProgrammeur.add(programmeur);
                historique.ajouterAction(new Action("Ajout Programmeur", obtenirDateActuelle(), programmeur));
                JOptionPane.showMessageDialog(this, "Programmeur ajouté avec succès !");
                rafraichirListeProgrammeurs();
                rafraichirHistorique();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Le salaire doit être un nombre valide !", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void afficherDialogueModificationProgrammeur() {
        String idStr = JOptionPane.showInputDialog(this, "Entrez l'ID du programmeur à modifier :");
        if (idStr != null) {
            try {
                int id = Integer.parseInt(idStr);
                Programmeur p = serviceProgrammeur.findOne(id);

                if (p != null) {
                    JPanel panneau = new JPanel(new GridLayout(0, 2));
                    JTextField champNom = new JTextField(p.getNom());
                    JTextField champPrenom = new JTextField(p.getPrenom());
                    JTextField champAdresse = new JTextField(p.getAdresse());
                    JTextField champPseudo = new JTextField(p.getPseudo());
                    JTextField champSalaire = new JTextField(String.valueOf(p.getSalaire()));

                    panneau.add(new JLabel("Nom :"));
                    panneau.add(champNom);
                    panneau.add(new JLabel("Prénom :"));
                    panneau.add(champPrenom);
                    panneau.add(new JLabel("Adresse :"));
                    panneau.add(champAdresse);
                    panneau.add(new JLabel("Pseudo :"));
                    panneau.add(champPseudo);
                    panneau.add(new JLabel("Salaire :"));
                    panneau.add(champSalaire);

                    int resultat = JOptionPane.showConfirmDialog(this, panneau, "Modifier Programmeur", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                    if (resultat == JOptionPane.OK_OPTION) {
                        if (champNom.getText().isEmpty() || champPrenom.getText().isEmpty() ||
                                champAdresse.getText().isEmpty() || champPseudo.getText().isEmpty() ||
                                champSalaire.getText().isEmpty()) {
                            JOptionPane.showMessageDialog(this, "Tous les champs sont requis !", "Erreur", JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        try {
                            int salaire = Integer.parseInt(champSalaire.getText());

                            p.setNom(champNom.getText());
                            p.setPrenom(champPrenom.getText());
                            p.setAdresse(champAdresse.getText());
                            p.setPseudo(champPseudo.getText());
                            p.setSalaire(salaire);

                            serviceProgrammeur.update(p);
                            JOptionPane.showMessageDialog(this, "Programmeur modifié avec succès !");
                            rafraichirListeProgrammeurs();
                            rafraichirHistorique();
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(this, "Le salaire doit être un nombre valide !", "Erreur", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Programmeur introuvable !", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "L'ID doit être un nombre valide !", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void afficherDialogueSuppressionProgrammeur() {
        String idStr = JOptionPane.showInputDialog(this, "Entrez l'ID du programmeur à supprimer :");
        if (idStr != null) {
            try {
                int id = Integer.parseInt(idStr);
                Programmeur programmeur = serviceProgrammeur.findOne(id);

                if (programmeur != null) {
                    int confirmation = JOptionPane.showConfirmDialog(this,
                            "Êtes-vous sûr de vouloir supprimer le programmeur avec l'ID " + id + " ?",
                            "Confirmer la suppression", JOptionPane.YES_NO_OPTION);

                    if (confirmation == JOptionPane.YES_OPTION) {
                        serviceProgrammeur.delete(programmeur);
                        historique.ajouterAction(new Action("Suppression Programmeur", obtenirDateActuelle(), programmeur));
                        JOptionPane.showMessageDialog(this, "Programmeur supprimé avec succès !");
                        rafraichirListeProgrammeurs();
                        rafraichirHistorique();
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Programmeur introuvable !", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "L'ID doit être un nombre valide !", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void rafraichirListeProgrammeurs() {
        getContentPane().removeAll();
        add(creerPanneauListeProgrammeurs(), BorderLayout.CENTER);
        add(creerPanneauHistorique(), BorderLayout.SOUTH);
        revalidate();
        repaint();
    }

    private void rafraichirHistorique() {
        Object[][] data = obtenirDonneesHistorique();
        String[] columns = {"Action", "Date", "Details"};

        tableHistorique.setModel(new javax.swing.table.DefaultTableModel(data, columns));
        panneauDefilementHistorique.revalidate();
        panneauDefilementHistorique.repaint();
    }

    private String obtenirDateActuelle() {
        return new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
    }

    public static void main(String[] args) {
            SwingUtilities.invokeLater(() -> new MainUI(new ProgrammeurService(), new Historique()));
    }
}
