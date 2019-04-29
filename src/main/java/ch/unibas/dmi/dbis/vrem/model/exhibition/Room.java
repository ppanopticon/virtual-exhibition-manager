package ch.unibas.dmi.dbis.vrem.model.exhibition;

import ch.unibas.dmi.dbis.vrem.model.Vector3f;
import ch.unibas.dmi.dbis.vrem.model.objects.CulturalHeritageObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Room {

    public final String text;

    public final String floor;

    public final String ceiling;

    public Vector3f size;
    public Vector3f entrypoint;
    public final String ambient;
    /**
     * List of exhibits (only 3D models valid).
     */
    private List<Exhibit> exhibits = new ArrayList<>();
    public Vector3f position;
    /**
     * List of walls (4 max).
     */
    private List<Wall> walls = new ArrayList<>(4);

    public Room(String text, Texture floor, Texture ceiling, Vector3f size, Vector3f position, Vector3f entrypoint) {
        this(text, new ArrayList<>(4), floor.name(), ceiling.name(), size, position, entrypoint, null);
    }

    public Room(String text, List<Wall> walls, Texture floor, Texture ceiling, Vector3f size, Vector3f position, Vector3f entrypoint) {
        this(text, walls, floor.toString(), ceiling.toString(), size, position, entrypoint, null);
    }

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

    public Wall getNorth() {
        return this.walls.stream().filter(w -> w.direction == Direction.NORTH).findFirst().orElseThrow(() -> new IllegalStateException("This room is corrupted!"));
    }

    public void setNorth(Wall wall) {
        setWall(Direction.NORTH, wall);
    }

    private void setWall(Direction dir, Wall w) {
        if (w.direction != dir) {
            throw new IllegalArgumentException("Wall direction not matching. Expected " + dir + ", but " + w.direction + " given");
        }
        //this.walls.add(dir.ordinal(),w);
        if (this.walls == null) {
            this.walls = new ArrayList<>();
        }
        this.walls.add(w);
    }

    public Wall getEast() {
        return this.walls.stream().filter(w -> w.direction == Direction.EAST).findFirst().orElseThrow(() -> new IllegalStateException("This room is corrupted!"));
    }

    public void setEast(Wall wall) {
        setWall(Direction.EAST, wall);
    }

    public Wall getSouth() {
        return this.walls.stream().filter(w -> w.direction == Direction.SOUTH).findFirst().orElseThrow(() -> new IllegalStateException("This room is corrupted!"));
    }

    public void setSouth(Wall wall) {
        setWall(Direction.SOUTH, wall);
    }

    public Wall getWest() {
        return this.walls.stream().filter(w -> w.direction == Direction.WEST).findFirst().orElseThrow(() -> new IllegalStateException("This room is corrupted!"));
    }

    public void setWest(Wall wall) {
        setWall(Direction.WEST, wall);
    }

    @Override
    public String toString() {
        return "Room{" + "text='" + text + '\'' + ", floor='" + floor + '\'' + ", ceiling='" + ceiling + '\'' + ", size=" + size + ", entrypoint=" + entrypoint + ", ambient='" + ambient + '\'' + ", exhibits=" + exhibits + ", position=" + position + ", walls=" + walls + '}';
    }

    public List<Exhibit> getExhibits() {
        if (this.exhibits == null) {
            exhibits = new ArrayList<>();
        }
        return Collections.unmodifiableList(this.exhibits);
    }
}
