package com.meow.bebrablender.normals;

import com.meow.bebrablender.math.vectors.Vector3d;
import com.meow.bebrablender.model.Model;
import com.meow.bebrablender.model.Polygon;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class NormalUtilsTest {
    private static Model constructBrickModel() throws NoSuchFieldException, IllegalAccessException {
        Model model = new Model();

        Field verticiesField = Model.class.getDeclaredField("vertices");
        verticiesField.setAccessible(true);
        verticiesField.set(model, new ArrayList<>(List.of(new Vector3d[]{
                new Vector3d(0, 0, 0),
                new Vector3d(0, 2, 0),
                new Vector3d(1, 2, 0),
                new Vector3d(1, 0, 0),
                new Vector3d(0, 0, 1),
                new Vector3d(0, 2, 1),
                new Vector3d(1, 2, 1),
                new Vector3d(1, 0, 1)
        })));

        Field polygonsField = Model.class.getDeclaredField("polygons");
        polygonsField.setAccessible(true);
        polygonsField.set(model, new ArrayList<>(List.of(
                new Polygon(), new Polygon(), new Polygon(),
                new Polygon(), new Polygon(), new Polygon()
        )));

        List<Polygon> polygons = new ArrayList<>(List.of(
                new Polygon(), new Polygon(), new Polygon(),
                new Polygon(), new Polygon(), new Polygon()
        ));
        polygons.get(0).setVertexIndices(new ArrayList<>(List.of(new Integer[]{0, 1, 2, 3})));
        polygons.get(1).setVertexIndices(new ArrayList<>(List.of(new Integer[]{0, 4, 5, 1})));
        polygons.get(2).setVertexIndices(new ArrayList<>(List.of(new Integer[]{3, 7, 4, 0})));
        polygons.get(3).setVertexIndices(new ArrayList<>(List.of(new Integer[]{2, 6, 5, 1})));
        polygons.get(4).setVertexIndices(new ArrayList<>(List.of(new Integer[]{3, 7, 6, 2})));
        polygons.get(5).setVertexIndices(new ArrayList<>(List.of(new Integer[]{7, 4, 5, 6})));

        polygonsField.set(model,
                polygons
        );

        return model;
    }

    private static Model constructPyramidModel() throws NoSuchFieldException, IllegalAccessException {
        Model model = new Model();

        Field verticiesField = Model.class.getDeclaredField("vertices");
        verticiesField.setAccessible(true);
        verticiesField.set(model, new ArrayList<>(List.of(new Vector3d[]{
                new Vector3d(0, 0, 0),
                new Vector3d(2, 0, 0),
                new Vector3d(1, 2, 0),
                new Vector3d(1, 1, 1)
        })));

        List<Polygon> polygons = new ArrayList<>(List.of(
                new Polygon(), new Polygon(),
                new Polygon(), new Polygon()
        ));
        polygons.get(0).setVertexIndices(new ArrayList<>(List.of(new Integer[]{1, 0, 2})));
        polygons.get(1).setVertexIndices(new ArrayList<>(List.of(new Integer[]{0, 3, 1})));
        polygons.get(2).setVertexIndices(new ArrayList<>(List.of(new Integer[]{2, 3, 0})));
        polygons.get(3).setVertexIndices(new ArrayList<>(List.of(new Integer[]{1, 3, 2})));

        Field polygonsField = Model.class.getDeclaredField("polygons");
        polygonsField.setAccessible(true);
        polygonsField.set(model,
                polygons
        );

        return model;
    }

    @Test
    public void normalToPolygon1() throws NoSuchFieldException, IllegalAccessException {
        Model model = constructBrickModel();

        Field verticesField = Model.class.getDeclaredField("vertices");
        verticesField.setAccessible(true);
        
        Field polygonsField = Model.class.getDeclaredField("polygons");
        polygonsField.setAccessible(true);

        List<Vector3d> vertices = (List<Vector3d>) verticesField.get(model);
        List<Polygon> polygons = (List<Polygon>) polygonsField.get(model);

        Polygon randomPolygon = polygons.get(0);

        Vector3d result = NormalUtils.polygonNormal(randomPolygon, vertices);

        Vector3d vertex1 = vertices.get(randomPolygon.getVertexIndices().get(0));
        Vector3d vertex2 = vertices.get(randomPolygon.getVertexIndices().get(1));
        Vector3d vertex3 = vertices.get(randomPolygon.getVertexIndices().get(2));

        Vector3d randomPolygonVector1 = new Vector3d(vertex2.x() - vertex1.x(), vertex2.y() - vertex1.y(), vertex2.z() - vertex1.z());
        Vector3d randomPolygonVector2 = new Vector3d(vertex3.x() - vertex1.x(), vertex3.y() - vertex1.y(), vertex3.z() - vertex1.z());

        Assertions.assertTrue(randomPolygonVector1.isOrt(result) && randomPolygonVector2.isOrt(result));
    }

    @Test
    public void normalToPolygon2() throws NoSuchFieldException, IllegalAccessException {
        Model model = constructBrickModel();

        Field verticesField = Model.class.getDeclaredField("vertices");
        verticesField.setAccessible(true);

        Field polygonsField = Model.class.getDeclaredField("polygons");
        polygonsField.setAccessible(true);

        List<Vector3d> vertices = (List<Vector3d>) verticesField.get(model);
        List<Polygon> polygons = (List<Polygon>) polygonsField.get(model);
        
        Polygon randomPolygon = polygons.get(1);

        Vector3d result = NormalUtils.polygonNormal(randomPolygon, vertices);

        Vector3d vertex1 = vertices.get(randomPolygon.getVertexIndices().get(0));
        Vector3d vertex2 = vertices.get(randomPolygon.getVertexIndices().get(1));
        Vector3d vertex3 = vertices.get(randomPolygon.getVertexIndices().get(2));

        Vector3d randomPolygonVector1 = new Vector3d(vertex2.x() - vertex1.x(), vertex2.y() - vertex1.y(), vertex2.z() - vertex1.z());
        Vector3d randomPolygonVector2 = new Vector3d(vertex3.x() - vertex1.x(), vertex3.y() - vertex1.y(), vertex3.z() - vertex1.z());

        Assertions.assertTrue(randomPolygonVector1.isOrt(result) && randomPolygonVector2.isOrt(result));
    }

    @Test
    public void normalToPolygon3() throws NoSuchFieldException, IllegalAccessException {
        Model model = constructBrickModel();

        Field verticesField = Model.class.getDeclaredField("vertices");
        verticesField.setAccessible(true);

        Field polygonsField = Model.class.getDeclaredField("polygons");
        polygonsField.setAccessible(true);

        List<Vector3d> vertices = (List<Vector3d>) verticesField.get(model);
        List<Polygon> polygons = (List<Polygon>) polygonsField.get(model);

        Polygon randomPolygon = polygons.get(2);

        Vector3d result = NormalUtils.polygonNormal(randomPolygon, vertices);

        Vector3d vertex1 = vertices.get(randomPolygon.getVertexIndices().get(0));
        Vector3d vertex2 = vertices.get(randomPolygon.getVertexIndices().get(1));
        Vector3d vertex3 = vertices.get(randomPolygon.getVertexIndices().get(2));

        Vector3d randomPolygonVector1 = new Vector3d(vertex2.x() - vertex1.x(), vertex2.y() - vertex1.y(), vertex2.z() - vertex1.z());
        Vector3d randomPolygonVector2 = new Vector3d(vertex3.x() - vertex1.x(), vertex3.y() - vertex1.y(), vertex3.z() - vertex1.z());

        Assertions.assertTrue(randomPolygonVector1.isOrt(result) && randomPolygonVector2.isOrt(result));
    }

    @Test
    public void normalToPolygon4() throws NoSuchFieldException, IllegalAccessException {
        Model model = constructBrickModel();

        Field verticesField = Model.class.getDeclaredField("vertices");
        verticesField.setAccessible(true);

        Field polygonsField = Model.class.getDeclaredField("polygons");
        polygonsField.setAccessible(true);

        List<Vector3d> vertices = (List<Vector3d>) verticesField.get(model);
        List<Polygon> polygons = (List<Polygon>) polygonsField.get(model);
        
        Polygon randomPolygon = polygons.get(3);

        Vector3d result = NormalUtils.polygonNormal(randomPolygon, vertices);

        Vector3d vertex1 = vertices.get(randomPolygon.getVertexIndices().get(0));
        Vector3d vertex2 = vertices.get(randomPolygon.getVertexIndices().get(1));
        Vector3d vertex3 = vertices.get(randomPolygon.getVertexIndices().get(2));

        Vector3d randomPolygonVector1 = new Vector3d(vertex2.x() - vertex1.x(), vertex2.y() - vertex1.y(), vertex2.z() - vertex1.z());
        Vector3d randomPolygonVector2 = new Vector3d(vertex3.x() - vertex1.x(), vertex3.y() - vertex1.y(), vertex3.z() - vertex1.z());

        Assertions.assertTrue(randomPolygonVector1.isOrt(result) && randomPolygonVector2.isOrt(result));
    }

    @Test
    public void normalToPolygon5() throws NoSuchFieldException, IllegalAccessException {
        Model model = constructBrickModel();

        Field verticesField = Model.class.getDeclaredField("vertices");
        verticesField.setAccessible(true);

        Field polygonsField = Model.class.getDeclaredField("polygons");
        polygonsField.setAccessible(true);

        List<Vector3d> vertices = (List<Vector3d>) verticesField.get(model);
        List<Polygon> polygons = (List<Polygon>) polygonsField.get(model);
        
        Polygon randomPolygon = polygons.get(4);

        Vector3d result = NormalUtils.polygonNormal(randomPolygon, vertices);

        Vector3d vertex1 = vertices.get(randomPolygon.getVertexIndices().get(0));
        Vector3d vertex2 = vertices.get(randomPolygon.getVertexIndices().get(1));
        Vector3d vertex3 = vertices.get(randomPolygon.getVertexIndices().get(2));

        Vector3d randomPolygonVector1 = new Vector3d(vertex2.x() - vertex1.x(), vertex2.y() - vertex1.y(), vertex2.z() - vertex1.z());
        Vector3d randomPolygonVector2 = new Vector3d(vertex3.x() - vertex1.x(), vertex3.y() - vertex1.y(), vertex3.z() - vertex1.z());

        Assertions.assertTrue(randomPolygonVector1.isOrt(result) && randomPolygonVector2.isOrt(result));
    }

    @Test
    public void normalToPolygon6() throws NoSuchFieldException, IllegalAccessException {
        Model model = constructBrickModel();

        Field verticesField = Model.class.getDeclaredField("vertices");
        verticesField.setAccessible(true);

        Field polygonsField = Model.class.getDeclaredField("polygons");
        polygonsField.setAccessible(true);

        List<Vector3d> vertices = (List<Vector3d>) verticesField.get(model);
        List<Polygon> polygons = (List<Polygon>) polygonsField.get(model);
        
        Polygon randomPolygon = polygons.get(5);

        Vector3d result = NormalUtils.polygonNormal(randomPolygon, vertices);

        Vector3d vertex1 = vertices.get(randomPolygon.getVertexIndices().get(0));
        Vector3d vertex2 = vertices.get(randomPolygon.getVertexIndices().get(1));
        Vector3d vertex3 = vertices.get(randomPolygon.getVertexIndices().get(2));

        Vector3d randomPolygonVector1 = new Vector3d(vertex2.x() - vertex1.x(), vertex2.y() - vertex1.y(), vertex2.z() - vertex1.z());
        Vector3d randomPolygonVector2 = new Vector3d(vertex3.x() - vertex1.x(), vertex3.y() - vertex1.y(), vertex3.z() - vertex1.z());

        Assertions.assertTrue(randomPolygonVector1.isOrt(result) && randomPolygonVector2.isOrt(result));
    }


    @Test
    public void normalToPolygon7() throws NoSuchFieldException, IllegalAccessException {
        Model model = constructPyramidModel();

        Field verticesField = Model.class.getDeclaredField("vertices");
        verticesField.setAccessible(true);

        Field polygonsField = Model.class.getDeclaredField("polygons");
        polygonsField.setAccessible(true);

        List<Vector3d> vertices = (List<Vector3d>) verticesField.get(model);
        List<Polygon> polygons = (List<Polygon>) polygonsField.get(model);
        
        Polygon randomPolygon = polygons.get(0);

        Vector3d result = NormalUtils.polygonNormal(randomPolygon, vertices);

        Vector3d vertex1 = vertices.get(randomPolygon.getVertexIndices().get(0));
        Vector3d vertex2 = vertices.get(randomPolygon.getVertexIndices().get(1));
        Vector3d vertex3 = vertices.get(randomPolygon.getVertexIndices().get(2));

        Vector3d randomPolygonVector1 = new Vector3d(vertex2.x() - vertex1.x(), vertex2.y() - vertex1.y(), vertex2.z() - vertex1.z());
        Vector3d randomPolygonVector2 = new Vector3d(vertex3.x() - vertex1.x(), vertex3.y() - vertex1.y(), vertex3.z() - vertex1.z());

        Assertions.assertTrue(randomPolygonVector1.isOrt(result) && randomPolygonVector2.isOrt(result));
    }

    @Test
    public void normalToPolygon8() throws NoSuchFieldException, IllegalAccessException {
        Model model = constructPyramidModel();

        Field verticesField = Model.class.getDeclaredField("vertices");
        verticesField.setAccessible(true);

        Field polygonsField = Model.class.getDeclaredField("polygons");
        polygonsField.setAccessible(true);

        List<Vector3d> vertices = (List<Vector3d>) verticesField.get(model);
        List<Polygon> polygons = (List<Polygon>) polygonsField.get(model);
        
        Polygon randomPolygon = polygons.get(0);

        Vector3d result = NormalUtils.polygonNormal(randomPolygon, vertices);

        Vector3d vertex1 = vertices.get(randomPolygon.getVertexIndices().get(0));
        Vector3d vertex2 = vertices.get(randomPolygon.getVertexIndices().get(1));
        Vector3d vertex3 = vertices.get(randomPolygon.getVertexIndices().get(2));

        Vector3d randomPolygonVector1 = new Vector3d(vertex2.x() - vertex1.x(), vertex2.y() - vertex1.y(), vertex2.z() - vertex1.z());
        Vector3d randomPolygonVector2 = new Vector3d(vertex3.x() - vertex1.x(), vertex3.y() - vertex1.y(), vertex3.z() - vertex1.z());

        Assertions.assertTrue(randomPolygonVector1.isOrt(result) && randomPolygonVector2.isOrt(result));
    }

    @Test
    public void normalToPolygon9() throws NoSuchFieldException, IllegalAccessException {
        Model model = constructPyramidModel();

        Field verticesField = Model.class.getDeclaredField("vertices");
        verticesField.setAccessible(true);

        Field polygonsField = Model.class.getDeclaredField("polygons");
        polygonsField.setAccessible(true);

        List<Vector3d> vertices = (List<Vector3d>) verticesField.get(model);
        List<Polygon> polygons = (List<Polygon>) polygonsField.get(model);
        
        Polygon randomPolygon = polygons.get(0);

        Vector3d result = NormalUtils.polygonNormal(randomPolygon, vertices);

        Vector3d vertex1 = vertices.get(randomPolygon.getVertexIndices().get(0));
        Vector3d vertex2 = vertices.get(randomPolygon.getVertexIndices().get(1));
        Vector3d vertex3 = vertices.get(randomPolygon.getVertexIndices().get(2));

        Vector3d randomPolygonVector1 = new Vector3d(vertex2.x() - vertex1.x(), vertex2.y() - vertex1.y(), vertex2.z() - vertex1.z());
        Vector3d randomPolygonVector2 = new Vector3d(vertex3.x() - vertex1.x(), vertex3.y() - vertex1.y(), vertex3.z() - vertex1.z());

        Assertions.assertTrue(randomPolygonVector1.isOrt(result) && randomPolygonVector2.isOrt(result));
    }

    @Test
    public void normalToPolygon10() throws NoSuchFieldException, IllegalAccessException {
        Model model = constructPyramidModel();

        Field verticesField = Model.class.getDeclaredField("vertices");
        verticesField.setAccessible(true);

        Field polygonsField = Model.class.getDeclaredField("polygons");
        polygonsField.setAccessible(true);

        List<Vector3d> vertices = (List<Vector3d>) verticesField.get(model);
        List<Polygon> polygons = (List<Polygon>) polygonsField.get(model);
        
        Polygon randomPolygon = polygons.get(0);

        Vector3d result = NormalUtils.polygonNormal(randomPolygon, vertices);

        Vector3d vertex1 = vertices.get(randomPolygon.getVertexIndices().get(0));
        Vector3d vertex2 = vertices.get(randomPolygon.getVertexIndices().get(1));
        Vector3d vertex3 = vertices.get(randomPolygon.getVertexIndices().get(2));

        Vector3d randomPolygonVector1 = new Vector3d(vertex2.x() - vertex1.x(), vertex2.y() - vertex1.y(), vertex2.z() - vertex1.z());
        Vector3d randomPolygonVector2 = new Vector3d(vertex3.x() - vertex1.x(), vertex3.y() - vertex1.y(), vertex3.z() - vertex1.z());

        Assertions.assertTrue(randomPolygonVector1.isOrt(result) && randomPolygonVector2.isOrt(result));
    }

    @Test
    public void selectPolygonsSurroundingVertex1() throws NoSuchFieldException, IllegalAccessException {
        Model model = constructPyramidModel();
        
        Field verticesField = Model.class.getDeclaredField("vertices");
        verticesField.setAccessible(true);

        Field polygonsField = Model.class.getDeclaredField("polygons");
        polygonsField.setAccessible(true);

        List<Vector3d> vertices = (List<Vector3d>) verticesField.get(model);
        List<Polygon> polygons = (List<Polygon>) polygonsField.get(model);
        

        List<Polygon> result = NormalUtils.selectPolygonsSurroundingVertex(
                0, vertices, polygons
        );

        List<Polygon> expected = new ArrayList<>();
        expected.add(polygons.get(0));
        expected.add(polygons.get(1));
        expected.add(polygons.get(2));

        Assertions.assertEquals(expected, result);
    }

    @Test
    public void normalToVertex1() throws NoSuchFieldException, IllegalAccessException {
        Model model = constructBrickModel();

        Field verticesField = Model.class.getDeclaredField("vertices");
        verticesField.setAccessible(true);

        Field polygonsField = Model.class.getDeclaredField("polygons");
        polygonsField.setAccessible(true);

        List<Vector3d> vertices = (List<Vector3d>) verticesField.get(model);
        List<Polygon> polygons = (List<Polygon>) polygonsField.get(model);
        

        Vector3d result = NormalUtils.vertexNormal(7, vertices, polygons);

        Vector3d expected = new Vector3d((float) -1 / 3, (float) -1 / 3, (float) -1 / 3).norm();

        Assertions.assertEquals(expected.norm(), result.norm());
    }

    @Test
    public void normalToVertex2() throws NoSuchFieldException, IllegalAccessException {
        Model model = constructPyramidModel();

        Field verticesField = Model.class.getDeclaredField("vertices");
        verticesField.setAccessible(true);

        Field polygonsField = Model.class.getDeclaredField("polygons");
        polygonsField.setAccessible(true);

        List<Vector3d> vertices = (List<Vector3d>) verticesField.get(model);
        List<Polygon> polygons = (List<Polygon>) polygonsField.get(model);
        

        Vector3d result = NormalUtils.vertexNormal(3, vertices, polygons);
        Vector3d expected = new Vector3d(
                0,
                (float) (1 / Math.sqrt(2)) - (float) (2 / Math.sqrt(6)),
                (float) -(1 / Math.sqrt(2)) - (float) (2 / Math.sqrt(6))
        ).div(3).norm();

        Assertions.assertEquals(expected.norm(), result.norm());
    }
}
