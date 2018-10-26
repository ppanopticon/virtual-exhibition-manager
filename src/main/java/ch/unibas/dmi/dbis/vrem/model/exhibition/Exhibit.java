package ch.unibas.dmi.dbis.vrem.model.exhibition;

import ch.unibas.dmi.dbis.vrem.model.Vector3f;
import ch.unibas.dmi.dbis.vrem.model.objects.CulturalHeritageObject;

import org.bson.types.ObjectId;

import java.net.URL;

public class Exhibit extends CulturalHeritageObject{
    /** */
    public Vector3f position = Vector3f.ORIGIN;

    /** */
    public Vector3f size = Vector3f.UNIT;
    
    /**
     *
     * @param name
     */
    public Exhibit(ObjectId id, String name, URL url, CHOType type) {
        super(id,name,url,type);
    }

    /**
     *
     * @param name
     */
    public Exhibit(String name, URL url, CHOType type) {
        super(new ObjectId(), name, url, type);
    }
}
