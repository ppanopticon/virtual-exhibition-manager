package ch.unibas.dmi.dbis.vrem.model.exhibition;

import ch.unibas.dmi.dbis.vrem.model.Vector3f;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Wall {
    /** */
    public final Vector3f color;

    /** */
    public final Texture texture;

    /** */
    public final Direction direction;

    /** */
    private final List<Exhibit> exhibits = new ArrayList<>();

    /** */
    transient Room room = null;

    /**
     *
     * @param direction
     * @param color
     */
    public Wall(Direction direction, Vector3f color) {
        this.direction = direction;
        this.color = color;
        this.texture = Texture.NONE;
    }

    /**
     *
     * @param direction
     * @param texture
     */
    public Wall(Direction direction, Texture texture) {
        this.direction = direction;
        this.color = Vector3f.UNIT;
        this.texture = texture;
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
