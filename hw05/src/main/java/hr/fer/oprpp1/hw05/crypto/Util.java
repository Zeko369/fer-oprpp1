package hr.fer.oprpp1.hw05.crypto;

import java.nio.charset.StandardCharsets;

public class Util {
    public static byte[] hexToByte(String data) {
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

    public static String byteToHex(byte[] data) {
        StringBuilder sb = new StringBuilder();
        for (byte b : data) {
            sb.append(String.format("%02x", b));
        }

        return sb.toString();
    }
}
