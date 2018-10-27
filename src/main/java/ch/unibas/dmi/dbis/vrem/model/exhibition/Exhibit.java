package ch.unibas.dmi.dbis.vrem.model.exhibition;

import ch.unibas.dmi.dbis.vrem.model.Vector3f;
import ch.unibas.dmi.dbis.vrem.model.objects.CulturalHeritageObject;

import org.bson.types.ObjectId;

public class Exhibit extends CulturalHeritageObject{
    /** */
    public Vector3f position = Vector3f.ORIGIN;

    /** */
    public Vector3f size = Vector3f.UNIT;

    /**
     *
     * @param id
     * @param name
     * @param description
     * @param path
     * @param type
     */
    public Exhibit(ObjectId id, String name, String description, String path, CHOType type) {
        super(id,name, description,path,type);
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
        super(id,name, description,path,type);
        this.size = size;
        this.position = position;
    }

    /**
     *
     * @param name
     * @param description
     * @param path
     * @param type
     */
    public Exhibit(String name, String description, String path, CHOType type) {
        super(new ObjectId(), name, description, path, type);
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
}
