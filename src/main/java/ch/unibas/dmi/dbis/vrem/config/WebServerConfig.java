package ch.unibas.dmi.dbis.vrem.config;

import java.nio.file.Path;
import java.nio.file.Paths;

public class WebServerConfig {

    /** */
    private String documentRoot;

    /** */
    private short port;

    public Path getDocumentRoot() {
        return Paths.get(documentRoot);
    }

    public short getPort() {
        return port;
    }


}
