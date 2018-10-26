package ch.unibas.dmi.dbis.vrem;

import ch.unibas.dmi.dbis.vrem.database.codec.VREMCodecProvider;
import ch.unibas.dmi.dbis.vrem.database.dao.VREMReader;
import ch.unibas.dmi.dbis.vrem.handlers.ListExhibitionsHandler;
import ch.unibas.dmi.dbis.vrem.handlers.LoadExhibitionHandler;
import com.google.gson.Gson;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;


import static spark.Spark.*;

public class VREM {


    /** */
    private static final String MONGO_HOST = "127.0.0.1";

    /** */
    private static final int MONGO_PORT = 27017;

    /** */
    private static final String MONGO_DATABASE = "vrem";

    /**
     *
     * @param args
     */
    public static void main(String [] args) {
        final CodecRegistry registry = CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), CodecRegistries.fromProviders(new VREMCodecProvider()));
        final ConnectionString connectionString = new ConnectionString(String.format("mongodb://%s:%d", MONGO_HOST, MONGO_PORT));
        final MongoClientSettings settings = MongoClientSettings.builder().codecRegistry(registry).applyConnectionString(connectionString).applicationName("VREM").build();

        final MongoClient client = MongoClients.create(settings);
        final MongoDatabase db = client.getDatabase(MONGO_DATABASE);
        final VREMReader reader = new VREMReader(db);




        get("/exhibitions/list", new ListExhibitionsHandler(reader));
        get("/exhibitions/load/:id", new LoadExhibitionHandler(reader));
    }
}
