package hr.fer.oprpp1.hw08.jnotepadpp.model;

import hr.fer.oprpp1.hw08.jnotepadpp.model.enums.FileSystemExceptionType;

public class FileSystemException extends RuntimeException {
    private final FileSystemExceptionType type;

    public FileSystemException(FileSystemExceptionType type) {
        this.type = type;
    }

    public FileSystemExceptionType getType() {
        return type;
    }
}
