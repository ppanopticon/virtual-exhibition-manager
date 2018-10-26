package ch.unibas.dmi.dbis.vrem.database.codec;

import ch.unibas.dmi.dbis.vrem.model.Vector3f;
import org.bson.BsonReader;
import org.bson.BsonType;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;

public class VectorCodec implements Codec<Vector3f> {

    private final String FIELD_NAME_X = "x";
    private final String FIELD_NAME_Y = "y";
    private final String FIELD_NAME_Z = "z";

    @Override
    public Vector3f decode(BsonReader reader, DecoderContext decoderContext) {
        reader.readStartDocument();
        float x = 0.0f;
        float y = 0.0f;
        float z = 0.0f;
        while(reader.readBsonType() != BsonType.END_OF_DOCUMENT) {
            switch (reader.readName()) {
                case FIELD_NAME_X:
                    x = (float)reader.readDouble();
                    break;
                case FIELD_NAME_Y:
                    y = (float)reader.readDouble();
                    break;
                case FIELD_NAME_Z:
                    z = (float)reader.readDouble();
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.readEndDocument();
        return new Vector3f(x,y,z);
    }

    @Override
    public void encode(BsonWriter writer, Vector3f value, EncoderContext encoderContext) {
        writer.writeStartDocument();
        writer.writeDouble(FIELD_NAME_X, value.x);
        writer.writeDouble(FIELD_NAME_Y, value.y);
        writer.writeDouble(FIELD_NAME_Z, value.z);
        writer.writeEndDocument();

    }

    @Override
    public Class<Vector3f> getEncoderClass() {
        return Vector3f.class;
    }
}



