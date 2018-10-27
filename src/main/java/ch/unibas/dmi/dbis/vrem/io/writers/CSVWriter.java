package ch.unibas.dmi.dbis.vrem.io.writers;

import ch.unibas.dmi.dbis.vrem.io.AbstractCSV;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

/**
 * @author rgasser
 * @version 1.0
 * @created 24.11.16
 */
public class CSVWriter extends AbstractCSV {

    /**
     * Line the writer is currently processing.
     */
    private int linenumber = 0;

    /**
     * Buffered writer used to write data to the CSV.
     */
    protected BufferedWriter bw;

    /**
     * Default constructor; used the default values.
     *
     * @param writer Writer which to use with this AbstractCSVWriter.
     */
    public CSVWriter(Writer writer) {
        this(writer, DEFAULT_SEPARATOR, DEFAULT_QUOTE_CHARACTER, DEFAULT_SKIP_LINES);
    }

    /**
     * Constructor with custom separator, quote and line.
     *
     * @param writer Writer which to use with this AbstractCSVWriter.
     * @param separator Char used as a separator between columns.
     * @param quote Char used to quote strings. Can be null!
     * @param line Number of lines to skip before writing.
     */
    public CSVWriter(Writer writer, char separator, Character quote, int line) {
        super(separator, quote, line);
        this.bw = new BufferedWriter(writer);
    }

    /**
     *
     * @param tokens
     * @throws IOException
     */
    public void write(Object[] tokens) throws IOException {
        this.linenumber += 1;
        int i = 0;
        for (Object token : tokens) {
            if (i > 0) this.bw.append(this.separator);
            if (token instanceof Integer ||token instanceof Long || token instanceof Float || token instanceof Double || this.quote == null) {
                this.bw.append(token.toString());
            } else {
                this.bw.append(this.quote).append(token.toString()).append(this.quote);
            }
            i++;
        }
        this.bw.newLine();
        this.bw.flush();
    }

    /**
     *
     * @param entries
     * @throws IOException
     */
    public void writeAll(List<Object[]> entries) throws IOException {
        for (Object[] tokens : entries) {
            this.write(tokens);
        }
    }

    /**
     *
     * @throws IOException
     */
    public void close() throws IOException {
        this.bw.flush();
        this.bw.close();
    }
}
