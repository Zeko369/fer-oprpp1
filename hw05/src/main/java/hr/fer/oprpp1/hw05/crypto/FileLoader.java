package hr.fer.oprpp1.hw05.crypto;

import java.io.*;

/**
 * Util for loading a file using callbacks for returning data
 *
 * @author franzekan
 */
public class FileLoader {
    /**
     * The constant READ_BLOCK.
     */
    public static final int READ_BLOCK = 1024 * 4;

    /**
     * Method for loading a file
     *
     * @param filename the filename
     * @param onData   the onData callback function
     */
    public static void load(String filename, FileLoaderCallable onData) {
        File file = new File(filename);

        try (FileInputStream fis = new FileInputStream(file)) {
            BufferedInputStream bis = new BufferedInputStream(fis);

            while (bis.available() > 0) {
                onData.call(bis.readNBytes(READ_BLOCK));
            }

            bis.close();
        } catch (FileNotFoundException ex) {
            System.err.println("File not found");
        } catch (IOException e) {
            System.err.println();
        }
    }
}
