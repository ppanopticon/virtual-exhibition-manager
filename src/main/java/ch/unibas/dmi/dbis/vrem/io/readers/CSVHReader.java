package ch.unibas.dmi.dbis.vrem.io.readers;

import java.io.IOException;
import java.io.Reader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author rgasser
 * @version 1.0
 * @created 24.11.16
 */
public class CSVHReader extends CSVReader {

    /**
     *
     */
    private String[] header;

    /**
     * @param reader
     */
    public CSVHReader(Reader reader) throws IOException {
        super(reader);

        /* Read the first line, which will now act as header. */
        this.header = readNext();
    }

    /**
     * @param reader
     * @param separator
     * @param quotechar
     * @param line
     */
    public CSVHReader(Reader reader, char separator, Character quotechar, int line)  throws IOException {
        super(reader, separator, quotechar, line);

        /* Read the first line, which will now act as header. */
        this.header = this.readNext();
    }

    /**
     *
     * @return
     */
    public List<HashMap<String,String>> readAllWithHeader() throws IOException {
        List<HashMap<String,String>> allElements = new ArrayList<>();
        while (this.hasNext) {
            allElements.add(this.readNextWithHeader());
        }
        return allElements;
    }

    /**
     *
     * @return
     */
    public HashMap<String,String> readNextWithHeader() throws IOException {
        String[] values = this.readNext();
        if (values != null) {
            HashMap<String, String> map = new HashMap<>();
            for (int i = 0; i < this.header.length; i++) {
                if (i < values.length) {
                    map.put(this.header[i], values[i]);
                } else {
                    map.put(this.header[i], null);
                }
            }
            return map;
        } else {
            return null;
        }

    }

    /**
     *
     * @return
     */
    public String[] getHeader() {
        return this.header;
    }
}
