package ch.unibas.dmi.dbis.vrem.server.handlers.exhibition;

import ch.unibas.dmi.dbis.vrem.database.dao.VREMReader;
import ch.unibas.dmi.dbis.vrem.server.handlers.basic.ParsingActionHandler;
import ch.unibas.dmi.dbis.vrem.server.handlers.basic.ActionHandlerException;

import java.util.Map;

public class ListExhibitionsHandler extends ParsingActionHandler<Map> {



    private final VREMReader reader;

    /**
     *
     * @param reader
     */
    public ListExhibitionsHandler(VREMReader reader) {
        this.reader = reader;
    }


    @Override
    public Object doGet(Map<String, String> parameters) throws ActionHandlerException {
       return this.reader.listExhibitions();
    }

    @Override
    public Class<Map> inClass() {
        return Map.class;
    }
}
