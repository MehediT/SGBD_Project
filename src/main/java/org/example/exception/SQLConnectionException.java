package org.example.exception;

/**
 * Exception personnalisée pour les erreurs de connexion à une base de données SQL.
 * <p>
 * Cette exception est déclenchée lorsqu'une erreur survient lors de l'établissement
 * d'une connexion SQL, afin de fournir un message d'erreur détaillé.
 * </p>
 */
public class SQLConnectionException extends RuntimeException {

    /**
     * Constructeur de l'exception avec un message détaillé.
     *
     * @param message Le message décrivant la cause de l'exception.
     */
    public SQLConnectionException(String message) {
        super(message);
    }
}
