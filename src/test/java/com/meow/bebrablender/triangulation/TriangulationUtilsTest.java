package com.meow.bebrablender.triangulation;

import com.meow.bebrablender.model.Polygon;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class TriangulationUtilsTest {
    @Test
    public void testTriangulate() {
        Polygon polygon = new Polygon();

        polygon.setVertexIndices(Arrays.asList(1, 2, 3, 4));
        polygon.setNormalIndices(Arrays.asList(1, 2, 3, 4));
        polygon.setTextureVertexIndices(Arrays.asList(1, 2, 3, 4));

        List<Polygon> triangulatedPolygons = TriangulationUtils.triangulate(polygon);

        assertEquals(2, triangulatedPolygons.size());
        assertArrayEquals(new Integer[] {1, 2, 3}, triangulatedPolygons.get(0).getVertexIndices().toArray());
        assertArrayEquals(new Integer[] {1, 3, 4}, triangulatedPolygons.get(1).getVertexIndices().toArray());
        assertArrayEquals(new Integer[] {1, 2, 3}, triangulatedPolygons.get(0).getNormalIndices().toArray());
        assertArrayEquals(new Integer[] {1, 3, 4}, triangulatedPolygons.get(1).getNormalIndices().toArray());
        assertArrayEquals(new Integer[] {1, 2, 3}, triangulatedPolygons.get(0).getTextureVertexIndices().toArray());
        assertArrayEquals(new Integer[] {1, 3, 4}, triangulatedPolygons.get(1).getTextureVertexIndices().toArray());
        assertTrue(triangulatedPolygons.get(0).isTriangle());
        assertTrue(triangulatedPolygons.get(1).isTriangle());
    }

    @Test
    public void testTriangulateList() {
        Polygon polygon1 = new Polygon();
        Polygon polygon2 = new Polygon();

        polygon1.setVertexIndices(Arrays.asList(1, 2, 3));
        polygon2.setVertexIndices(Arrays.asList(4, 5, 6));

        polygon1.setNormalIndices(Arrays.asList(1, 2, 3));
        polygon2.setNormalIndices(Arrays.asList(4, 5, 6));

        polygon1.setTextureVertexIndices(Arrays.asList(1, 2, 3));
        polygon2.setTextureVertexIndices(Arrays.asList(4, 5, 6));

        List<Polygon> polygons = Arrays.asList(polygon1, polygon2);
        TriangulationUtils.triangulateList(polygons);

        assertEquals(2, polygons.size());
        assertTrue(polygons.get(0).isTriangle());
        assertTrue(polygons.get(1).isTriangle());
    }
}

