package com.meow.bebrablender.objreader;


import com.meow.bebrablender.model.Model;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;

class ObjReaderFaceTest {
    private final ObjReader reader = new ObjReader(Path.of("src/main/resources/com/meow/bebrablender/ObjFiles/Null.obj"));

    ObjReaderFaceTest() throws IOException {
    }

    @Test
    void testParseFaceWord01() {
        ArrayList<String> polygon = new ArrayList<>(Arrays.asList("1", "2", "3", "4"));
        ArrayList<Integer> onePolygonVertexIndices = new ArrayList<>();
        ArrayList<Integer> onePolygonTextureVertexIndices = new ArrayList<>();
        ArrayList<Integer> onePolygonNormalIndices = new ArrayList<>();
        
        for (String s : polygon) {
            reader.parseFaceWord(s, onePolygonVertexIndices, onePolygonTextureVertexIndices,onePolygonNormalIndices, 10);

        }
        ArrayList<Integer> expectedResult = new ArrayList<>(Arrays.asList(0,1,2,3));
        Assertions.assertEquals(expectedResult, onePolygonVertexIndices);
    }

    @Test
    void testParseFaceWord02() {
        ArrayList<String> polygon = new ArrayList<>(Arrays.asList("1/1", "2/2", "3/5", "4/10"));
        ArrayList<Integer> onePolygonVertexIndices = new ArrayList<>();
        ArrayList<Integer> onePolygonTextureVertexIndices = new ArrayList<>();
        ArrayList<Integer> onePolygonNormalIndices = new ArrayList<>();
        for (String s : polygon) {
            reader.parseFaceWord(s, onePolygonVertexIndices, onePolygonTextureVertexIndices,onePolygonNormalIndices, 10);

        }
        ArrayList<Integer> expectedResult = new ArrayList<>(Arrays.asList(0,1,4,9));
        Assertions.assertEquals(expectedResult, onePolygonTextureVertexIndices);
    }

    @Test
    void testParseFaceWord03() {
        ArrayList<String> polygon = new ArrayList<>(Arrays.asList("1/1/1", "2/2/2", "3/3/4", "4/4/15"));
        ArrayList<Integer> onePolygonVertexIndices = new ArrayList<>();
        ArrayList<Integer> onePolygonTextureVertexIndices = new ArrayList<>();
        ArrayList<Integer> onePolygonNormalIndices = new ArrayList<>();
        for (String s : polygon) {
            reader.parseFaceWord(s, onePolygonVertexIndices, onePolygonTextureVertexIndices,onePolygonNormalIndices, 10);

        }
        ArrayList<Integer> expectedResult = new ArrayList<>(Arrays.asList(0,1,3,14));
        Assertions.assertEquals(expectedResult, onePolygonNormalIndices);
    }

    @Test
    void testParseFaceWord04() {
        ArrayList<String> polygon = new ArrayList<>(Arrays.asList("1//1", "2//2", "3//3", "4//5"));
        ArrayList<Integer> onePolygonVertexIndices = new ArrayList<>();
        ArrayList<Integer> onePolygonTextureVertexIndices = new ArrayList<>();
        ArrayList<Integer> onePolygonNormalIndices = new ArrayList<>();
        for (String s : polygon) {
            reader.parseFaceWord(s, onePolygonVertexIndices, onePolygonTextureVertexIndices,onePolygonNormalIndices, 10);

        }
        ArrayList<Integer> expectedResultTexture = new ArrayList<>();

        Assertions.assertEquals(expectedResultTexture, onePolygonTextureVertexIndices);
    }

    @Test
    void testParseFaceWord05() {
        ArrayList<String> polygon = new ArrayList<>(Arrays.asList("1//1", "2//2", "3//3", "4//5"));
        ArrayList<Integer> onePolygonVertexIndices = new ArrayList<>();
        ArrayList<Integer> onePolygonTextureVertexIndices = new ArrayList<>();
        ArrayList<Integer> onePolygonNormalIndices = new ArrayList<>();
        for (String s : polygon) {
            reader.parseFaceWord(s, onePolygonVertexIndices, onePolygonTextureVertexIndices,onePolygonNormalIndices, 10);

        }
        ArrayList<Integer> expectedResultNormal = new ArrayList<>(Arrays.asList(0,1,2,4));

        Assertions.assertEquals(expectedResultNormal, onePolygonNormalIndices);
    }

}