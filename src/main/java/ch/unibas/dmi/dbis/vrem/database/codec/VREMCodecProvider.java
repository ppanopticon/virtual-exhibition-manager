package ch.unibas.dmi.dbis.vrem.database.codec;

import ch.unibas.dmi.dbis.vrem.model.Vector3f;
import ch.unibas.dmi.dbis.vrem.model.exhibition.Exhibit;
import ch.unibas.dmi.dbis.vrem.model.exhibition.Exhibition;
import ch.unibas.dmi.dbis.vrem.model.exhibition.Room;
import ch.unibas.dmi.dbis.vrem.model.exhibition.Wall;
import ch.unibas.dmi.dbis.vrem.model.objects.CulturalHeritageObject;
import org.bson.codecs.Codec;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;

public class VREMCodecProvider implements CodecProvider {
    @Override
    public <T> Codec<T> get(Class<T> clazz, CodecRegistry registry) {
        if (clazz == Exhibition.class) {
            return (Codec<T>)new ExhibitionCodec(registry);
        } else if (clazz == Room.class) {
            return (Codec<T>)new RoomCodec(registry);
        } else if (clazz == Wall.class) {
            return (Codec<T>)new WallCodec(registry);
        } else if (clazz == Exhibit.class) {
            return (Codec<T>)new ExhibitCodec(registry);
        } else if (clazz == CulturalHeritageObject.class) {
            return (Codec<T>)new CulturalHeritageObjectCodec(registry);
        } else if (clazz == Vector3f.class) {
            return (Codec<T>)new VectorCodec(registry);
        }
        return null;
    }
}
