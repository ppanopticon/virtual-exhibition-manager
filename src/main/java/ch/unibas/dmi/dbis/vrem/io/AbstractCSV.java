package ch.unibas.dmi.dbis.vrem.io;

/**
 * @author rgasser
 * @version 1.0
 * @created 24.11.16
 */
public abstract class AbstractCSV implements AutoCloseable {

    /*
     * Default values for separator, quote and skipLines.
     */
    protected static final Character DEFAULT_SEPARATOR = ',';
    protected static final Character DEFAULT_QUOTE_CHARACTER = '"';
    protected static final int DEFAULT_SKIP_LINES = 0;

    /**
     * Character used as a separator between columns.
     */
    protected final char separator;

    /**
     * Character used to quote strings. Can be null!
     */
    protected final Character quote;

    /**
     *  Number of lines to skip before writing.
     */
    protected final int skipLines;

    /**
     * Constructor with custom separator, quote and line.
     *
     * @param separator char used as a separator between columns.
     * @param quote Chararacter used to quote strings. Can be null, which is why it's an object!
     * @param line Number of lines to skip before writing.
     */
    public AbstractCSV(char separator, Character quote, int line) {
        this.separator = separator;
        this.quote = quote;
        this.skipLines = line;
    }
}
