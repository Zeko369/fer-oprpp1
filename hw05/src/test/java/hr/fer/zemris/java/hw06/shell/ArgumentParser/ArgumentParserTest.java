package hr.fer.zemris.java.hw06.shell.ArgumentParser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArgumentParserTest {
    @Test
    void throwsErrorForNull() {
        assertThrows(NullPointerException.class, () -> ArgumentParser.parse(null, 1));
        assertThrows(NullPointerException.class, () -> ArgumentParser.parse(null, 1, 2));
    }

    @Test
    void throwsOnLessThanZero() {
        assertThrows(IllegalArgumentException.class, () -> ArgumentParser.parse("test", -1));
        assertThrows(IllegalArgumentException.class, () -> ArgumentParser.parse("test", 0, -1));
    }

    @Test
    void throwsOnMaxLessThanMin() {
        assertThrows(IllegalArgumentException.class, () -> ArgumentParser.parse("test", 3, 1));
    }

    @Test
    void simpleTest() {
        String[] args = ArgumentParser.parse("foo bar", 2);
        assertEquals(2, args.length);
        assertArrayEquals(new String[]{"foo", "bar"}, args);
    }

    @Test
    void simpleTestWithQuotes() {
        String[] args = ArgumentParser.parse("foo \"bar\"", 2);
        assertEquals(2, args.length);
        assertArrayEquals(new String[]{"foo", "bar"}, args);
    }

    @Test
    void throwsForTooMany() {
        String rawArgs = "foo \"bar\" bar";
        assertThrows(ArgumentParserException.class, () -> ArgumentParser.parse(rawArgs, 1));
        assertThrows(ArgumentParserException.class, () -> ArgumentParser.parse(rawArgs, 1, 2));
    }

    @Test
    void throwsForNotEnough() {
        String rawArgs = "foo \"bar\"";
        assertThrows(ArgumentParserException.class, () -> ArgumentParser.parse(rawArgs, 3));
        assertThrows(ArgumentParserException.class, () -> ArgumentParser.parse(rawArgs, 3, 4));
    }

    @Test
    void returnEmptyForEmpty() {
        String[] out = ArgumentParser.parse("", 0);
        assertEquals(0, out.length);
    }
}
