package hr.fer.oprpp1.hw05.crypto;

import java.io.IOException;

/**
 * The interface File loader callable.
 *
 * @author franzekan
 */
public interface FileLoaderCallable {
    /**
     * Call.
     *
     * @param data array of bytes
     * @throws IOException the io exception
     */
    void call(byte[] data) throws IOException;
}
