package com.meow.bebrablender.model;

import com.meow.bebrablender.math.vectors.Vector2f;
import com.meow.bebrablender.math.vectors.Vector3f;

import java.util.ArrayList;
import java.util.List;

/**
 * Updated and redependencied to math library by Alexander Laptev
 *
 * @author Pertsev Roman (Alexander Laptev, Ivan Kosenko)
 */
public class Model {
    private List<Vector3f> vertices = new ArrayList<>();
    private List<Vector2f> textureVertices = new ArrayList<>();
    private List<Vector3f> normals = new ArrayList<>();
    private List<Polygon> polygons = new ArrayList<>();

    public List<Vector3f> getVertices() {
        return vertices;
    }

    public List<Vector2f> getTextureVertices() {
        return textureVertices;
    }

    public List<Vector3f> getNormals() {
        return normals;
    }

    public List<Polygon> getPolygons() {
        return polygons;
    }
}
