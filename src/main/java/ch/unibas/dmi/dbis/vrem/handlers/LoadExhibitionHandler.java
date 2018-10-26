package ch.unibas.dmi.dbis.vrem.handlers;

import ch.unibas.dmi.dbis.vrem.database.dao.VREMReader;
import org.bson.types.ObjectId;

import java.util.Map;

public class LoadExhibitionHandler extends ParsingActionHandler<Map> {

    private final VREMReader reader;

    private final static String ATTRIBUTE_ID = ":id";

    /**
     *
     * @param reader
     */
    public LoadExhibitionHandler(VREMReader reader) {
        this.reader = reader;
    }

    @Override
    public Object doGet(Map<String, String> parameters) {
        final String objectId = parameters.get(ATTRIBUTE_ID);
        return this.reader.getExhibition(new ObjectId(objectId));
    }

    @Override
    public Class<Map> inClass() {
        return Map.class;
    }
}

