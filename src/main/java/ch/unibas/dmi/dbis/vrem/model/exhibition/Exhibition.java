package ch.unibas.dmi.dbis.vrem.model.exhibition;

import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

public class Exhibition {


    public final String id;

    public final String name;

    public final String description;

    public final List<Room> rooms = new ArrayList<>();

    /**
     *
     * @param id
     * @param name
     * @param description
     */
    public Exhibition(ObjectId id, String name, String description) {
        this.id = id.toString();
        this.name = name;
        this.description = description;
    }

    /**
     *
     * @param name
     * @param description
     */
    public Exhibition(String name, String description) {
        this(new ObjectId(), name, description);
    }

    /**
     *
     * @param room
     */
    public void addRoom(Room room) {
        if (!this.rooms.contains(room)) {
            this.rooms.add(room);
        }
    }
}
