package hr.fer.oprpp1.hw05.crypto;

/**
 * The type Util.
 *
 * @author franzekan
 */
public class Util {
    /**
     * Hex to byte[].
     *
     * @param data the data
     * @return the byte []
     * @throws IllegalArgumentException if len of data is not even
     */
    public static byte[] hexToByte(String data) throws IllegalArgumentException {
        if (data.length() % 2 == 1) {
            throw new IllegalArgumentException();
        }

        byte[] result = new byte[data.length() / 2];
        for (int i = 0; i < data.length(); i += 2) {
            byte tmp = (byte) (Character.digit(data.charAt(i), 16) << 4);
            result[i / 2] = (byte) (tmp + Character.digit(data.charAt(i + 1), 16));
        }

        return result;
    }

    /**
     * Byte[] to hex string.
     *
     * @param data the data
     * @return the string
     */
    public static String byteToHex(byte[] data) {
        StringBuilder sb = new StringBuilder();
        for (byte b : data) {
            sb.append(String.format("%02x", b));
        }

        return sb.toString();
    }
}
