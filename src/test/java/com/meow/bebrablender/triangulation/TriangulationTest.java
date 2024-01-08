package com.meow.bebrablender.triangulation;

import com.meow.bebrablender.model.Polygon;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class TriangulationTest {
    @Test
    public void testTriangulate() {
        Polygon polygon = new Polygon(Arrays.asList(1, 2, 3, 4), Arrays.asList(1, 2, 3, 4), Arrays.asList(1, 2, 3, 4));

        List<Polygon> triangles = Triangulation.triangulate(polygon);

        assertEquals(2, triangles.size());
        assertArrayEquals(new Integer[] {1, 2, 3}, triangles.get(0).getVertexIndices().toArray());
        assertArrayEquals(new Integer[] {1, 3, 4}, triangles.get(1).getVertexIndices().toArray());
        assertArrayEquals(new Integer[] {1, 2, 3}, triangles.get(0).getNormalIndices().toArray());
        assertArrayEquals(new Integer[] {1, 3, 4}, triangles.get(1).getNormalIndices().toArray());
        assertArrayEquals(new Integer[] {1, 2, 3}, triangles.get(0).getTextureVertexIndices().toArray());
        assertArrayEquals(new Integer[] {1, 3, 4}, triangles.get(1).getTextureVertexIndices().toArray());
        assertTrue(triangles.get(0).isTriangle());
        assertTrue(triangles.get(1).isTriangle());
    }

    @Test
    public void testTriangulateList() {
        Polygon polygon1 = new Polygon(Arrays.asList(1, 2, 3), Arrays.asList(1, 2, 3), Arrays.asList(1, 2, 3));
        Polygon polygon2 = new Polygon(Arrays.asList(4, 5, 6), Arrays.asList(4, 5, 6), Arrays.asList(4, 5, 6));

        List<Polygon> polygons = Arrays.asList(polygon1, polygon2);
        List<Polygon> triangles = Triangulation.triangulateList(polygons);

        assertEquals(2, triangles.size());
        assertTrue(triangles.get(0).isTriangle());
        assertTrue(triangles.get(1).isTriangle());
    }
}

