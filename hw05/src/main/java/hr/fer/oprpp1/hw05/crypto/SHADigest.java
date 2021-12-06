package hr.fer.oprpp1.hw05.crypto;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHADigest {
    private final String fileName;

    public SHADigest(String fileName) {
        this.fileName = fileName;
    }

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
