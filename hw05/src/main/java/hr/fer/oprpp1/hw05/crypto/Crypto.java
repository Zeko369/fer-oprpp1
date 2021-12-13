package hr.fer.oprpp1.hw05.crypto;

/**
 * Main entry point into our simple crypto CLI program.
 *
 * @author franzekan
 */
public class Crypto {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println("Expected at least 2 arguments");
            System.exit(1);
            return;
        }

        try {
            switch (args[0]) {
                case "checksha" -> runDigest(args[1]);
                case "encrypt" -> runEncryptor(EncryptorMode.ENCRYPT, args);
                case "decrypt" -> runEncryptor(EncryptorMode.DECRYPT, args);
                default -> {
                    System.err.println("Unknown command: " + args[0]);
                    System.exit(1);
                }
            }
        } catch (Exception e) {
            System.err.println("Unexpected error occurred");
        }
    }

    private static void runDigest(String filename) {
        String expectedDigest = PromptInput.getUserInput("Please provide expected sha-256 digest for hw05test.bin:\n> ");

        SHADigest sha = new SHADigest(filename);
        String fileDigest = sha.getDigest();

        if (fileDigest.equals(expectedDigest)) {
            System.out.printf("Digesting completed. Digest of %s matches expected digest.", filename);
        } else {
            System.out.printf("Digesting completed. Digest of %s does not match the expected digest. Digest\nwas: %s", filename, fileDigest);
        }
    }

    private static void runEncryptor(EncryptorMode mode, String[] args) {
        if (args.length != 3) {
            System.err.println("Expected exactly 3 arguments");
            System.exit(1);
        }

        String key = PromptInput.getUserInput("Please provide password as hex-encoded text (16 bytes, i.e. 32 hex-digits):\n> ");
        String iv = PromptInput.getUserInput("Please provide initialization vector as hex-encoded text (32 hex-digits):\n> ");

        Encryptor encryptor = new Encryptor(mode, args[1], args[2]);
        encryptor.run(key, iv);
    }
}
