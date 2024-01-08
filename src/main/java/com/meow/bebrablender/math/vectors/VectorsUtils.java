package com.meow.bebrablender.math.vectors;

import com.meow.bebrablender.math.vectors.Vector3f;
import com.meow.bebrablender.math.vectors.Vector4f;

public class VectorsUtils {
    private VectorsUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static Vector4f v3fToV4f(Vector3f v3f) {
        return new Vector4f(v3f.x(), v3f.y(), v3f.z(), 1);
    }

    public static Vector3f v4fToV3f(Vector4f v4f) {
        Vector4f copy = new Vector4f(v4f.getCoords());
        copy.normalize();

        return new Vector3f(v4f.x(), v4f.y(), v4f.z());
    }

    public static Vector2f add(Vector2f v1, Vector2f v2) {
        return new Vector2f(v1.x() + v2.x(), v1.y() + v2.y());
    }

    public static Vector2f subtract(Vector2f v1, Vector2f v2) {
        return new Vector2f(v1.x() - v2.x(), v1.y() - v2.y());
    }

    public static Vector2f multiply(Vector2f v1, Vector2f v2) {
        return new Vector2f(v1.x() * v2.x(), v1.y() * v2.y());
    }

    public static Vector2f divide(Vector2f v1, Vector2f v2) {
        return new Vector2f(v1.x() / v2.x(), v1.y() / v2.y());
    }

    public static Vector3f add(Vector3f v1, Vector3f v2) {
        return new Vector3f(v1.x() + v2.x(), v1.y() + v2.y(), v1.z() + v2.z());
    }

    public static Vector3f subtract(Vector3f v1, Vector3f v2) {
        return new Vector3f(v1.x() - v2.x(), v1.y() - v2.y(), v1.z() - v2.z());
    }

    public static Vector3f multiply(Vector3f v1, Vector3f v2) {
        return new Vector3f(v1.x() * v2.x(), v1.y() * v2.y(), v1.z() * v2.z());
    }

    public static Vector3f divide(Vector3f v1, Vector3f v2) {
        return new Vector3f(v1.x() / v2.x(), v1.y() / v2.y(), v1.z() / v2.z());
    }

    public static Vector4f add(Vector4f v1, Vector4f v2) {
        return new Vector4f(v1.x() + v2.x(), v1.y() + v2.y(), v1.z() + v2.z(), v1.w() + v2.w());
    }

    public static Vector4f subtract(Vector4f v1, Vector4f v2) {
        return new Vector4f(v1.x() - v2.x(), v1.y() - v2.y(), v1.z() - v2.z(), v1.w() - v2.w());
    }

    public static Vector4f multiply(Vector4f v1, Vector4f v2) {
        return new Vector4f(v1.x() * v2.x(), v1.y() * v2.y(), v1.z() * v2.z(), v1.w() * v2.w());
    }

    public static Vector4f divide(Vector4f v1, Vector4f v2) {
        return new Vector4f(v1.x() / v2.x(), v1.y() / v2.y(), v1.z() / v2.z(), v1.w() / v2.w());
    }

    public static Vector2f scalarProduct(Vector2f v1, Vector2f v2) {
        return new Vector2f(v1.x() * v2.x(), v1.y() * v2.y());
    }


    public static Vector3f scalarProduct(Vector3f v1, Vector3f v2) {
        return new Vector3f(v1.x() * v2.x(), v1.y() * v2.y(), v1.z() * v2.z());
    }


    public static Vector4f scalarProduct(Vector4f v1, Vector4f v2) {
        return new Vector4f(v1.x() * v2.x(), v1.y() * v2.y(), v1.z() * v2.z(), v1.w() * v2.w());
    }

    public static Vector3f crossProduct(Vector3f v1, Vector3f v2) {
        return new Vector3f(
                v1.y() * v2.z() - v1.z() * v2.y(),
                v1.z() * v2.x() - v1.x() * v2.z(),
                v1.x() * v2.y() - v1.y() * v2.x()
        );
    }
}
