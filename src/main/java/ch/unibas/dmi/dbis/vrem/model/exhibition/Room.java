package ch.unibas.dmi.dbis.vrem.model.exhibition;

import ch.unibas.dmi.dbis.vrem.model.Vector3f;
import ch.unibas.dmi.dbis.vrem.model.objects.CulturalHeritageObject;

import java.util.*;

public class Room {

    public final String text;

    public final Texture floor;

    public final Vector3f size;

    public final Vector3f position;

    public final Vector3f entrypoint;

    /** List of walls (4 max). */
    private final List<Wall> walls = new ArrayList<>(4);

    /** List of exhibits (only 3D models valid). */
    private final List<Exhibit> exhibits = new ArrayList<>();

    /**
     *
     * @param text
     * @param floor
     * @param size
     * @param position
     * @param entrypoint
     */
    public Room(String text, Texture floor, Vector3f size, Vector3f position, Vector3f entrypoint) {
        this.floor = floor;
        this.size = size;
        this.text = text;
        this.position = position;
        this.entrypoint = entrypoint;
    }

    /**
     *
     * @param wall
     */
    public boolean placeWall(Wall wall) {
        boolean contained = this.walls.stream().anyMatch(w -> w.direction == wall.direction);
        if (this.walls.size() >= 4 || contained) {
            return false;
        } else {
            this.walls.add(wall);
            return true;
        }
    }

    /**
     *
     * @param exhibit
     * @return
     */
    public boolean placeExhibit(Exhibit exhibit) {
        if (exhibit.type != CulturalHeritageObject.CHOType.MODEL) {
            throw new IllegalArgumentException("Only 3D objects can be placed in a room.");
        }
        if (!this.exhibits.contains(exhibit)) {
            this.exhibits.add(exhibit);
            return true;
        }
        return false;
    }

    /**
     *
     * @return
     */
    public List<Wall> getWalls() {
        return Collections.unmodifiableList(this.walls);
    }

    /**
     *
     * @return
     */
    public List<Exhibit> getExhibits() {
        return Collections.unmodifiableList(this.exhibits);
    }
}
