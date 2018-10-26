package ch.unibas.dmi.dbis.vrem.handlers;

import ch.unibas.dmi.dbis.vrem.database.dao.VREMReader;

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
