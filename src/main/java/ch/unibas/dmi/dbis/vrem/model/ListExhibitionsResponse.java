package ch.unibas.dmi.dbis.vrem.model;

import ch.unibas.dmi.dbis.vrem.model.exhibition.ExhibitionSummary;

import java.util.List;

public class ListExhibitionsResponse {

    public final List<ExhibitionSummary> exhibitions;

    public ListExhibitionsResponse(List<ExhibitionSummary> exhibitions) {
        this.exhibitions = exhibitions;
    }

    public List<ExhibitionSummary> getExhibitions() {
        return exhibitions;
    }
}
