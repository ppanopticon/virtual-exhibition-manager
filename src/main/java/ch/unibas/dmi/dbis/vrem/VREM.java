package ch.unibas.dmi.dbis.vrem;

import ch.unibas.dmi.dbis.vrem.server.WebServer;
import com.github.rvesse.airline.annotations.Cli;


import java.nio.file.Path;
import java.nio.file.Paths;

@Cli(name = "VREM", description = "Virtual Reality Exhibition Manager", commands = {WebServer.class}, defaultCommand = WebServer.class)
public class VREM {
    /** */
    private static final String MONGO_HOST = "127.0.0.1";

    /** */
    private static final int MONGO_PORT = 27017;

    /** */
    private static final String MONGO_DATABASE = "vrem";

    /** */
    private static final Path DOCUMENT_ROOT = Paths.get("/Users/gassra02/Downloads/vrem");

    /**
     *
     * @param args
     */
    public static void main(String [] args) {
        new com.github.rvesse.airline.Cli<Runnable>(VREM.class).parse(args).run();
    }
}
