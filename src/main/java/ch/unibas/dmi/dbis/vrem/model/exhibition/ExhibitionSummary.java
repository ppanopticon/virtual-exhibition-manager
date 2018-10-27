package ch.unibas.dmi.dbis.vrem.model.exhibition;

import org.bson.types.ObjectId;

public class ExhibitionSummary {
    public String objectId;

    public String name;

    /**
     *
     * @param id
     * @param name
     */
    public ExhibitionSummary(ObjectId id, String name) {
        this.objectId = id.toHexString();
        this.name = name;
    }
}
