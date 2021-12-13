package hr.fer.oprpp1.hw05.crypto;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;

/**
 * Class used for encrypting and decrypting files.
 *
 * @author franzekan
 */
public class Encryptor {
    private final EncryptorMode mode;
    private final String srcFilename;
    private final String outFilename;

    /**
     * Instantiates a new Encryptor.
     *
     * @param mode        the mode
     * @param srcFilename the src filename
     * @param outFilename the out filename
     */
    public Encryptor(EncryptorMode mode, String srcFilename, String outFilename) {
        this.mode = mode;
        this.srcFilename = srcFilename;
        this.outFilename = outFilename;
    }

    /**
     * Encrypt or decrypt the file.
     *
     * @param pass the pass
     * @param iv   the iv
     */
    public void run(String pass, String iv) {
        SecretKeySpec keySpec = new SecretKeySpec(Util.hexToByte(pass), "AES");
        AlgorithmParameterSpec paramSpec = new IvParameterSpec(Util.hexToByte(iv));

        File outFile = new File(this.outFilename);
        File srcFile = new File(this.srcFilename);

        try (FileOutputStream fos = new FileOutputStream(outFile);
             FileInputStream fis = new FileInputStream(srcFile)
        ) {
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            BufferedInputStream bis = new BufferedInputStream(fis);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

            int mode = this.mode == EncryptorMode.ENCRYPT ? Cipher.ENCRYPT_MODE : Cipher.DECRYPT_MODE;
            cipher.init(mode, keySpec, paramSpec);

            while (bis.available() > 0) {
                bos.write(cipher.update(bis.readNBytes(FileLoader.READ_BLOCK)));
            }

            bos.write(cipher.doFinal());
        } catch (FileNotFoundException e) {
            System.err.println("File not found");
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            System.err.println("INTERNAL: Invalid algorithm");
        } catch (InvalidKeyException e) {
            System.err.println("Invalid key");
        } catch (IllegalBlockSizeException | BadPaddingException | InvalidAlgorithmParameterException e) {
            System.err.println("Bad format: Invalid block size / padding / arguments don't meet requirements");
        } catch (IOException e) {
            System.err.println("Error while reading / writing file");
        }
    }
}
