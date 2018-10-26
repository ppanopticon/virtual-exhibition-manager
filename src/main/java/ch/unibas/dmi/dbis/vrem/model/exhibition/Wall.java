package ch.unibas.dmi.dbis.vrem.model.exhibition;

import ch.unibas.dmi.dbis.vrem.model.Vector3f;

import java.util.ArrayList;
import java.util.List;

public class Wall {

    /** */
    public final Vector3f position;

    /** */
    public final Vector3f color;

    /** */
    public final List<Exhibit> exhibits = new ArrayList();

    /**
     *
     * @param position
     */
    public Wall(Vector3f position) {
        this(position, new Vector3f(0.75f, 0.75f, 0.75f));
    }

    /**
     *
     * @param position
     */
    public Wall(Vector3f position, Vector3f color) {
        this.position = position;
        this.color = color;
    }
}
