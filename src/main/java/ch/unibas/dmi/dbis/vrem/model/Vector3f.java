package ch.unibas.dmi.dbis.vrem.model;

import java.util.Objects;

public class Vector3f {
    /** */
    public static final Vector3f ORIGIN = new Vector3f(0,0,0);

    /** */
    public static final Vector3f UNIT = new Vector3f(1.0f,1.0f,1.0f);

    public final float x;

    public final float y;

    public final float z;

    /**
     *
     * @param x
     * @param y
     * @param z
     */
    public Vector3f(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector3f vector3f = (Vector3f) o;
        return Float.compare(vector3f.x, x) == 0 &&
                Float.compare(vector3f.y, y) == 0 &&
                Float.compare(vector3f.z, z) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }
}
