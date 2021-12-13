package hr.fer.zemris.java.hw06.shell;

import hr.fer.zemris.java.hw06.shell.commands.ShellCommand;

import java.util.SortedMap;

/**
 * The interface Environment.
 *
 * @author franzekan
 */
public interface Environment {
    /**
     * Read line string.
     *
     * @return the string
     * @throws ShellIOException the shell io exception
     */
    String readLine() throws ShellIOException;

    /**
     * Write.
     *
     * @param text the text
     * @throws ShellIOException the shell io exception
     */
    void write(String text) throws ShellIOException;

    /**
     * Writeln.
     *
     * @param text the text
     * @throws ShellIOException the shell io exception
     */
    void writeln(String text) throws ShellIOException;

    /**
     * Errorln.
     *
     * @param text the text
     * @throws ShellIOException the shell io exception
     */
    void errorln(String text) throws ShellIOException;

    /**
     * Commands sorted map.
     *
     * @return the sorted map
     */
    SortedMap<String, ShellCommand> commands();

    /**
     * Gets multiline symbol.
     *
     * @return the multiline symbol
     */
    Character getMultilineSymbol();

    /**
     * Sets multiline symbol.
     *
     * @param symbol the symbol
     */
    void setMultilineSymbol(Character symbol);

    /**
     * Gets prompt symbol.
     *
     * @return the prompt symbol
     */
    Character getPromptSymbol();

    /**
     * Sets prompt symbol.
     *
     * @param symbol the symbol
     */
    void setPromptSymbol(Character symbol);

    /**
     * Gets morelines symbol.
     *
     * @return the morelines symbol
     */
    Character getMorelinesSymbol();

    /**
     * Sets morelines symbol.
     *
     * @param symbol the symbol
     */
    void setMorelinesSymbol(Character symbol);
}
