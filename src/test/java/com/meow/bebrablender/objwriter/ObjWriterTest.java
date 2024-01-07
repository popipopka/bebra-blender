package com.meow.bebrablender.objwriter;


import com.meow.bebrablender.math.vectors.Vector2f;
import com.meow.bebrablender.math.vectors.Vector3f;
import com.meow.bebrablender.model.Model;
import com.meow.bebrablender.model.Polygon;
import com.meow.bebrablender.objreader.ObjReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;

class ObjWriterTest {
    @Test
    public void testWriteVerticesToObjFile() throws IOException {
        ArrayList<Vector3f> vertices = new ArrayList<>();
        vertices.add(new Vector3f(1.0f, 1.0f, 1.0f));

        try (PrintWriter printWriter = new PrintWriter("Test file.obj")) {
            ObjWriter.writeVerticesOfModel(printWriter, vertices);
        }

        String fileContent = Files.readString(Path.of("Test file.obj"));

        Assertions.assertTrue(fileContent.contains("v 1.0 1.0 1.0"));
    }

    @Test
    public void testWriteTextureVerticesToObjFile() throws IOException {
        ArrayList<Vector2f> textureVertices = new ArrayList<>();
        textureVertices.add(new Vector2f(1.0f, 1.0f));

        try (PrintWriter printWriter = new PrintWriter("Test file.obj")) {
            ObjWriter.writeTextureVerticesOfModel(printWriter, textureVertices);
        }

        String fileContent = Files.readString(Path.of("Test file.obj"));

        Assertions.assertTrue(fileContent.contains("vt 1.0 1.0"));
    }

    @Test
    public void testWriteNormalsToObjFile() throws IOException {
        ArrayList<Vector3f> normals = new ArrayList<>();
        normals.add(new Vector3f(1.0f, 1.0f, 1.0f));

        try (PrintWriter printWriter = new PrintWriter("Test file.obj")) {
            ObjWriter.writeNormalsOfModel(printWriter, normals);
        }

        String fileContent = Files.readString(Path.of("Test file.obj"));

        Assertions.assertTrue(fileContent.contains("vn 1.0 1.0 1.0"));
    }

    @Test
    public void testWritePolygonsToObjFile() throws IOException {
        ArrayList<Polygon> polygons = new ArrayList<>();
        Polygon polygon = new Polygon();
        polygon.setVertexIndices(new ArrayList<>(Arrays.asList(-1, 1, 4)));
        polygon.setTextureVertexIndices(new ArrayList<>(Arrays.asList(-1, 1, 4)));
        polygon.setNormalIndices(new ArrayList<>(Arrays.asList(-1, 1, 4)));
        polygons.add(polygon);
        //здесь(-1,1,4) а дальше (0,2,5) тк при считывании данных в листы полигона мы должны уменьшить
        //значения на единицу(см методичку стр 48)
        try (PrintWriter printWriter = new PrintWriter("Test file.obj")) {
            ObjWriter.writePolygonsOfModel(printWriter, polygons);
        }

        String fileContent = Files.readString(Path.of("Test file.obj"));
        Assertions.assertTrue(fileContent.contains("f 0/0/0 2/2/2 5/5/5"));
    }
    @Test
    public void testCompareObjFiles() throws IOException {
        String fileContent = Files.readString(Path.of("3DModels/Faceform/WrapUpperTeeth.obj"));
        Model originalModel = ObjReader.read(fileContent);

        ObjWriter.writeModelToObjFile("Test file.obj", originalModel);
        String fileContent2 = Files.readString(Path.of("Test file.obj"));
        Model newModel = ObjReader.read(fileContent2);

        Assertions.assertEquals(originalModel, newModel);
    }

}
