package hr.fer.oprpp1.hw05.crypto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UtilTest {
    @Test
    void testHexToByte() {
        assertArrayEquals(new byte[]{1, -82, 34}, Util.hexToByte("01aE22"));
    }

    @Test
    void testHexToByteFailsForBadRange() {
        assertThrows(IllegalArgumentException.class, () -> Util.hexToByte("0"));
    }

    @Test
    void testByteToHex() {
        assertEquals("01ae22", Util.byteToHex(new byte[]{1, -82, 34}));
    }
}
