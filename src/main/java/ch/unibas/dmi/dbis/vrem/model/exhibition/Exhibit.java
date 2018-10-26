package ch.unibas.dmi.dbis.vrem.model.exhibition;

import ch.unibas.dmi.dbis.vrem.model.Vector3f;
import ch.unibas.dmi.dbis.vrem.model.objects.CulturalHeritageObject;

import org.bson.types.ObjectId;

public class Exhibit extends CulturalHeritageObject{
    /** */
    public Vector3f position = Vector3f.ORIGIN;

    /** */
    public Vector3f size = Vector3f.UNIT;

    /** */
    transient Wall wall = null;

    /**
     *
     * @param name
     */
    public Exhibit(ObjectId id, String name, String path, CHOType type) {
        super(id,name,path,type);
    }

    /**
     *
     * @param name
     */
    public Exhibit(String name, String path, CHOType type) {
        super(new ObjectId(), name, path, type);
    }

}
