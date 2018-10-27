package ch.unibas.dmi.dbis.vrem.database.dao;

import com.mongodb.client.MongoDatabase;

public class VREMDao {

    public final static String EXHIBITION_COLLECTION = "exhibitions";


    protected final MongoDatabase database;

    /**
     *
     * @param database
     */
    public VREMDao(MongoDatabase database) {
        this.database = database;
    }
}
