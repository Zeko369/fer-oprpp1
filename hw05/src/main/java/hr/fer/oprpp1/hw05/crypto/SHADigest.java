package hr.fer.oprpp1.hw05.crypto;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Util for SHA-256 digest.
 *
 * @author franzekan
 */
public class SHADigest {
    private final String fileName;

    /**
     * Instantiates a new Sha digest.
     *
     * @param fileName the file name
     */
    public SHADigest(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Gets digest.
     *
     * @return the digest
     */
    public String getDigest() {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            FileLoader.load(this.fileName, md::update);

            return Util.byteToHex(md.digest());
        } catch (NoSuchAlgorithmException e) {
            System.err.println("SHA-256 algorithm not found.");
            System.exit(1);
            return null;
        }
    }
}
