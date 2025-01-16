package org.example.run;

import org.example.model.Action;
import org.example.model.Historique;
import org.example.model.Programmeur;
import org.example.database.ActionsBDD;
import org.example.database.ActionsBDDImpl;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * La classe <code>StartBonusInterface</code> représente l'interface graphique principale de l'application,
 * permettant de gérer la liste des programmeurs (affichage, ajout, modification, suppression)
 * et de consulter l'historique des actions effectuées.
 *
 * <p>Elle hérite de <code>JFrame</code> et utilise un <code>BorderLayout</code> pour
 * organiser ses composants principaux :</p>
 * <ul>
 *     <li>Une barre de menu (menu supérieur) contenant différentes actions.</li>
 *     <li>Un panneau central pour afficher la liste des programmeurs.</li>
 *     <li>Un panneau au bas (SOUTH) pour afficher l’historique des actions.</li>
 * </ul>
 *
 * @author Mehedi Touré & Adil Chetouni
 * @version 1.0
 * @since   1.0
 */
public class StartBonusInterface extends JFrame {

    /** Service permettant d'effectuer les opérations CRUD sur les programmeurs. */
    private final ActionsBDD serviceProgrammeur;

    /** Historique centralisant les actions effectuées (ajouts, modifications, suppressions). */
    private final Historique historique;

    /** Tableau pour l'affichage de l'historique. */
    private JTable tableHistorique;

    /** Panneau permettant de faire défiler le tableau de l'historique. */
    private JScrollPane panneauDefilementHistorique;

    /**
     * Constructeur de la fenêtre principale de l'application.
     *
     * @param serviceProgrammeur L’implémentation du service des programmeurs.
     * @param historique         L’historique des actions effectuées.
     */
    public StartBonusInterface(ActionsBDD serviceProgrammeur, Historique historique) {
        this.serviceProgrammeur = serviceProgrammeur;
        this.historique = historique;

        setTitle("Projet SGBD");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Ajout de la barre de menu
        setJMenuBar(creerBarreDeMenu());

        // Ajout des panneaux principaux
        add(creerPanneauListeProgrammeurs(), BorderLayout.CENTER);
        add(creerPanneauHistorique(), BorderLayout.SOUTH);

        setVisible(true);
    }

    /**
     * Crée et configure la barre de menu de l’application.
     *
     * @return La barre de menu complète (<code>JMenuBar</code>).
     */
    private JMenuBar creerBarreDeMenu() {
        JMenuBar barreDeMenu = new JMenuBar();

        JMenuItem informationsProgrammeur = new JMenuItem("Information d'un Programmeur");
        informationsProgrammeur.addActionListener(e -> afficherInformationsProgrammeur());
        barreDeMenu.add(informationsProgrammeur);

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
        rafraichirListe.addActionListener(e -> {
            rafraichirListeProgrammeurs();
            rafraichirHistorique();
        });
        barreDeMenu.add(rafraichirListe);

        JMenuItem quitter = new JMenuItem("Quitter");
        quitter.addActionListener(e -> {
            System.out.println("Bonus interface graphique terminé\n");
            dispose();
        });
        barreDeMenu.add(quitter);

        return barreDeMenu;
    }

    /**
     * Crée le panneau central qui affiche la liste des programmeurs.
     *
     * <p>Un tableau (<code>JTable</code>) est généré dynamiquement à partir
     * de la liste des programmeurs renvoyée par le service.</p>
     *
     * @return Un <code>JPanel</code> contenant la liste des programmeurs et ses en-têtes.
     */
    private JPanel creerPanneauListeProgrammeurs() {
        JPanel panneau = new JPanel(new BorderLayout());
        panneau.add(new JLabel("Liste des Programmeurs", JLabel.CENTER), BorderLayout.NORTH);

        String[] colonnes = {"ID", "Nom", "Prénom", "Adresse", "Pseudo", "Salaire"};
        List<Programmeur> programmeurs = serviceProgrammeur.findAll();
        Object[][] donnees = new Object[programmeurs.size()][colonnes.length];

        for (int i = 0; i < programmeurs.size(); i++) {
            Programmeur p = programmeurs.get(i);
            donnees[i] = new Object[]{
                    p.getId(),
                    p.getNom(),
                    p.getPrenom(),
                    p.getAdresse(),
                    p.getPseudo(),
                    p.getSalaire()
            };
        }

        JTable table = new JTable(donnees, colonnes);
        JScrollPane panneauDefilement = new JScrollPane(table);

        panneau.add(panneauDefilement, BorderLayout.CENTER);
        return panneau;
    }

    /**
     * Crée le panneau d’affichage de l’historique situé en bas de la fenêtre.
     *
     * <p>Un tableau (<code>JTable</code>) est généré dynamiquement à partir
     * des actions présentes dans l’historique.</p>
     *
     * @return Un <code>JPanel</code> contenant le tableau d’historique.
     */
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

    /**
     * Récupère les données (sous forme de tableau à deux dimensions)
     * correspondant à la liste des actions de l’historique.
     *
     * @return Un tableau d’objets contenant les informations de chaque action.
     */
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

    /**
     * Affiche une boîte de dialogue permettant d’ajouter un nouveau programmeur.
     *
     * <p>Des contrôles sont effectués pour s’assurer que les champs obligatoires
     * sont remplis avant de créer et sauvegarder le nouveau programmeur.</p>
     */
    private void afficherDialogueAjoutProgrammeur() {
        JPanel panneau = new JPanel(new GridLayout(0, 2));

        // Champs pour les informations du programmeur
        JTextField champNom = new JTextField();
        JTextField champPrenom = new JTextField();
        JTextField champAdresse = new JTextField();
        JTextField champPseudo = new JTextField();
        JComboBox<String> champResponsable = new JComboBox<>();
        JTextField champHobby = new JTextField();
        JTextField champAnneeNaissance = new JTextField();
        JTextField champSalaire = new JTextField();
        JTextField champPrime = new JTextField();

        // Charger les responsables dans le JComboBox
        champResponsable.addItem("Aucun"); // Option par défaut pour aucun responsable
        List<Programmeur> listeProgrammeurs = serviceProgrammeur.findAll();
        for (Programmeur programmeur : listeProgrammeurs) {
            champResponsable.addItem(programmeur.getId() + " - " + programmeur.getNom() + " " + programmeur.getPrenom());
        }

        // Ajout des labels et champs au panneau
        panneau.add(new JLabel("Nom :"));
        panneau.add(champNom);
        panneau.add(new JLabel("Prénom :"));
        panneau.add(champPrenom);
        panneau.add(new JLabel("Adresse :"));
        panneau.add(champAdresse);
        panneau.add(new JLabel("Pseudo :"));
        panneau.add(champPseudo);
        panneau.add(new JLabel("Responsable :"));
        panneau.add(champResponsable);
        panneau.add(new JLabel("Hobby :"));
        panneau.add(champHobby);
        panneau.add(new JLabel("Année de Naissance :"));
        panneau.add(champAnneeNaissance);
        panneau.add(new JLabel("Salaire :"));
        panneau.add(champSalaire);
        panneau.add(new JLabel("Prime (optionnel) :"));
        panneau.add(champPrime);

        // Afficher la boîte de dialogue
        int resultat = JOptionPane.showConfirmDialog(
                this,
                panneau,
                "Ajouter un Programmeur",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (resultat == JOptionPane.OK_OPTION) {
            // Vérification des champs obligatoires
            if (champNom.getText().isEmpty() ||
                    champPrenom.getText().isEmpty() ||
                    champAdresse.getText().isEmpty() ||
                    champPseudo.getText().isEmpty() ||
                    champHobby.getText().isEmpty() ||
                    champAnneeNaissance.getText().isEmpty() ||
                    champSalaire.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Tous les champs obligatoires doivent être remplis !",
                        "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                // Création du programmeur
                Programmeur programmeur = new Programmeur();
                programmeur.setNom(champNom.getText());
                programmeur.setPrenom(champPrenom.getText());
                programmeur.setAdresse(champAdresse.getText());
                programmeur.setPseudo(champPseudo.getText());

                // Gestion du responsable
                String responsableSelection = (String) champResponsable.getSelectedItem();
                if (responsableSelection != null && !responsableSelection.equals("Aucun")) {
                    String[] split = responsableSelection.split(" - ");
                    programmeur.setResponsable(Integer.parseInt(split[0]));
                } else {
                    programmeur.setResponsable(0); // Aucun responsable
                }

                // Champs obligatoires et optionnels
                programmeur.setHobby(champHobby.getText());
                programmeur.setAnNaissance(Integer.parseInt(champAnneeNaissance.getText()));
                programmeur.setSalaire(Integer.parseInt(champSalaire.getText()));
                programmeur.setPrime(champPrime.getText().isEmpty() ? 0 : Integer.parseInt(champPrime.getText()));

                // Ajouter le programmeur via le service
                serviceProgrammeur.add(programmeur);

                // Enregistrer l'action dans l'historique
                String dateCourante = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                Action action = new Action("Ajout d'un nouveau programmeur ", dateCourante, programmeur);
                historique.ajouterAction(action);

                // Notification et mise à jour de l'interface
                JOptionPane.showMessageDialog(this, "Programmeur ajouté avec succès !");
                rafraichirListeProgrammeurs();
                rafraichirHistorique();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this,
                        "Les champs Année de Naissance et Salaire doivent être des nombres valides !",
                        "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Affiche une boîte de dialogue permettant de modifier un programmeur existant.
     *
     * <p>Une première boîte de dialogue permet de sélectionner le programmeur à modifier,
     * puis une seconde s’ouvre pour saisir les nouvelles informations. Les champs sont
     * pré-remplis avec les valeurs actuelles du programmeur.</p>
     */
    private void afficherDialogueModificationProgrammeur() {
        // Panneau principal pour la sélection du programmeur
        JPanel panneauSelection = new JPanel(new BorderLayout());
        JComboBox<String> listeProgrammeurs = new JComboBox<>();

        // Charger les programmeurs dans le menu déroulant
        List<Programmeur> programmeurs = serviceProgrammeur.findAll();
        for (Programmeur p : programmeurs) {
            listeProgrammeurs.addItem(p.getId() + " - " + p.getNom() + " " + p.getPrenom());
        }

        panneauSelection.add(new JLabel("Choisissez le programmeur à modifier :"), BorderLayout.NORTH);
        panneauSelection.add(listeProgrammeurs, BorderLayout.CENTER);

        // Afficher la boîte de dialogue pour sélectionner le programmeur
        int resultatSelection = JOptionPane.showConfirmDialog(
                this,
                panneauSelection,
                "Modifier un Programmeur",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (resultatSelection == JOptionPane.OK_OPTION) {
            String programmeurSelectionne = (String) listeProgrammeurs.getSelectedItem();

            if (programmeurSelectionne != null) {
                String[] split = programmeurSelectionne.split(" - ");
                int id = Integer.parseInt(split[0]);

                Programmeur programmeur = serviceProgrammeur.findOne(id);

                if (programmeur != null) {
                    JPanel panneau = new JPanel(new GridLayout(0, 2));

                    // Champs pré-remplis
                    JTextField champNom = new JTextField(programmeur.getNom());
                    JTextField champPrenom = new JTextField(programmeur.getPrenom());
                    JTextField champAdresse = new JTextField(programmeur.getAdresse());
                    JTextField champPseudo = new JTextField(programmeur.getPseudo());
                    JComboBox<String> champResponsable = new JComboBox<>();
                    JTextField champHobby = new JTextField(programmeur.getHobby());
                    JTextField champAnneeNaissance = new JTextField(String.valueOf(programmeur.getAnNaissance()));
                    JTextField champSalaire = new JTextField(String.valueOf(programmeur.getSalaire()));
                    JTextField champPrime = new JTextField(String.valueOf(programmeur.getPrime()));

                    // Charger les responsables dans le JComboBox
                    champResponsable.addItem("Aucun");
                    for (Programmeur p : programmeurs) {
                        // Empêcher le programmeur actuel d'être son propre responsable
                        if (p.getId() != programmeur.getId()) {
                            String option = p.getId() + " - " + p.getNom() + " " + p.getPrenom();
                            champResponsable.addItem(option);
                            // Sélectionner le responsable actuel si correspond
                            if (p.getId() == programmeur.getResponsable()) {
                                champResponsable.setSelectedItem(option);
                            }
                        }
                    }

                    // Ajout des labels et champs au panneau
                    panneau.add(new JLabel("Nom :"));
                    panneau.add(champNom);
                    panneau.add(new JLabel("Prénom :"));
                    panneau.add(champPrenom);
                    panneau.add(new JLabel("Adresse :"));
                    panneau.add(champAdresse);
                    panneau.add(new JLabel("Pseudo :"));
                    panneau.add(champPseudo);
                    panneau.add(new JLabel("Responsable :"));
                    panneau.add(champResponsable);
                    panneau.add(new JLabel("Hobby :"));
                    panneau.add(champHobby);
                    panneau.add(new JLabel("Année de Naissance :"));
                    panneau.add(champAnneeNaissance);
                    panneau.add(new JLabel("Salaire :"));
                    panneau.add(champSalaire);
                    panneau.add(new JLabel("Prime :"));
                    panneau.add(champPrime);

                    // Afficher la boîte de dialogue de modification
                    int resultat = JOptionPane.showConfirmDialog(
                            this,
                            panneau,
                            "Modifier un Programmeur",
                            JOptionPane.OK_CANCEL_OPTION,
                            JOptionPane.PLAIN_MESSAGE
                    );

                    if (resultat == JOptionPane.OK_OPTION) {
                        // Vérification des champs obligatoires
                        if (champNom.getText().isEmpty() ||
                                champPrenom.getText().isEmpty() ||
                                champAdresse.getText().isEmpty() ||
                                champPseudo.getText().isEmpty() ||
                                champHobby.getText().isEmpty() ||
                                champAnneeNaissance.getText().isEmpty() ||
                                champSalaire.getText().isEmpty()) {
                            JOptionPane.showMessageDialog(this,
                                    "Tous les champs obligatoires doivent être remplis !",
                                    "Erreur", JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        try {
                            // Mise à jour des attributs
                            programmeur.setNom(champNom.getText());
                            programmeur.setPrenom(champPrenom.getText());
                            programmeur.setAdresse(champAdresse.getText());
                            programmeur.setPseudo(champPseudo.getText());

                            // Responsable
                            String responsableSelection = (String) champResponsable.getSelectedItem();
                            if (responsableSelection != null && !responsableSelection.equals("Aucun")) {
                                String[] splitResponsable = responsableSelection.split(" - ");
                                programmeur.setResponsable(Integer.parseInt(splitResponsable[0]));
                            } else {
                                programmeur.setResponsable(0);
                            }

                            // Autres champs
                            programmeur.setHobby(champHobby.getText());
                            programmeur.setAnNaissance(Integer.parseInt(champAnneeNaissance.getText()));
                            programmeur.setSalaire(Integer.parseInt(champSalaire.getText()));
                            programmeur.setPrime(champPrime.getText().isEmpty() ? 0 : Integer.parseInt(champPrime.getText()));

                            // Mettre à jour via le service
                            serviceProgrammeur.update(programmeur);

                            // Enregistrer l'action dans l'historique
                            String dateCourante = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                            Action action = new Action(
                                    "Modification du programmeur (ID=" + programmeur.getId() + ")",
                                    dateCourante,
                                    programmeur
                            );
                            historique.ajouterAction(action);

                            // Notification et mise à jour
                            JOptionPane.showMessageDialog(this, "Programmeur modifié avec succès !");
                            rafraichirListeProgrammeurs();
                            rafraichirHistorique();
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(this,
                                    "Les champs Année de Naissance et Salaire doivent être des nombres valides !",
                                    "Erreur", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        }
    }

    /**
     * Affiche une boîte de dialogue permettant de supprimer un programmeur,
     * identifiant le programmeur par son ID (saisi manuellement).
     */
    private void afficherDialogueSuppressionProgrammeur() {
        String idStr = JOptionPane.showInputDialog(this, "Entrez l'ID du programmeur à supprimer :");
        if (idStr != null) {
            try {
                int id = Integer.parseInt(idStr);
                Programmeur programmeur = serviceProgrammeur.findOne(id);

                if (programmeur != null) {
                    int confirmation = JOptionPane.showConfirmDialog(
                            this,
                            "Êtes-vous sûr de vouloir supprimer le programmeur avec l'ID " + id + " ?",
                            "Confirmer la suppression",
                            JOptionPane.YES_NO_OPTION
                    );

                    if (confirmation == JOptionPane.YES_OPTION) {
                        serviceProgrammeur.delete(programmeur);
                        historique.ajouterAction(
                                new Action("Suppression Programmeur", obtenirDateActuelle(), programmeur)
                        );
                        JOptionPane.showMessageDialog(this, "Programmeur supprimé avec succès !");
                        rafraichirListeProgrammeurs();
                        rafraichirHistorique();
                    }
                } else {
                    JOptionPane.showMessageDialog(this,
                            "Programmeur introuvable !",
                            "Erreur",
                            JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this,
                        "L'ID doit être un nombre valide !",
                        "Erreur",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Affiche une boîte de dialogue pour choisir un programmeur,
     * et montre ensuite ses informations détaillées en lecture seule.
     */
    private void afficherInformationsProgrammeur() {
        JPanel panneauSelection = new JPanel(new BorderLayout());
        JComboBox<String> listeProgrammeurs = new JComboBox<>();

        // Charger les programmeurs dans le menu déroulant
        List<Programmeur> programmeurs = serviceProgrammeur.findAll();
        for (Programmeur p : programmeurs) {
            listeProgrammeurs.addItem(p.getId() + " - " + p.getNom() + " " + p.getPrenom());
        }

        panneauSelection.add(new JLabel("Choisissez un programmeur :"), BorderLayout.NORTH);
        panneauSelection.add(listeProgrammeurs, BorderLayout.CENTER);

        int resultatSelection = JOptionPane.showConfirmDialog(
                this,
                panneauSelection,
                "Voir les Informations",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (resultatSelection == JOptionPane.OK_OPTION) {
            String programmeurSelectionne = (String) listeProgrammeurs.getSelectedItem();

            if (programmeurSelectionne != null) {
                String[] split = programmeurSelectionne.split(" - ");
                int id = Integer.parseInt(split[0]);

                Programmeur programmeur = serviceProgrammeur.findOne(id);

                if (programmeur != null) {
                    // Panneau avec les informations en texte non modifiable
                    JPanel panneauDetails = new JPanel(new GridLayout(0, 2));
                    panneauDetails.add(new JLabel("ID :"));
                    panneauDetails.add(new JLabel(String.valueOf(programmeur.getId())));
                    panneauDetails.add(new JLabel("Nom :"));
                    panneauDetails.add(new JLabel(programmeur.getNom()));
                    panneauDetails.add(new JLabel("Prénom :"));
                    panneauDetails.add(new JLabel(programmeur.getPrenom()));
                    panneauDetails.add(new JLabel("Adresse :"));
                    panneauDetails.add(new JLabel(programmeur.getAdresse()));
                    panneauDetails.add(new JLabel("Pseudo :"));
                    panneauDetails.add(new JLabel(programmeur.getPseudo()));
                    panneauDetails.add(new JLabel("Responsable (ID) :"));
                    panneauDetails.add(new JLabel(programmeur.getResponsable() == 0 ? "Aucun" : String.valueOf(programmeur.getResponsable())));
                    panneauDetails.add(new JLabel("Hobby :"));
                    panneauDetails.add(new JLabel(programmeur.getHobby()));
                    panneauDetails.add(new JLabel("Année de Naissance :     "));
                    panneauDetails.add(new JLabel(String.valueOf(programmeur.getAnNaissance())));
                    panneauDetails.add(new JLabel("Salaire :"));
                    panneauDetails.add(new JLabel(String.valueOf(programmeur.getSalaire())));
                    panneauDetails.add(new JLabel("Prime :"));
                    panneauDetails.add(new JLabel(String.valueOf(programmeur.getPrime())));

                    // Afficher les informations
                    JOptionPane.showMessageDialog(this, panneauDetails,
                            "Informations du Programmeur", JOptionPane.PLAIN_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this,
                            "Programmeur introuvable !",
                            "Erreur",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    /**
     * Rafraîchit la liste des programmeurs et l’historique de la fenêtre,
     * en recréant les panneaux concernés et en les réinjectant dans le layout.
     */
    private void rafraichirListeProgrammeurs() {
        getContentPane().removeAll();
        add(creerPanneauListeProgrammeurs(), BorderLayout.CENTER);
        add(creerPanneauHistorique(), BorderLayout.SOUTH);
        revalidate();
        repaint();
    }

    /**
     * Rafraîchit uniquement la partie historique de la fenêtre,
     * en mettant à jour le <code>DefaultTableModel</code> du tableau historique.
     */
    private void rafraichirHistorique() {
        Object[][] data = obtenirDonneesHistorique();
        String[] columns = {"Action", "Date", "Details"};

        tableHistorique.setModel(new javax.swing.table.DefaultTableModel(data, columns));
        panneauDefilementHistorique.revalidate();
        panneauDefilementHistorique.repaint();
    }

    /**
     * Retourne la date et l’heure au format <code>yyyy-MM-dd HH:mm:ss</code>.
     *
     * @return La date et l'heure actuelle au format chaîne de caractères.
     */
    private String obtenirDateActuelle() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    /**
     * Point d’entrée principal de l’application.
     */
    public static void run() {
        SwingUtilities.invokeLater(() -> new StartBonusInterface(new ActionsBDDImpl(), new Historique()));
    }
}
