package hr.fer.zemris.java.hw06.shell.commands.shared;

import hr.fer.zemris.java.hw06.shell.ShellIOException;

import java.io.*;

public class FileLoader {
    public static final int DEFAULT_CHUNK_SIZE = 16;

    public enum FileLoaderResponse {
        NOT_FOUND,
        DONE
    }

    public interface FileLoaderListener {
        void onBytes(byte[] bytes);
    }

    private final String path;
    private final int chunkSize;

    public FileLoader(String path, int chunkSize) {
        this.path = path;
        this.chunkSize = chunkSize;
    }

    public FileLoader(String path) {
        this(path, DEFAULT_CHUNK_SIZE);
    }

    public FileLoaderResponse loadFile(FileLoaderListener listener) {
        File file = new File(this.path);
        if (!file.exists()) {
            return FileLoaderResponse.NOT_FOUND;
        }

        try (FileInputStream fis = new FileInputStream(file)) {
            BufferedInputStream bis = new BufferedInputStream(fis);

            while (bis.available() > 0) {
                listener.onBytes(bis.readNBytes(this.chunkSize));
            }

            bis.close();
            return FileLoaderResponse.DONE;
        } catch (FileNotFoundException ex) {
            return FileLoaderResponse.NOT_FOUND;
        } catch (IOException e) {
            throw new ShellIOException(e.getMessage());
        }
    }
}
