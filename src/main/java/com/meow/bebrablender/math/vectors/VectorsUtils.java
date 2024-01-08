package com.meow.bebrablender.math.vectors;

public class VectorsUtils {
    private VectorsUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static Vector4d v3fToV4f(Vector3d v3f) {
        return new Vector4d(v3f.x(), v3f.y(), v3f.z(), 1);
    }

    public static Vector3d v4fToV3f(Vector4d v4f) {
        Vector4d copy = new Vector4d(v4f.coords());
        copy.norm();

        return new Vector3d(v4f.x(), v4f.y(), v4f.z());
    }
}
