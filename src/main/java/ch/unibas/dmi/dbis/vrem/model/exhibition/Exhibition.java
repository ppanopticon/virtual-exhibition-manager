package ch.unibas.dmi.dbis.vrem.model.exhibition;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.bson.types.ObjectId;

public class Exhibition {


    public final String id;

    public final String name;

    public final String description;

    private final List<Room> rooms = new ArrayList<>();

    /**
     *
     */
    public Exhibition(ObjectId id, String name, String description) {
        this.id = id.toString();
        this.name = name;
        this.description = description;
    }

    /**
     *
     */
    public Exhibition(String name, String description) {
        this(new ObjectId(), name, description);
    }

    /**
     *
     */
    public boolean addRoom(Room room) {
        if (!this.rooms.contains(room)) {
            this.rooms.add(room);
            return true;
        } else {
            return false;
        }
    }

    /**
     *
     */
    public List<Room> getRooms() {
        if (rooms == null) {
            return Collections.unmodifiableList(new ArrayList<>());
        }
        return Collections.unmodifiableList(this.rooms);
    }
}
