package org.example.exception;

/**
 * Exception personnalisée pour les échecs d'exécution de requêtes SQL.
 * <p>
 * Cette exception est déclenchée lorsqu'une requête SQL échoue, qu'il s'agisse d'une requête de lecture,
 * de mise à jour ou d'autres types d'opérations. Elle permet de transmettre un message d'erreur détaillé
 * sur la cause de l'échec.
 * </p>
 */
public class SQLFailQuery extends RuntimeException {

    /**
     * Constructeur de l'exception avec un message d'erreur détaillé.
     *
     * @param message Le message décrivant la cause de l'échec de la requête SQL.
     */
    public SQLFailQuery(String message) {
        super(message);
    }
}
