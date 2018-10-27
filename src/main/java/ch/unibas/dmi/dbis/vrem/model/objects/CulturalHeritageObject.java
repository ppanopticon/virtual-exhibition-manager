package ch.unibas.dmi.dbis.vrem.model.objects;

import org.bson.types.ObjectId;

import java.util.HashMap;
import java.util.Map;

public class CulturalHeritageObject {

    public final String id;

    public final String name;

    public final CHOType type;

    public final String path;

    public final String description;

    public final Map<String,String> metadata = new HashMap<>();

    /**
     *
     * @param id
     * @param name
     * @param description
     * @param path
     * @param type
     */
    public CulturalHeritageObject(ObjectId id, String name, String description, String path, CHOType type) {
        this.id = id.toHexString();
        this.name = name;
        this.description = description;
        this.path = path;
        this.type = type;
    }

    /**
     *
     * @param name
     * @param description
     * @param path
     * @param type
     */
    public CulturalHeritageObject(String name, String description, String path, CHOType type) {
        this(new ObjectId(), name, description, path, type);
    }

    /**
     *
     */
    public enum CHOType {
        IMAGE, MODEL
    }
}
