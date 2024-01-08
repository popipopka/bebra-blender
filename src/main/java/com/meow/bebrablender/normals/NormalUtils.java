package com.meow.bebrablender.normals;

import com.meow.bebrablender.math.vectors.Vector3d;
import com.meow.bebrablender.math.vectors.Vector3d;
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
     * @param normals  all model normals list
     * @param polygons all model polygons list
     */
    public static void recalculateModelNormals(List<Vector3d> vertices, List<Vector3d> normals,
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
    public static Vector3d polygonNormal(Polygon polygon, List<Vector3d> vertices) {
        List<Integer> vertexIndices = polygon.getVertexIndices();

        if (vertexIndices.size() < 3)
            throw new IllegalArgumentException("Polygon vertex count must be greater than or equal to 3");
        if (vertices.size() < 3)
            throw new IllegalArgumentException("Vertices count must be greater than or equal to 3");

        Vector3d vertex1 = vertices.get(vertexIndices.get(0));
        Vector3d vertex2 = vertices.get(vertexIndices.get(1));
        Vector3d vertex3 = vertices.get(vertexIndices.get(2));

        //vectors in the polygon flat
        Vector3d vector1 = new Vector3d(vertex2.x() - vertex1.x(), vertex2.y() - vertex1.y(), vertex2.z() - vertex1.z()).norm();
        Vector3d vector2 = new Vector3d(vertex3.x() - vertex1.x(), vertex3.y() - vertex1.y(), vertex3.z() - vertex1.z()).norm();

        return vector1.crossProd(vector2).norm();
    }

    /**
     * @param vertexIndex vertex index
     * @param vertices    all model vertices list
     * @param polygons    all model polygons list
     * @return normal vector to vertex relative to model
     */
    public static Vector3d vertexNormal(Integer vertexIndex, List<Vector3d> vertices, List<Polygon> polygons) {
        // Вариант, куда передают все вершины модели
        List<Polygon> polygonsSurroundingVertex = selectPolygonsSurroundingVertex(
                vertexIndex, vertices, polygons
        );

        Vector3d sumVector = new Vector3d(0, 0, 0);
        for (Polygon polygon : polygonsSurroundingVertex) {
            sumVector.add(polygonNormal(polygon, vertices).norm());
        }

        // return average vector
        return sumVector.div(polygonsSurroundingVertex.size()).norm();
    }

    /**
     * Support method for "normalToVertex"
     *
     * @param vertexIndex vertex index
     * @param vertices    all model vertices list
     * @param polygons    all model polygons list
     * @return polygons which bordering input vertex
     */
    public static List<Polygon> selectPolygonsSurroundingVertex(Integer vertexIndex, List<Vector3d> vertices,
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

