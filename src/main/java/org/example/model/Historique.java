package org.example.model;

import java.util.ArrayList;

/**
 * La classe <code>Historique</code> gère la liste des actions effectuées
 * (par exemple, des ajouts, modifications ou suppressions de programmeurs)
 * au sein de l'application.
 *
 * <p>Elle permet d'ajouter de nouvelles actions, de récupérer la liste
 * complète des actions, de les afficher en console et de les effacer.</p>
 *
 * @author Mehedi Touré & Adil Chetouni
 * @version 1.0
 * @since   1.0
 */
public class Historique {

    /**
     * La liste interne stockant toutes les actions effectuées.
     */
    private final ArrayList<Action> historique = new ArrayList<>();

    /**
     * Ajoute une nouvelle action à l'historique.
     *
     * @param action L'action à ajouter.
     */
    public void ajouterAction(Action action) {
        historique.add(action);
    }

    /**
     * Retourne la liste complète des actions présentes dans l'historique.
     *
     * @return Une <code>ArrayList</code> d'objets <code>Action</code>.
     */
    public ArrayList<Action> getHistorique() {
        return historique;
    }
}
