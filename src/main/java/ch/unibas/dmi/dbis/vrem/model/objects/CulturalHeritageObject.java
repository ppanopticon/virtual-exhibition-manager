package ch.unibas.dmi.dbis.vrem.model.objects;

import org.bson.types.ObjectId;

import java.util.HashMap;
import java.util.Map;

public class CulturalHeritageObject {

    public final String id;

    public final String name;

    public final CHOType type;

    public final String path;

    public String description;


    public final Map<String,String> metadata = new HashMap<>();


    /**
     *
     * @param name
     */
    public CulturalHeritageObject(ObjectId id, String name, String path, CHOType type) {
        this.id = id.toHexString();
        this.name = name;
        this.path = path;
        this.type = type;
    }

    /**
     *
     * @param name
     */
    public CulturalHeritageObject(String name, String path, CHOType type) {
        this(new ObjectId(), name, path, type);
    }

    /**
     *
     */
    public enum CHOType {
        IMAGE, MODEL
    }
}
