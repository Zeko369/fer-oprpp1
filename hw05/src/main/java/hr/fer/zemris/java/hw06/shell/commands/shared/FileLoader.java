package hr.fer.zemris.java.hw06.shell.commands.shared;

import hr.fer.zemris.java.hw06.shell.ShellIOException;

import java.io.*;

/**
 * Helper for loading files with callbacks
 *
 * @author franzekan
 */
public class FileLoader {
    /**
     * The constant DEFAULT_CHUNK_SIZE.
     */
    public static final int DEFAULT_CHUNK_SIZE = 16;

    /**
     * The enum File loader response.
     *
     * @author franzekan
     */
    public enum FileLoaderResponse {
        /**
         * Not found file loader response.
         */
        NOT_FOUND,
        /**
         * Done file loader response.
         */
        DONE
    }

    /**
     * The interface File loader listener.
     *
     * @author franzekan
     */
    public interface FileLoaderListener {
        /**
         * On bytes.
         *
         * @param bytes the bytes
         * @throws IOException the io exception
         */
        void onBytes(byte[] bytes) throws IOException;
    }

    private final String path;
    private final int chunkSize;

    /**
     * Instantiates a new File loader.
     *
     * @param path      the path
     * @param chunkSize the chunk size
     */
    public FileLoader(String path, int chunkSize) {
        this.path = path;
        this.chunkSize = chunkSize;
    }

    /**
     * Instantiates a new File loader.
     *
     * @param path the path
     */
    public FileLoader(String path) {
        this(path, DEFAULT_CHUNK_SIZE);
    }

    /**
     * Load file and call callback for each chunk.
     *
     * @param listener the listener
     * @return the file loader response
     */
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
        } catch (Exception e) {
            throw new ShellIOException(e);
        }
    }
}
