package ch.unibas.dmi.dbis.vrem.model.exhibition;

import ch.unibas.dmi.dbis.vrem.model.Vector3f;
import ch.unibas.dmi.dbis.vrem.model.objects.CulturalHeritageObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Wall {
    /** */
    public final Vector3f color;

    /** */
    public final Texture texture;

    /** */
    public Direction direction;

    /** */
    private final List<Exhibit> exhibits = new ArrayList<>();

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
        if (exhibit.type != CulturalHeritageObject.CHOType.IMAGE) {
            throw new IllegalArgumentException("Only images can be placed on walls.");
        }
        if (!this.exhibits.contains(exhibit)) {
            this.exhibits.add(exhibit);
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
