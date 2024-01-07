package com.meow.bebrablender.math.vectors;

public class VectorsUtils {
    private VectorsUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static Vector4f v3fToV4f(Vector3f v3f) {
        return new Vector4f(v3f.x(), v3f.y(), v3f.z(), 1);
    }

    public static Vector3f v4fToV3f(Vector4f v4f) {
        Vector4f copy = new Vector4f(v4f.coords);
        copy.normalize();

        return new Vector3f(v4f.x(), v4f.y(), v4f.z());
    }
}
