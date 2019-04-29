package ch.unibas.dmi.dbis.vrem.database.codec;

import ch.unibas.dmi.dbis.vrem.model.Vector3f;
import ch.unibas.dmi.dbis.vrem.model.exhibition.*;

import org.bson.BsonReader;
import org.bson.BsonType;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.types.ObjectId;

import java.util.LinkedList;
import java.util.List;


public class ExhibitionCodec implements Codec<Exhibition> {

    public final String FIELD_NAME_ID = "_id";
    public final String FIELD_NAME_TEXT = "name";
    public final String FIELD_NAME_DESCRIPTION = "description";
    public final String FIELD_NAME_ROOMS = "rooms";

    private final Codec<Room> codec;

    public ExhibitionCodec(CodecRegistry registry) {
        this.codec = registry.get(Room.class);
    }

    @Override
    public Exhibition decode(BsonReader reader, DecoderContext decoderContext) {
        reader.readStartDocument();
        String id = null;
        String name = null;
        String description = null;
        List<Room> rooms = new LinkedList<>();

        while(reader.readBsonType() != BsonType.END_OF_DOCUMENT) {
            switch (reader.readName()) {
                case FIELD_NAME_ID:
                    id = reader.readString();
                    break;
                case FIELD_NAME_TEXT:
                    name = reader.readString();
                    break;
                case FIELD_NAME_DESCRIPTION:
                    description = reader.readString();
                    break;
                case FIELD_NAME_ROOMS:
                    reader.readStartArray();
                    while (reader.readBsonType() != BsonType.END_OF_DOCUMENT) {
                        rooms.add(this.codec.decode(reader, decoderContext));
                    }
                    reader.readEndArray();
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.readEndDocument();
        final Exhibition exhibition = new Exhibition(id, name, description);
        for (Room room : rooms) {
            exhibition.addRoom(room);
        }
        return exhibition;
    }

    @Override
    public void encode(BsonWriter writer, Exhibition value, EncoderContext encoderContext) {
        writer.writeStartDocument();
            writer.writeString(FIELD_NAME_ID, value.id);
            writer.writeString(FIELD_NAME_TEXT, value.name);
            writer.writeString(FIELD_NAME_DESCRIPTION, value.description);
            writer.writeName(FIELD_NAME_ROOMS);
            writer.writeStartArray();
            for (Room room : value.getRooms()) {
                this.codec.encode(writer, room, encoderContext);
            }
            writer.writeEndArray();
        writer.writeEndDocument();
    }

    @Override
    public Class<Exhibition> getEncoderClass() {
        return Exhibition.class;
    }
}
