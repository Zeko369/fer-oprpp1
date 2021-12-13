package hr.fer.zemris.java.hw06.shell;

/**
 * The type ShellIOException
 *
 * @author franzekan
 */
public class ShellIOException extends RuntimeException {
    /**
     * Instantiates a new ShellIOException from a string message.
     *
     * @param message the message
     */
    public ShellIOException(String message) {
        super(message);
    }

    /**
     * Instantiates a new ShellIOException from a Throwable.
     *
     * @param cause the cause
     */
    public ShellIOException(Throwable cause) {
        super(cause);
    }
}
