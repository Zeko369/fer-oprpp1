package hr.fer.oprpp1.hw08.jnotepadpp.model;

import hr.fer.oprpp1.hw08.jnotepadpp.model.enums.FileSystemExceptionType;

/**
 * The type File system exception.
 */
public class FileSystemException extends RuntimeException {
    private final FileSystemExceptionType type;

    /**
     * Instantiates a new File system exception.
     *
     * @param type the type
     */
    public FileSystemException(FileSystemExceptionType type) {
        this.type = type;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public FileSystemExceptionType getType() {
        return type;
    }
}
