package ch.unibas.dmi.dbis.vrem.server.handlers.exhibition;

import ch.unibas.dmi.dbis.vrem.database.dao.VREMReader;
import ch.unibas.dmi.dbis.vrem.model.exhibition.Exhibition;
import ch.unibas.dmi.dbis.vrem.server.handlers.basic.ParsingActionHandler;
import org.bson.types.ObjectId;

import java.util.Map;

public class LoadExhibitionHandler extends ParsingActionHandler<Exhibition> {

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
    public Exhibition doGet(Map<String, String> parameters) {
        final String objectId = parameters.get(ATTRIBUTE_ID);
        return this.reader.getExhibition(new ObjectId(objectId));
    }

    @Override
    public Class<Exhibition> inClass() {
        return Exhibition.class;
    }
}

