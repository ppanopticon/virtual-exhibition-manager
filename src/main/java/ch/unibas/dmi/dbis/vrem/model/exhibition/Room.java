package ch.unibas.dmi.dbis.vrem.model.exhibition;

import ch.unibas.dmi.dbis.vrem.model.Vector3f;

import java.util.*;

public class Room {

    public final String text;

    public final Vector3f size;

    public final Vector3f position;

    public final Vector3f entrypoint;

    /** */
    private final List<Wall> walls = new ArrayList<>(4);

    /** */
    transient Exhibition exhibition;

    /**
     *
     * @param text
     * @param size
     * @param position
     * @param entrypoint
     */
    public Room(String text, Vector3f size, Vector3f position, Vector3f entrypoint) {
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
        if (wall.room != null || this.walls.size() >= 4 || contained) {
            return false;
        } else {
            this.walls.add(wall);
            wall.room = this;
            return true;
        }
    }

    /**
     *
     * @return
     */
    public List<Wall> getWalls() {
        return Collections.unmodifiableList(this.walls);
    }
}
