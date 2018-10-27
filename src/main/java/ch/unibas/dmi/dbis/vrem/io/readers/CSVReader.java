package ch.unibas.dmi.dbis.vrem.io.readers;

import ch.unibas.dmi.dbis.vrem.io.AbstractCSV;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author rgasser
 * @version 1.0
 * @created 24.11.16
 */
public class CSVReader extends AbstractCSV {

    protected BufferedReader br;

    protected boolean hasNext = true;

    protected int linesCount = 0;

    protected boolean linesSkiped;

    /**
     *
     * @param reader
     */
    public CSVReader(Reader reader) {
        this(reader, DEFAULT_SEPARATOR, DEFAULT_QUOTE_CHARACTER, DEFAULT_SKIP_LINES);
    }

    /**
     *
     * @param reader
     * @param separator
     * @param quotechar
     * @param line
     */
    public CSVReader(Reader reader, char separator, Character quotechar, int line) {
        super(separator, quotechar, line);
        this.br = new BufferedReader(reader);
    }

    public String[] readNext() throws IOException {
        String nextLine = getNextLine();
        return hasNext ? parseLine(nextLine) : null;
    }

    public String getNextLine() throws IOException {
        if (!this.linesSkiped) {
            for (int i = 0; i < skipLines; i++) {
                br.readLine();
            }
            this.linesSkiped = true;
        }
        String nextLine = br.readLine();
        if (nextLine == null) {
            hasNext = false;
        }
        return hasNext ? nextLine : null;
    }


    public List<String[]> readAll() throws IOException {

        List<String[]> allElements = new ArrayList<String[]>();
        while (hasNext) {
            String[] nextLineAsTokens = readNext();
            if (nextLineAsTokens != null)
                allElements.add(nextLineAsTokens);
        }
        return allElements;

    }

    private String[] parseLine(String nextLine) throws IOException {

        if (nextLine == null) {
            return null;
        }

        List<String> tokensOnThisLine = new ArrayList<String>();
        StringBuffer sb = new StringBuffer();
        boolean inQuotes = false;
        do {
            if (inQuotes) {
                // continuing a quoted section, reappend newline
                sb.append("\n");
                nextLine = getNextLine();
                linesCount++;
                if (nextLine == null)

                    break;
            }
            for (int i = 0; i < nextLine.length(); i++) {
                char c = nextLine.charAt(i);
                if (this.quote != null && this.quote.equals(c)) {
                    if( inQuotes
                            && nextLine.length() > (i+1)
                            && quote.equals(nextLine.charAt(i+1))) {
                        sb.append(nextLine.charAt(i+1));
                        i++;
                    }else{
                        inQuotes = !inQuotes;
                        if(i>2
                                && nextLine.charAt(i-1) != this.separator
                                && nextLine.length()>(i+1) &&
                                nextLine.charAt(i+1) != this.separator
                                ){
                            sb.append(c);
                        }
                    }
                } else if (c == separator && !inQuotes) {
                    tokensOnThisLine.add(sb.toString());
                    sb = new StringBuffer();
                } else {
                    sb.append(c);
                }
            }
        } while (inQuotes);
        tokensOnThisLine.add(sb.toString());
        return (String[]) tokensOnThisLine.toArray(new String[0]);

    }

    /**
     *
     * @throws IOException
     */
    public void close() throws IOException{
        br.close();
    }
}