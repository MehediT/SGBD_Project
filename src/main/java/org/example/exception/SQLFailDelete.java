package org.example.exception;

/**
 * Exception personnalisée pour les échecs de suppression d'une entrée dans une base de données SQL.
 * <p>
 * Cette exception est déclenchée lorsqu'une tentative de suppression d'une entrée dans une base SQL échoue.
 * Elle permet de transmettre un message d'erreur détaillé à l'utilisateur ou au développeur.
 * </p>
 */
public class SQLFailDelete extends RuntimeException {

    /**
     * Constructeur de l'exception avec un message d'erreur détaillé.
     *
     * @param message Le message décrivant la cause de l'échec de la suppression.
     */
    public SQLFailDelete(String message) {
        super(message);
    }
}
