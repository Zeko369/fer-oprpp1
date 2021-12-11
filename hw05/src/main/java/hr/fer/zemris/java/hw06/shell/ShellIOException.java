package hr.fer.zemris.java.hw06.shell;

public class ShellIOException extends RuntimeException {
    public ShellIOException() {
        super();
    }

    public ShellIOException(String message) {
        super(message);
    }

    public ShellIOException(Throwable cause) {
        super(cause);
    }
}
