package ch.unibas.dmi.dbis.vrem;

import ch.unibas.dmi.dbis.vrem.importer.DemoImporter;
import ch.unibas.dmi.dbis.vrem.importer.MuseumNightImporter;
import ch.unibas.dmi.dbis.vrem.server.WebServer;
import com.github.rvesse.airline.annotations.Cli;

@Cli(name = "VREM", description = "Virtual Reality Exhibition Manager", commands = {WebServer.class, DemoImporter.class,
    MuseumNightImporter.class}, defaultCommand = WebServer.class)
public class VREM {
    /**
     *
     * @param args
     */
    public static void main(String [] args) {
        new com.github.rvesse.airline.Cli<Runnable>(VREM.class).parse(args).run();
    }
}
