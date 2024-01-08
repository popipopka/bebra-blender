package com.meow.bebrablender.render_engine;

import com.meow.bebrablender.math.vectors.Vector3f;
import com.meow.bebrablender.model.Polygon;
import com.meow.bebrablender.triangulation.TriangulationUtils;
import com.meow.bebrablender.normals.NormalUtils;

import java.util.List;

public class ModelNormalizer {
    /**
     * @param vertices all model vertices list.
     * @param polygons all model polygons list.
     * @param normals  all model normals list.
     */
    public static void triangulateAndRecalculateModelNormals(
            List<Vector3f> vertices,
            List<Polygon> polygons,
            List<Vector3f> normals) {
        TriangulationUtils.triangulateModelPolygons(polygons);
        NormalUtils.recalculateModelNormals(vertices, normals, polygons);
    }
}
