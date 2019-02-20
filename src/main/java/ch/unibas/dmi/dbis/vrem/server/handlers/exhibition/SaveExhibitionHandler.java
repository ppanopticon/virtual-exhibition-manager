package ch.unibas.dmi.dbis.vrem.server.handlers.exhibition;

import ch.unibas.dmi.dbis.vrem.database.dao.VREMWriter;
import ch.unibas.dmi.dbis.vrem.model.exhibition.Exhibition;

import ch.unibas.dmi.dbis.vrem.server.handlers.basic.ParsingActionHandler;

import java.util.Map;

public class SaveExhibitionHandler extends ParsingActionHandler<Exhibition> {

    private final VREMWriter writer;

    /**
     * Default constructor
     *
     * @param writer
     */
    public SaveExhibitionHandler(VREMWriter writer) {
        this.writer = writer;
    }
    
    @Override
    public Exhibition doPost(Exhibition context, Map<String, String> parameters) {
        this.writer.saveExhibition(context);
        return context;
    }

    @Override
    public Class<Exhibition> inClass() {
        return Exhibition.class;
    }
}
