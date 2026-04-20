package spp.data.exception;

/**
 * Se lanza cuando hay un error técnico con MySQL (ej. servidor caído, error de sintaxis).
 */
public class PersistenceException extends RuntimeException {
    public PersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
}