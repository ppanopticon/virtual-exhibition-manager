package ch.unibas.dmi.dbis.vrem.model.exhibition;

import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

public class Exhibition {


    private final ObjectId id;

    private final String name;

    private final String description;

    private final List<Room> rooms = new ArrayList<>();


    /**
     *
     * @param id
     * @param name
     * @param description
     */
    public Exhibition(ObjectId id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    /**
     *
     * @param name
     * @param description
     */
    public Exhibition(String name, String description) {
        this.id = new ObjectId();
        this.name = name;
        this.description = description;
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
