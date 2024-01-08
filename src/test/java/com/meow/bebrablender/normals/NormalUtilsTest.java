package com.meow.bebrablender.normals;

import com.meow.bebrablender.math.vectors.Vector3f;
import com.meow.bebrablender.model.Model;
import com.meow.bebrablender.model.Polygon;
import com.meow.bebrablender.utils.NormalUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class NormalUtilsTest {
    private static Model constructBrickModel() {
        Model model = new Model();

        model.vertices = new ArrayList<>(List.of(new Vector3f[]{
                new Vector3f(0, 0, 0),
                new Vector3f(0, 2, 0),
                new Vector3f(1, 2, 0),
                new Vector3f(1, 0, 0),
                new Vector3f(0, 0, 1),
                new Vector3f(0, 2, 1),
                new Vector3f(1, 2, 1),
                new Vector3f(1, 0, 1)
        }));

        model.polygons = new ArrayList<>(List.of(
                new Polygon(), new Polygon(), new Polygon(),
                new Polygon(), new Polygon(), new Polygon()
        ));

        model.polygons.get(0).setVertexIndices(new ArrayList<>(List.of(new Integer[]{0, 1, 2, 3})));
        model.polygons.get(1).setVertexIndices(new ArrayList<>(List.of(new Integer[]{0, 4, 5, 1})));
        model.polygons.get(2).setVertexIndices(new ArrayList<>(List.of(new Integer[]{3, 7, 4, 0})));
        model.polygons.get(3).setVertexIndices(new ArrayList<>(List.of(new Integer[]{2, 6, 5, 1})));
        model.polygons.get(4).setVertexIndices(new ArrayList<>(List.of(new Integer[]{3, 7, 6, 2})));
        model.polygons.get(5).setVertexIndices(new ArrayList<>(List.of(new Integer[]{7, 4, 5, 6})));


        return model;
    }

    private static Model constructPyramidModel() {
        Model model = new Model();

        model.vertices = new ArrayList<>(List.of(new Vector3f[]{
                new Vector3f(0, 0, 0),
                new Vector3f(2, 0, 0),
                new Vector3f(1, 2, 0),
                new Vector3f(1, 1, 1)
        }));

        model.polygons = new ArrayList<>(List.of(
                new Polygon(), new Polygon(),
                new Polygon(), new Polygon()
        ));

        model.polygons.get(0).setVertexIndices(new ArrayList<>(List.of(new Integer[]{1, 0, 2})));
        model.polygons.get(1).setVertexIndices(new ArrayList<>(List.of(new Integer[]{0, 3, 1})));
        model.polygons.get(2).setVertexIndices(new ArrayList<>(List.of(new Integer[]{2, 3, 0})));
        model.polygons.get(3).setVertexIndices(new ArrayList<>(List.of(new Integer[]{1, 3, 2})));

        return model;
    }

    @Test
    public void normalToPolygon1() {
        Model model = constructBrickModel();

        Polygon randomPolygon = model.polygons.get(0);

        Vector3f result = NormalUtils.polygonNormal(randomPolygon, model.vertices);

        Vector3f vertex1 = model.vertices.get(randomPolygon.getVertexIndices().get(0));
        Vector3f vertex2 = model.vertices.get(randomPolygon.getVertexIndices().get(1));
        Vector3f vertex3 = model.vertices.get(randomPolygon.getVertexIndices().get(2));

        Vector3f randomPolygonVector1 = new Vector3f(vertex2.x() - vertex1.x(), vertex2.y() - vertex1.y(), vertex2.z() - vertex1.z());
        Vector3f randomPolygonVector2 = new Vector3f(vertex3.x() - vertex1.x(), vertex3.y() - vertex1.y(), vertex3.z() - vertex1.z());

        Assertions.assertTrue(randomPolygonVector1.isOrthogonal(result) && randomPolygonVector2.isOrthogonal(result));
    }

    @Test
    public void normalToPolygon2() {
        Model model = constructBrickModel();

        Polygon randomPolygon = model.polygons.get(1);

        Vector3f result = NormalUtils.polygonNormal(randomPolygon, model.vertices);

        Vector3f vertex1 = model.vertices.get(randomPolygon.getVertexIndices().get(0));
        Vector3f vertex2 = model.vertices.get(randomPolygon.getVertexIndices().get(1));
        Vector3f vertex3 = model.vertices.get(randomPolygon.getVertexIndices().get(2));

        Vector3f randomPolygonVector1 = new Vector3f(vertex2.x() - vertex1.x(), vertex2.y() - vertex1.y(), vertex2.z() - vertex1.z());
        Vector3f randomPolygonVector2 = new Vector3f(vertex3.x() - vertex1.x(), vertex3.y() - vertex1.y(), vertex3.z() - vertex1.z());

        Assertions.assertTrue(randomPolygonVector1.isOrthogonal(result) && randomPolygonVector2.isOrthogonal(result));
    }

    @Test
    public void normalToPolygon3() {
        Model model = constructBrickModel();

        Polygon randomPolygon = model.polygons.get(2);

        Vector3f result = NormalUtils.polygonNormal(randomPolygon, model.vertices);

        Vector3f vertex1 = model.vertices.get(randomPolygon.getVertexIndices().get(0));
        Vector3f vertex2 = model.vertices.get(randomPolygon.getVertexIndices().get(1));
        Vector3f vertex3 = model.vertices.get(randomPolygon.getVertexIndices().get(2));

        Vector3f randomPolygonVector1 = new Vector3f(vertex2.x() - vertex1.x(), vertex2.y() - vertex1.y(), vertex2.z() - vertex1.z());
        Vector3f randomPolygonVector2 = new Vector3f(vertex3.x() - vertex1.x(), vertex3.y() - vertex1.y(), vertex3.z() - vertex1.z());

        Assertions.assertTrue(randomPolygonVector1.isOrthogonal(result) && randomPolygonVector2.isOrthogonal(result));
    }

    @Test
    public void normalToPolygon4() {
        Model model = constructBrickModel();

        Polygon randomPolygon = model.polygons.get(3);

        Vector3f result = NormalUtils.polygonNormal(randomPolygon, model.vertices);

        Vector3f vertex1 = model.vertices.get(randomPolygon.getVertexIndices().get(0));
        Vector3f vertex2 = model.vertices.get(randomPolygon.getVertexIndices().get(1));
        Vector3f vertex3 = model.vertices.get(randomPolygon.getVertexIndices().get(2));

        Vector3f randomPolygonVector1 = new Vector3f(vertex2.x() - vertex1.x(), vertex2.y() - vertex1.y(), vertex2.z() - vertex1.z());
        Vector3f randomPolygonVector2 = new Vector3f(vertex3.x() - vertex1.x(), vertex3.y() - vertex1.y(), vertex3.z() - vertex1.z());

        Assertions.assertTrue(randomPolygonVector1.isOrthogonal(result) && randomPolygonVector2.isOrthogonal(result));
    }

    @Test
    public void normalToPolygon5() {
        Model model = constructBrickModel();

        Polygon randomPolygon = model.polygons.get(4);

        Vector3f result = NormalUtils.polygonNormal(randomPolygon, model.vertices);

        Vector3f vertex1 = model.vertices.get(randomPolygon.getVertexIndices().get(0));
        Vector3f vertex2 = model.vertices.get(randomPolygon.getVertexIndices().get(1));
        Vector3f vertex3 = model.vertices.get(randomPolygon.getVertexIndices().get(2));

        Vector3f randomPolygonVector1 = new Vector3f(vertex2.x() - vertex1.x(), vertex2.y() - vertex1.y(), vertex2.z() - vertex1.z());
        Vector3f randomPolygonVector2 = new Vector3f(vertex3.x() - vertex1.x(), vertex3.y() - vertex1.y(), vertex3.z() - vertex1.z());

        Assertions.assertTrue(randomPolygonVector1.isOrthogonal(result) && randomPolygonVector2.isOrthogonal(result));
    }

    @Test
    public void normalToPolygon6() {
        Model model = constructBrickModel();

        Polygon randomPolygon = model.polygons.get(5);

        Vector3f result = NormalUtils.polygonNormal(randomPolygon, model.vertices);

        Vector3f vertex1 = model.vertices.get(randomPolygon.getVertexIndices().get(0));
        Vector3f vertex2 = model.vertices.get(randomPolygon.getVertexIndices().get(1));
        Vector3f vertex3 = model.vertices.get(randomPolygon.getVertexIndices().get(2));

        Vector3f randomPolygonVector1 = new Vector3f(vertex2.x() - vertex1.x(), vertex2.y() - vertex1.y(), vertex2.z() - vertex1.z());
        Vector3f randomPolygonVector2 = new Vector3f(vertex3.x() - vertex1.x(), vertex3.y() - vertex1.y(), vertex3.z() - vertex1.z());

        Assertions.assertTrue(randomPolygonVector1.isOrthogonal(result) && randomPolygonVector2.isOrthogonal(result));
    }


    @Test
    public void normalToPolygon7() {
        Model model = constructPyramidModel();

        Polygon randomPolygon = model.polygons.get(0);

        Vector3f result = NormalUtils.polygonNormal(randomPolygon, model.vertices);

        Vector3f vertex1 = model.vertices.get(randomPolygon.getVertexIndices().get(0));
        Vector3f vertex2 = model.vertices.get(randomPolygon.getVertexIndices().get(1));
        Vector3f vertex3 = model.vertices.get(randomPolygon.getVertexIndices().get(2));

        Vector3f randomPolygonVector1 = new Vector3f(vertex2.x() - vertex1.x(), vertex2.y() - vertex1.y(), vertex2.z() - vertex1.z());
        Vector3f randomPolygonVector2 = new Vector3f(vertex3.x() - vertex1.x(), vertex3.y() - vertex1.y(), vertex3.z() - vertex1.z());

        Assertions.assertTrue(randomPolygonVector1.isOrthogonal(result) && randomPolygonVector2.isOrthogonal(result));
    }

    @Test
    public void normalToPolygon8() {
        Model model = constructPyramidModel();

        Polygon randomPolygon = model.polygons.get(1);

        Vector3f result = NormalUtils.polygonNormal(randomPolygon, model.vertices);

        Vector3f vertex1 = model.vertices.get(randomPolygon.getVertexIndices().get(0));
        Vector3f vertex2 = model.vertices.get(randomPolygon.getVertexIndices().get(1));
        Vector3f vertex3 = model.vertices.get(randomPolygon.getVertexIndices().get(2));

        Vector3f randomPolygonVector1 = new Vector3f(vertex2.x() - vertex1.x(), vertex2.y() - vertex1.y(), vertex2.z() - vertex1.z());
        Vector3f randomPolygonVector2 = new Vector3f(vertex3.x() - vertex1.x(), vertex3.y() - vertex1.y(), vertex3.z() - vertex1.z());

        Assertions.assertTrue(randomPolygonVector1.isOrthogonal(result) && randomPolygonVector2.isOrthogonal(result));
    }

    @Test
    public void normalToPolygon9() {
        Model model = constructPyramidModel();

        Polygon randomPolygon = model.polygons.get(2);

        Vector3f result = NormalUtils.polygonNormal(randomPolygon, model.vertices);
        Vector3f vertex1 = model.vertices.get(randomPolygon.getVertexIndices().get(0));
        Vector3f vertex2 = model.vertices.get(randomPolygon.getVertexIndices().get(1));
        Vector3f vertex3 = model.vertices.get(randomPolygon.getVertexIndices().get(2));

        Vector3f randomPolygonVector1 = new Vector3f(vertex2.x() - vertex1.x(), vertex2.y() - vertex1.y(), vertex2.z() - vertex1.z());
        Vector3f randomPolygonVector2 = new Vector3f(vertex3.x() - vertex1.x(), vertex3.y() - vertex1.y(), vertex3.z() - vertex1.z());

        Assertions.assertTrue(randomPolygonVector1.isOrthogonal(result) && randomPolygonVector2.isOrthogonal(result));
    }

    @Test
    public void normalToPolygon10() {
        Model model = constructPyramidModel();

        Polygon randomPolygon = model.polygons.get(3);

        Vector3f result = NormalUtils.polygonNormal(randomPolygon, model.vertices);

        Vector3f vertex1 = model.vertices.get(randomPolygon.getVertexIndices().get(0));
        Vector3f vertex2 = model.vertices.get(randomPolygon.getVertexIndices().get(1));
        Vector3f vertex3 = model.vertices.get(randomPolygon.getVertexIndices().get(2));

        Vector3f randomPolygonVector1 = new Vector3f(vertex2.x() - vertex1.x(), vertex2.y() - vertex1.y(), vertex2.z() - vertex1.z());
        Vector3f randomPolygonVector2 = new Vector3f(vertex3.x() - vertex1.x(), vertex3.y() - vertex1.y(), vertex3.z() - vertex1.z());

        Assertions.assertTrue(randomPolygonVector1.isOrthogonal(result) && randomPolygonVector2.isOrthogonal(result));
    }

    @Test
    public void selectPolygonsSurroundingVertex1() {
        Model model = constructPyramidModel();

        List<Polygon> result = NormalUtils.selectPolygonsSurroundingVertex(
                0, model.vertices, model.polygons
        );

        List<Polygon> expected = new ArrayList<>();
        expected.add(model.polygons.get(0));
        expected.add(model.polygons.get(1));
        expected.add(model.polygons.get(2));

        Assertions.assertEquals(expected, result);
    }

    @Test
    public void normalToVertex1() {
        Model model = constructBrickModel();

        Vector3f result = NormalUtils.vertexNormal(7, model.vertices, model.polygons);

        Vector3f expected = new Vector3f((float) -1 / 3, (float) -1 / 3, (float) -1 / 3).normalize();

        Assertions.assertEquals(expected.normalize(), result.normalize());
    }

    @Test
    public void normalToVertex2() {
        Model model = constructPyramidModel();

        Vector3f result = NormalUtils.vertexNormal(3, model.vertices, model.polygons);
        Vector3f expected = new Vector3f(
                0,
                (float) (1 / Math.sqrt(2)) - (float) (2 / Math.sqrt(6)),
                (float) -(1 / Math.sqrt(2)) - (float) (2 / Math.sqrt(6))
        ).divide(3).normalize();

        Assertions.assertEquals(expected.normalize(), result.normalize());
    }
}
