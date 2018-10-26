package ch.unibas.dmi.dbis.vrem.database.codec;

import ch.unibas.dmi.dbis.vrem.model.Vector3f;
import ch.unibas.dmi.dbis.vrem.model.exhibition.Wall;

import org.bson.BsonReader;
import org.bson.BsonType;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;

public class WallCodec implements Codec<Wall> {


    private final String FIELD_NAME_POSITION = "position";
    private final String FIELD_NAME_COLOR = "color";

    private final VectorCodec codec;


    public WallCodec(VectorCodec codec) {
        this.codec = codec;
    }


    @Override
    public Wall decode(BsonReader reader, DecoderContext decoderContext) {
        reader.readStartDocument();
        Vector3f position = null;
        Vector3f color = null;
        while(reader.readBsonType() != BsonType.END_OF_DOCUMENT) {
            switch (reader.readName()) {
                case FIELD_NAME_POSITION:
                    position = this.codec.decode(reader, decoderContext);
                    break;
                case FIELD_NAME_COLOR:
                    position = this.codec.decode(reader, decoderContext);
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.readEndDocument();
        return new Wall(position, color);
    }

    @Override
    public void encode(BsonWriter writer, Wall value, EncoderContext encoderContext) {
        writer.writeStartDocument();
        writer.writeName(FIELD_NAME_POSITION);
        this.codec.encode(writer, value.position, encoderContext);
        writer.writeName(FIELD_NAME_COLOR);
        this.codec.encode(writer, value.color, encoderContext);
        writer.writeEndDocument();
    }

    @Override
    public Class<Wall> getEncoderClass() {
        return Wall.class;
    }
}
