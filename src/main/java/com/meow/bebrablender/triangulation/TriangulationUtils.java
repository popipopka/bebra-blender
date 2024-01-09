package com.meow.bebrablender.triangulation;

import com.meow.bebrablender.model.Polygon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TriangulationUtils {
    public static List<Polygon> triangulate(Polygon polygon) {
        List<Polygon> triangulatedPolygons = new ArrayList<>();

        if (polygon.isTriangle()) {
            triangulatedPolygons.add(polygon);
            return triangulatedPolygons;
        }

        for (int verListInd = 2; verListInd < polygon.numberOfVertices(); verListInd++) {
            Polygon triangulatedPolygon = new Polygon();

            triangulatedPolygon.setVertexIndices(
                    Arrays.asList(
                            polygon.getVertexIndices().get(0),
                            polygon.getVertexIndices().get(verListInd - 1),
                            polygon.getVertexIndices().get(verListInd)
                    )
            );

            if (polygon.getNormalIndices().size() >= 3)
                triangulatedPolygon.setNormalIndices(
                        Arrays.asList(polygon.getNormalIndices().get(0),
                                polygon.getNormalIndices().get(verListInd - 1),
                                polygon.getNormalIndices().get(verListInd))
                );

            if (polygon.getTextureVertexIndices().size() >= 3)
                triangulatedPolygon.setTextureVertexIndices(
                        Arrays.asList(polygon.getTextureVertexIndices().get(0),
                                polygon.getTextureVertexIndices().get(verListInd - 1),
                                polygon.getTextureVertexIndices().get(verListInd))
                );

            triangulatedPolygons.add(triangulatedPolygon);
        }
        return triangulatedPolygons;
    }

    public static void triangulateList(List<Polygon> polygons) {
        for (int initialPolygonIndex = 0; initialPolygonIndex < polygons.size(); initialPolygonIndex++) {
            List<Polygon> triangulatedPolygons = triangulate(polygons.get(initialPolygonIndex));

            polygons.set(initialPolygonIndex, triangulatedPolygons.get(0));

            if (triangulatedPolygons.size() != 1) {
                polygons.addAll(
                        initialPolygonIndex + 1,
                        triangulatedPolygons.subList(0, triangulatedPolygons.size())
                );
            }
        }
    }

    public static void triangulateModelPolygons(List<Polygon> polygons) {
        for (int initialPolygonIndex = 0; initialPolygonIndex < polygons.size(); initialPolygonIndex++) {
            List<Polygon> triangulatedPolygons = triangulate(polygons.get(initialPolygonIndex));

            polygons.set(initialPolygonIndex, triangulatedPolygons.get(0));

            if (triangulatedPolygons.size() != 1) {
                polygons.addAll(
                        initialPolygonIndex + 1,
                        triangulatedPolygons.subList(1, triangulatedPolygons.size())
                );
            }
        }
    }
}
