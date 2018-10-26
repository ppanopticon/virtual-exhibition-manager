package ch.unibas.dmi.dbis.vrem.database.codec;

import ch.unibas.dmi.dbis.vrem.model.Vector3f;
import ch.unibas.dmi.dbis.vrem.model.exhibition.Exhibit;
import ch.unibas.dmi.dbis.vrem.model.exhibition.Wall;

import org.bson.BsonReader;
import org.bson.BsonType;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.bson.codecs.configuration.CodecRegistry;

import java.util.ArrayList;
import java.util.List;

public class WallCodec implements Codec<Wall> {


    private final String FIELD_NAME_POSITION = "position";
    private final String FIELD_NAME_COLOR = "color";
    private final String FIELD_NAME_EXHIBITS = "exhibits";

    private final Codec<Vector3f> vectorCodec;

    private final Codec<Exhibit> exhibitCodec;


    public WallCodec(CodecRegistry registry) {
        this.vectorCodec = registry.get(Vector3f.class);
        this.exhibitCodec = registry.get(Exhibit.class);
    }


    @Override
    public Wall decode(BsonReader reader, DecoderContext decoderContext) {
        reader.readStartDocument();
        Vector3f position = null;
        Vector3f color = null;
        List<Exhibit> exhibits = new ArrayList<>();
        while(reader.readBsonType() != BsonType.END_OF_DOCUMENT) {
            switch (reader.readName()) {
                case FIELD_NAME_POSITION:
                    position = this.vectorCodec.decode(reader, decoderContext);
                    break;
                case FIELD_NAME_COLOR:
                    color = this.vectorCodec.decode(reader, decoderContext);
                    break;
                case FIELD_NAME_EXHIBITS:
                    reader.readStartArray();
                    while (reader.readBsonType() != BsonType.END_OF_DOCUMENT) {
                        exhibits.add(this.exhibitCodec.decode(reader, decoderContext));
                    }
                    reader.readEndArray();
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.readEndDocument();

        /* Make final assembly. */
        final Wall wall = new Wall(position, color);
        for (Exhibit exhibit : exhibits) {
            wall.placeExhibit(exhibit);
        }
        return wall;
    }

    @Override
    public void encode(BsonWriter writer, Wall value, EncoderContext encoderContext) {
        writer.writeStartDocument();
            writer.writeName(FIELD_NAME_POSITION);
            this.vectorCodec.encode(writer, value.position, encoderContext);
            writer.writeName(FIELD_NAME_COLOR);
            this.vectorCodec.encode(writer, value.color, encoderContext);
            writer.writeName(FIELD_NAME_EXHIBITS);
            writer.writeStartArray();
                for (Exhibit exhibit : value.getExhibits()) {
                    this.exhibitCodec.encode(writer, exhibit, encoderContext);
                }
            writer.writeEndArray();
        writer.writeEndDocument();
    }

    @Override
    public Class<Wall> getEncoderClass() {
        return Wall.class;
    }
}
