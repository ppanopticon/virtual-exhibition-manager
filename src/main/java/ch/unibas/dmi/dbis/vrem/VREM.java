package ch.unibas.dmi.dbis.vrem;

import ch.unibas.dmi.dbis.vrem.database.codec.VREMCodecProvider;
import ch.unibas.dmi.dbis.vrem.database.dao.VREMReader;
import ch.unibas.dmi.dbis.vrem.server.WebServer;
import ch.unibas.dmi.dbis.vrem.server.handlers.exhibition.ListExhibitionsHandler;
import ch.unibas.dmi.dbis.vrem.server.handlers.exhibition.LoadExhibitionHandler;

import ch.unibas.dmi.dbis.vrem.server.handlers.content.RequestContentHandler;
import com.github.rvesse.airline.annotations.Cli;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;


import java.nio.file.Path;
import java.nio.file.Paths;

import static spark.Spark.*;

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
