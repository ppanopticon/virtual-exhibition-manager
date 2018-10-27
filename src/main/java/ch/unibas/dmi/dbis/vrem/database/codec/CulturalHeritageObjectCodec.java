package ch.unibas.dmi.dbis.vrem.database.codec;

import ch.unibas.dmi.dbis.vrem.model.objects.CulturalHeritageObject;

import org.bson.BsonReader;
import org.bson.BsonType;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.types.ObjectId;

public class CulturalHeritageObjectCodec implements Codec<CulturalHeritageObject> {


    private final String FIELD_NAME_OBJECTID = "_id";
    private final String FIELD_NAME_NAME = "name";
    private final String FIELD_NAME_DESCRIPTION = "description";
    private final String FIELD_NAME_TYPE = "type";
    private final String FIELD_NAME_PATH = "path";

    public CulturalHeritageObjectCodec(CodecRegistry registry) { }

    @Override
    public CulturalHeritageObject decode(BsonReader reader, DecoderContext decoderContext) {
        reader.readStartDocument();
        ObjectId id = null;
        String name = null;
        String description = null;
        CulturalHeritageObject.CHOType type = null;
        String path = null;

        while(reader.readBsonType() != BsonType.END_OF_DOCUMENT) {
            switch (reader.readName()) {
                case FIELD_NAME_OBJECTID:
                    id = reader.readObjectId();
                    break;
                case FIELD_NAME_NAME:
                    name = reader.readString();
                    break;
                case FIELD_NAME_DESCRIPTION:
                    description = reader.readString();
                    break;
                case FIELD_NAME_TYPE:
                    type = CulturalHeritageObject.CHOType.valueOf(reader.readString());
                    break;
                case FIELD_NAME_PATH:
                    path = reader.readString();
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.readEndDocument();
        return new CulturalHeritageObject(id, name, description, path, type);
    }

    @Override
    public void encode(BsonWriter writer, CulturalHeritageObject value, EncoderContext encoderContext) {
        writer.writeStartDocument();
        writer.writeObjectId(FIELD_NAME_OBJECTID, new ObjectId(value.id));
        writer.writeString(FIELD_NAME_NAME, value.name);
        writer.writeString(FIELD_NAME_DESCRIPTION, value.description);
        writer.writeString(FIELD_NAME_TYPE, value.type.name());
        writer.writeString(FIELD_NAME_PATH, value.path);
        writer.writeEndDocument();
    }

    @Override
    public Class<CulturalHeritageObject> getEncoderClass() {
        return CulturalHeritageObject.class;
    }
}
