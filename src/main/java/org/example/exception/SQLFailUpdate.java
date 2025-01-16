package org.example.exception;

/**
 * Exception personnalisée pour les échecs de mise à jour d'une entrée dans une base de données SQL.
 * <p>
 * Cette exception est déclenchée lorsqu'une tentative de mise à jour d'une entrée dans une base SQL échoue.
 * Elle permet de transmettre un message d'erreur détaillé à l'utilisateur ou au développeur.
 * </p>
 *
 * @author Mehedi Touré & Adil Chetouni
 * @version 1.0
 * @since   1.0
 */
public class SQLFailUpdate extends RuntimeException {

    /**
     * Constructeur de l'exception avec un message d'erreur détaillé.
     *
     * @param message Le message décrivant la cause de l'échec de la mise à jour.
     */
    public SQLFailUpdate(String message) {
        super(message);
    }
}
