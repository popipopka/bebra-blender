package com.meow.bebrablender.normals;


import com.meow.bebrablender.math.vectors.Vector3f;
import com.meow.bebrablender.model.Polygon;

import java.util.ArrayList;
import java.util.List;

/**
 * Library for calculating normals to a 3D model
 *
 * @author Pertsev Roman
 */
public class NormalUtils {
    /**
     * @param vertices all model vertices list
     * @param normals all model normals list
     * @param polygons all model polygons list
     */
    public static void recalculateModelNormals(List<Vector3f> vertices, List<Vector3f> normals,
                                               List<Polygon> polygons) {
        for (int i = 0; i < vertices.size(); i++) {
            normals.set(i, vertexNormal(i, vertices, polygons));
        }
    }

    /**
     * @param polygon  input polygon
     * @param vertices all model vertices list
     * @return normal vector to input polygon
     */
    public static Vector3f polygonNormal(Polygon polygon, List<Vector3f> vertices) {
        List<Integer> vertexIndices = polygon.getVertexIndices();

        if (vertexIndices.size() < 3)
            throw new IllegalArgumentException("Polygon vertex count must be greater than or equal to 3");
        if (vertices.size() < 3)
            throw new IllegalArgumentException("Vertices count must be greater than or equal to 3");

        Vector3f vertex1 = vertices.get(vertexIndices.get(0));
        Vector3f vertex2 = vertices.get(vertexIndices.get(1));
        Vector3f vertex3 = vertices.get(vertexIndices.get(2));

        //vectors in the polygon flat
        Vector3f vector1 = new Vector3f(vertex2.x()- vertex1.x(), vertex2.y() - vertex1.y(), vertex2.z() - vertex1.z()).normalize();
        Vector3f vector2 = new Vector3f(vertex3.x() - vertex1.x(), vertex3.y() - vertex1.y(), vertex3.z() - vertex1.z()).normalize();

        return vector1.product(vector2).normalize();
    }

    /**
     * @param vertexIndex vertex index
     * @param vertices    all model vertices list
     * @param polygons    all model polygons list
     * @return normal vector to vertex relative to model
     */
    public static Vector3f vertexNormal(Integer vertexIndex, List<Vector3f> vertices, List<Polygon> polygons) {
        // Вариант, куда передают все вершины модели
        List<Polygon> polygonsSurroundingVertex = selectPolygonsSurroundingVertex(
                vertexIndex, vertices, polygons
        );

        Vector3f sumVector = new Vector3f(0, 0, 0);
        for (Polygon polygon : polygonsSurroundingVertex) {
            sumVector.add(polygonNormal(polygon, vertices).normalize());
        }

        // return average vector
        return sumVector.divide(polygonsSurroundingVertex.size()).normalize();
    }

    /**
     * Support method for "normalToVertex"
     *
     * @param vertexIndex vertex index
     * @param vertices    all model vertices list
     * @param polygons    all model polygons list
     * @return polygons which bordering input vertex
     */
    public static List<Polygon> selectPolygonsSurroundingVertex(Integer vertexIndex, List<Vector3f> vertices,
                                                                List<Polygon> polygons) {
        if (vertices.isEmpty())
            throw new IllegalArgumentException("Vertex array must be not empty");
        if (polygons.isEmpty())
            throw new IllegalArgumentException("Polygon array must be not empty");

        List<Polygon> polygonsSurroundingVertex = new ArrayList<>();

        for (Polygon polygon : polygons) {
            if (polygon.getVertexIndices().contains(vertexIndex)) {
                polygonsSurroundingVertex.add(polygon);
            }

        }

        return polygonsSurroundingVertex;
    }
}

