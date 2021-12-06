package hr.fer.oprpp1.hw05.crypto;

import java.io.IOException;

public interface FileLoaderCallable {
    void call(byte[] data) throws IOException;
}
