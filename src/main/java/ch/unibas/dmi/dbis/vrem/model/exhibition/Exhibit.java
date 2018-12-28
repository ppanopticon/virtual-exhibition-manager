package ch.unibas.dmi.dbis.vrem.model.exhibition;

import ch.unibas.dmi.dbis.vrem.model.Vector3f;
import ch.unibas.dmi.dbis.vrem.model.objects.CulturalHeritageObject;

import org.bson.types.ObjectId;

public class Exhibit extends CulturalHeritageObject{
    /** */
    public final Vector3f position;

    /** */
    public Vector3f size ;

    /** */
    public final String audio;

    /** */
    public final boolean light;

    /**
     *
     * @param id
     * @param name
     * @param description
     * @param path
     * @param type
     */
    public Exhibit(ObjectId id, String name, String description, String path, CHOType type) {
        this(id,name, description,path,type, Vector3f.ORIGIN, Vector3f.UNIT, null, false);
    }

    /**
     *
     * @param id
     * @param name
     * @param description
     * @param path
     * @param type
     */
    public Exhibit(ObjectId id, String name, String description, String path, CHOType type, Vector3f position, Vector3f size) {
        this(id,name, description,path,type, position, size, null, false);
    }

    /**
     *
     * @param id
     * @param name
     * @param description
     * @param path
     * @param type
     */
    public Exhibit(ObjectId id, String name, String description, String path, CHOType type, Vector3f position, Vector3f size, String audio, boolean light) {
        super(id,name, description,path,type);
        this.size = size;
        this.position = position;
        this.audio = audio;
        this.light = light;
    }

    /**
     *
     * @param name
     * @param description
     * @param path
     * @param type
     */
    public Exhibit(String name, String description, String path, CHOType type) {
        this(new ObjectId(), name, description, path, type);
    }

    /**
     *
     * @param name
     * @param description
     * @param path
     * @param type
     * @param position
     * @param size
     */
    public Exhibit(String name, String description, String path, CHOType type, Vector3f position, Vector3f size) {
        this(new ObjectId(), name, description, path, type, position, size);
    }


    /**
     *
     * @param name
     * @param description
     * @param path
     * @param type
     * @param position
     * @param size
     */
    public Exhibit(String name, String description, String path, CHOType type, Vector3f position, Vector3f size, String audio, boolean light) {
        this(new ObjectId(), name, description, path, type, position, size, audio, light);
    }
}
