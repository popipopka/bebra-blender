package com.meow.bebrablender.model;

import com.meow.bebrablender.math.vectors.Vector2d;
import com.meow.bebrablender.math.vectors.Vector3d;

import java.util.ArrayList;
import java.util.List;

/**
 * Updated and redependencied to math library by Alexander Laptev
 *
 * @author Pertsev Roman (Alexander Laptev, Ivan Kosenko)
 */
public class Model {
    private List<Vector3d> vertices = new ArrayList<>();
    private List<Vector2d> textureVertices = new ArrayList<>();
    private List<Vector3d> normals = new ArrayList<>();
    private List<Polygon> polygons = new ArrayList<>();

    public List<Vector3d> getVertices() {
        return vertices;
    }

    public List<Vector2d> getTextureVertices() {
        return textureVertices;
    }

    public List<Vector3d> getNormals() {
        return normals;
    }

    public List<Polygon> getPolygons() {
        return polygons;
    }
}
