package org.example.model;

/**
 * La classe <code>Action</code> représente une action effectuée dans l’application.
 * Elle contient des informations sur le type d’action réalisée, la date à laquelle
 * elle a été effectuée et éventuellement le programmeur concerné.
 *
 * @author Mehedi Touré & Adil Chetouni
 * @version 1.0
 * @since   1.0
 */
public class Action {

    /** Le nom ou la description de l'action. */
    private String action;

    /** La date à laquelle l’action a été effectuée (format chaîne de caractères). */
    private String date;

    /** Le programmeur éventuellement lié à l'action. */
    private Programmeur programmeur;

    /**
     * Constructeur principal d’une action.
     * <p>
     * Il duplique l’objet <code>Programmeur</code> passé en paramètre pour en
     * stocker une copie indépendante dans la classe <code>Action</code>.
     * </p>
     *
     * @param action      Le nom ou la description de l’action.
     * @param date        La date à laquelle l’action a été effectuée.
     * @param programmeur Le programmeur concerné par l’action (ou <code>null</code> s’il n’y en a pas).
     */
    public Action(String action, String date, Programmeur programmeur) {
        this.action = action;
        this.date = date;
        if (programmeur != null) {
            this.programmeur = new Programmeur(
                    programmeur.getId(),
                    programmeur.getNom(),
                    programmeur.getPrenom(),
                    programmeur.getAdresse(),
                    programmeur.getPseudo(),
                    programmeur.getResponsable(),
                    programmeur.getHobby(),
                    programmeur.getAnNaissance(),
                    programmeur.getSalaire(),
                    programmeur.getPrime()
            );
        }
    }

    /**
     * Constructeur secondaire d’une action, ne prenant pas de programmeur en paramètre.
     *
     * @param action Le nom ou la description de l’action.
     * @param date   La date à laquelle l’action a été effectuée.
     */
    public Action(String action, String date) {
        this(action, date, null);
    }

    /**
     * Retourne le nom (ou la description) de l'action.
     *
     * @return Le nom de l'action.
     */
    public String getActionName() {
        return action;
    }

    /**
     * Retourne la date de l'action.
     *
     * @return La date de l'action au format chaîne de caractères.
     */
    public String getActionDate() {
        return date;
    }

    /**
     * Retourne des détails sur le programmeur lié à l’action, s’il existe.
     * <p>
     * Ces détails incluent son nom, son prénom ainsi que l’ID de son responsable
     * s’il en a un.
     * </p>
     *
     * @return Les détails sur le programmeur ou une chaîne vide s’il n’y a pas de programmeur associé.
     */
    public String getDetails() {
        if (programmeur == null) {
            return "";
        }
        String details = "Nom: " + programmeur.getNom() + " ";
        details += "Prenom: " + programmeur.getPrenom() + " ";
        if (programmeur.getResponsable() > 0) {
            details += "Responsable (id): " + programmeur.getResponsable();
        }
        return details;
    }

    /**
     * Redéfinit la méthode <code>toString()</code> pour fournir une représentation textuelle
     * de l’action, y compris la date et les informations sur le programmeur si celui-ci existe.
     *
     * @return Une chaîne représentant l’action et les informations associées.
     */
    @Override
    public String toString() {
        return "Action: " + action
                + " Date: " + date
                + (programmeur != null ? "\nPour le programmeur: " + programmeur : "");
    }
}
