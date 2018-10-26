package ch.unibas.dmi.dbis.vrem.model.objects;

import ch.unibas.dmi.dbis.vrem.model.Vector3f;
import org.bson.types.ObjectId;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class CulturalHeritageObject {

    public final ObjectId id;

    public final String name;

    public final CHOType type;

    public final URL path;

    public String description;


    public final Map<String,String> metadata = new HashMap<>();


    /**
     *
     * @param name
     */
    public CulturalHeritageObject(ObjectId id, String name, URL url, CHOType type) {
        this.id = id;
        this.name = name;
        this.path = url;
        this.type = type;
    }

    /**
     *
     * @param name
     */
    public CulturalHeritageObject(String name, URL url, CHOType type) {
        this(new ObjectId(), name, url, type);
    }

    /**
     *
     */
    public enum CHOType {
        IMAGE, MODEL
    }
}
