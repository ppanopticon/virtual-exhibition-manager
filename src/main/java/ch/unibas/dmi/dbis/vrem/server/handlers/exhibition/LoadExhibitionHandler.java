package ch.unibas.dmi.dbis.vrem.server.handlers.exhibition;

import ch.unibas.dmi.dbis.vrem.database.dao.VREMReader;
import ch.unibas.dmi.dbis.vrem.model.exhibition.Exhibition;
import ch.unibas.dmi.dbis.vrem.server.handlers.basic.ParsingActionHandler;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoadExhibitionHandler extends ParsingActionHandler<Exhibition> {

    private final VREMReader reader;

    private final static String ATTRIBUTE_ID = ":id";

    private final static Logger LOGGER = LogManager.getLogger();

    /**
     *
     */
    public LoadExhibitionHandler(VREMReader reader) {
        this.reader = reader;
    }

    @Override
    public Exhibition doGet(Map<String, String> parameters) {
        final String objectId = parameters.get(ATTRIBUTE_ID);
        LOGGER.debug("Loading exhibition {}", objectId);
        Exhibition exhibition = this.reader.getExhibition(objectId);
        if (exhibition == null) {
            LOGGER.warn("No exhibition found for id {}", objectId);
        } else {
            LOGGER.debug("Loaded exhibition with name {} successfully", exhibition.name);
        }
        return exhibition;
    }

    @Override
    public Class<Exhibition> inClass() {
        return Exhibition.class;
    }
}

