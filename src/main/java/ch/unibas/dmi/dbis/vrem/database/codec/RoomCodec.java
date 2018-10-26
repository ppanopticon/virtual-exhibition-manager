package ch.unibas.dmi.dbis.vrem.database.codec;

import ch.unibas.dmi.dbis.vrem.model.Vector3f;
import ch.unibas.dmi.dbis.vrem.model.exhibition.Direction;
import ch.unibas.dmi.dbis.vrem.model.exhibition.Room;
import ch.unibas.dmi.dbis.vrem.model.exhibition.Wall;
import org.bson.BsonReader;
import org.bson.BsonType;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.bson.codecs.configuration.CodecRegistry;

import java.util.HashMap;
import java.util.Map;

public class RoomCodec implements Codec<Room> {

    private final String FIELD_NAME_TEXT = "text";
    private final String FIELD_NAME_SIZE = "size";
    private final String FIELD_NAME_POSITION = "position";
    private final String FIELD_NAME_ENTRYPOINT = "entrypoint";
    private final String FIELD_NAME_WALLS = "walls";


    private final Codec<Wall> wallCodec;

    private final Codec<Vector3f> vectorCodec;

    /**
     *
     * @param registry
     */
    public RoomCodec(CodecRegistry registry) {
        this.wallCodec = registry.get(Wall.class);
        this.vectorCodec = registry.get(Vector3f.class);
    }

    /**
     *
     * @param reader
     * @param decoderContext
     * @return
     */
    @Override
    public Room decode(BsonReader reader, DecoderContext decoderContext) {
        reader.readStartDocument();
        String text = null;
        Vector3f size = null;
        Vector3f position = null;
        Vector3f entrypoint = null;
        Map<Direction,Wall> walls = new HashMap<>();


        while(reader.readBsonType() != BsonType.END_OF_DOCUMENT) {
            switch (reader.readName()) {
                case FIELD_NAME_TEXT:
                    text = reader.readString();
                    break;
                case FIELD_NAME_SIZE:
                    size = this.vectorCodec.decode(reader, decoderContext);
                    break;
                case FIELD_NAME_POSITION:
                    position = this.vectorCodec.decode(reader, decoderContext);
                    break;
                case FIELD_NAME_ENTRYPOINT:
                    entrypoint = this.vectorCodec.decode(reader, decoderContext);
                    break;
                case FIELD_NAME_WALLS:
                    reader.readStartDocument();
                    reader.readName(Direction.NORTH.name().toLowerCase());
                    walls.put(Direction.NORTH, this.wallCodec.decode(reader, decoderContext));
                    reader.readName(Direction.EAST.name().toLowerCase());
                    walls.put(Direction.EAST, this.wallCodec.decode(reader, decoderContext));
                    reader.readName(Direction.SOUTH.name().toLowerCase());
                    walls.put(Direction.SOUTH, this.wallCodec.decode(reader, decoderContext));
                    reader.readName(Direction.WEST.name().toLowerCase());
                    walls.put(Direction.WEST, this.wallCodec.decode(reader, decoderContext));
                    reader.readEndDocument();
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.readEndDocument();
        final Room room = new Room(text, size, position, entrypoint);
        for (Map.Entry<Direction,Wall> entry : walls.entrySet()) {
            room.placeWall(entry.getKey(), entry.getValue());
        }
        return room;
    }

    @Override
    public void encode(BsonWriter writer, Room value, EncoderContext encoderContext) {
        writer.writeStartDocument();
            writer.writeString(FIELD_NAME_TEXT, value.text);
            writer.writeName(FIELD_NAME_SIZE);
            this.vectorCodec.encode(writer, value.size, encoderContext);
            writer.writeName(FIELD_NAME_POSITION);
            this.vectorCodec.encode(writer, value.position, encoderContext);
            writer.writeName(FIELD_NAME_ENTRYPOINT);
            this.vectorCodec.encode(writer, value.entrypoint, encoderContext);

            writer.writeName(FIELD_NAME_WALLS);
            writer.writeStartDocument();
                writer.writeName(Direction.NORTH.name());
                this.wallCodec.encode(writer, value.getWalls().get(Direction.NORTH), encoderContext);
                writer.writeName(Direction.EAST.name());
                this.wallCodec.encode(writer, value.getWalls().get(Direction.EAST), encoderContext);
                writer.writeName(Direction.SOUTH.name());
                this.wallCodec.encode(writer, value.getWalls().get(Direction.SOUTH), encoderContext);
                writer.writeName(Direction.WEST.name());
                this.wallCodec.encode(writer, value.getWalls().get(Direction.WEST), encoderContext);
            writer.writeEndDocument();
        writer.writeEndDocument();
    }

    @Override
    public Class<Room> getEncoderClass() {
        return Room.class;
    }
}
