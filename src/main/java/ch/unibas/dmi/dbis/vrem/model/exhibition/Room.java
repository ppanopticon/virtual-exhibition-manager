package ch.unibas.dmi.dbis.vrem.model.exhibition;

import ch.unibas.dmi.dbis.vrem.model.Vector3f;
import ch.unibas.dmi.dbis.vrem.model.objects.CulturalHeritageObject;

import java.util.*;

public class Room {

    public final String text;

    public final String floor;

    public final String ceiling;

    public final Vector3f size;

    public final Vector3f position;

    public final Vector3f entrypoint;


    public final String ambient;


    /** List of walls (4 max). */
    private final List<Wall> walls = new ArrayList<>(4);

    /** List of exhibits (only 3D models valid). */
    private final List<Exhibit> exhibits = new ArrayList<>();

    /**
     *
     * @param text
     * @param walls
     * @param floor
     * @param ceiling
     * @param size
     * @param position
     * @param entrypoint
     */
    public Room(String text, List<Wall> walls, Texture floor, Texture ceiling, Vector3f size, Vector3f position, Vector3f entrypoint) {
        this(text, walls, floor.toString(), ceiling.toString(), size, position, entrypoint, null);
    }

    /**
     *
     * @param text
     * @param walls
     * @param floor
     * @param ceiling
     * @param size
     * @param position
     * @param entrypoint
     */
    public Room(String text, List<Wall> walls, String floor, String ceiling, Vector3f size, Vector3f position, Vector3f entrypoint, String ambient) {
        this.floor = floor;
        this.ceiling = ceiling;
        this.size = size;
        this.text = text;
        this.position = position;
        this.entrypoint = entrypoint;
        this.walls.addAll(walls);
        this.ambient = ambient;
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
    public Wall getNorth() {
        return this.walls.stream().filter(w -> w.direction == Direction.NORTH).findFirst().orElseThrow(() -> new IllegalStateException("This room is corrupted!"));
    }

    /**
     *
     * @return
     */
    public Wall getEast() {
        return this.walls.stream().filter(w -> w.direction == Direction.EAST).findFirst().orElseThrow(() -> new IllegalStateException("This room is corrupted!"));
    }

    /**
     *
     * @return
     */
    public Wall getSouth() {
        return this.walls.stream().filter(w -> w.direction == Direction.SOUTH).findFirst().orElseThrow(() -> new IllegalStateException("This room is corrupted!"));
    }

    /**
     *
     * @return
     */
    public Wall getWest() {
        return this.walls.stream().filter(w -> w.direction == Direction.WEST).findFirst().orElseThrow(() -> new IllegalStateException("This room is corrupted!"));
    }

    /**
     *
     * @return
     */
    public List<Exhibit> getExhibits() {
        return Collections.unmodifiableList(this.exhibits);
    }
}
