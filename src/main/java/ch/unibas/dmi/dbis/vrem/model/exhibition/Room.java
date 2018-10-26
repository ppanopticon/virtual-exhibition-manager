package ch.unibas.dmi.dbis.vrem.model.exhibition;

import ch.unibas.dmi.dbis.vrem.model.Vector3f;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Room {

    public final String text;

    public final Vector3f size;

    public final Vector3f position;

    public final Vector3f entrypoint;

    /** */
    private final Map<Direction,Wall> walls = new HashMap<>();

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
     * @param direction
     * @param wall
     */
    public boolean placeWall(Direction direction, Wall wall) {
        if (!this.walls.containsKey(direction) && wall.room == null) {
            this.walls.put(direction, wall);
            wall.room = this;
            return true;
        } else {
            return false;
        }
    }

    /**
     *
     * @return
     */
    public Map<Direction,Wall> getWalls() {
        return Collections.unmodifiableMap(this.walls);
    }
}
