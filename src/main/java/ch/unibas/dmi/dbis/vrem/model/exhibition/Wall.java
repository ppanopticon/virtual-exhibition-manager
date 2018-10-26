package ch.unibas.dmi.dbis.vrem.model.exhibition;

import ch.unibas.dmi.dbis.vrem.model.Vector3f;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Wall {

    /** */
    public final Vector3f position;

    /** */
    public final Vector3f color;

    /** */
    public final Direction direction;

    /** */
    private final List<Exhibit> exhibits = new ArrayList();

    /** */
    transient Room room = null;

    /**
     *
     * @param position
     */
    public Wall(Direction direction, Vector3f position) {
        this(direction, position, new Vector3f(0.75f, 0.75f, 0.75f));
    }

    /**
     *
     * @param position
     */
    public Wall(Direction direction, Vector3f position, Vector3f color) {
        this.direction = direction;
        this.position = position;
        this.color = color;
    }

    /**
     *
     * @param exhibit
     */
    public boolean placeExhibit(Exhibit exhibit) {
        if (exhibit.wall == null) {
            this.exhibits.add(exhibit);
            exhibit.wall = this;
            return true;
        } else {
            return false;
        }
    }

    /**
     *
     * @return
     */
    public List<Exhibit> getExhibits() {
        return Collections.unmodifiableList(this.exhibits);
    }
}
