package com.meow.bebrablender.objreader;


import com.meow.bebrablender.model.Model;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;

public class ObjReaderFaceTest {

    @Test
    public void testParseFaceWord01() {
        ArrayList<String> polygon = new ArrayList<>(Arrays.asList("1", "2", "3", "4"));
        ArrayList<Integer> onePolygonVertexIndices = new ArrayList<>();
        ArrayList<Integer> onePolygonTextureVertexIndices = new ArrayList<>();
        ArrayList<Integer> onePolygonNormalIndices = new ArrayList<>();
        for (String s : polygon) {
            ObjReader.parseFaceWord(s, onePolygonVertexIndices, onePolygonTextureVertexIndices,onePolygonNormalIndices, 10);

        }
        ArrayList<Integer> expectedResult = new ArrayList<>(Arrays.asList(0,1,2,3));
        Assertions.assertEquals(expectedResult, onePolygonVertexIndices);
    }

    @Test
    public void testParseFaceWord02() {
        ArrayList<String> polygon = new ArrayList<>(Arrays.asList("1/1", "2/2", "3/5", "4/10"));
        ArrayList<Integer> onePolygonVertexIndices = new ArrayList<>();
        ArrayList<Integer> onePolygonTextureVertexIndices = new ArrayList<>();
        ArrayList<Integer> onePolygonNormalIndices = new ArrayList<>();
        for (String s : polygon) {
            ObjReader.parseFaceWord(s, onePolygonVertexIndices, onePolygonTextureVertexIndices,onePolygonNormalIndices, 10);

        }
        ArrayList<Integer> expectedResult = new ArrayList<>(Arrays.asList(0,1,4,9));
        Assertions.assertEquals(expectedResult, onePolygonTextureVertexIndices);
    }

    @Test
    public void testParseFaceWord03() {
        ArrayList<String> polygon = new ArrayList<>(Arrays.asList("1/1/1", "2/2/2", "3/3/4", "4/4/15"));
        ArrayList<Integer> onePolygonVertexIndices = new ArrayList<>();
        ArrayList<Integer> onePolygonTextureVertexIndices = new ArrayList<>();
        ArrayList<Integer> onePolygonNormalIndices = new ArrayList<>();
        for (String s : polygon) {
            ObjReader.parseFaceWord(s, onePolygonVertexIndices, onePolygonTextureVertexIndices,onePolygonNormalIndices, 10);

        }
        ArrayList<Integer> expectedResult = new ArrayList<>(Arrays.asList(0,1,3,14));
        Assertions.assertEquals(expectedResult, onePolygonNormalIndices);
    }

    @Test
    public void testParseFaceWord04() {
        ArrayList<String> polygon = new ArrayList<>(Arrays.asList("1//1", "2//2", "3//3", "4//5"));
        ArrayList<Integer> onePolygonVertexIndices = new ArrayList<>();
        ArrayList<Integer> onePolygonTextureVertexIndices = new ArrayList<>();
        ArrayList<Integer> onePolygonNormalIndices = new ArrayList<>();
        for (String s : polygon) {
            ObjReader.parseFaceWord(s, onePolygonVertexIndices, onePolygonTextureVertexIndices,onePolygonNormalIndices, 10);

        }
        ArrayList<Integer> expectedResultTexture = new ArrayList<>();

        Assertions.assertEquals(expectedResultTexture, onePolygonTextureVertexIndices);
    }

    @Test
    public void testParseFaceWord05() {
        ArrayList<String> polygon = new ArrayList<>(Arrays.asList("1//1", "2//2", "3//3", "4//5"));
        ArrayList<Integer> onePolygonVertexIndices = new ArrayList<>();
        ArrayList<Integer> onePolygonTextureVertexIndices = new ArrayList<>();
        ArrayList<Integer> onePolygonNormalIndices = new ArrayList<>();
        for (String s : polygon) {
            ObjReader.parseFaceWord(s, onePolygonVertexIndices, onePolygonTextureVertexIndices,onePolygonNormalIndices, 10);

        }
        ArrayList<Integer> expectedResultNormal = new ArrayList<>(Arrays.asList(0,1,2,4));

        Assertions.assertEquals(expectedResultNormal, onePolygonNormalIndices);
    }

    @Test
    public void testParseFaceWord06() {
        try {
            String file = Files.readString(Path.of("src/main/ObjFiles/TestWithoutPolygons01.obj"));
            ObjReader.read(file);
            Assertions.assertTrue(false);
        } catch (Exception exception) {
            String error = "Error parsing OBJ file on line: 16. OBJ has not any polygons";
            Assertions.assertEquals(exception.getMessage(), error);
        }

    }

    @Test
    public void testParseFaceWord07() {
        try {
            String file = Files.readString(Path.of("src/main/ObjFiles/TestWithoutVertex01.obj"));
            Model model = ObjReader.read(file);
            Assertions.assertTrue(false);
        } catch (Exception exception) {
            String error = "Error parsing OBJ file on line: 6. OBJ has not any vertices";
            Assertions.assertEquals(exception.getMessage(), error);
        }
    }

    @Test
    public void testParseFaceWord08() {
        try {
            String file = Files.readString(Path.of("src/main/ObjFiles/TestWithoutPolygons02.obj"));
            Model model = ObjReader.read(file);
            Assertions.assertTrue(false);
        } catch (Exception exception) {
            String error = "Error parsing OBJ file on line: 18. Polygon has too few vertices.";
            Assertions.assertEquals(exception.getMessage(), error);
        }
    }

    @Test
    public void testParseFaceWord09() {
        try {
            String file = Files.readString(Path.of("src/main/ObjFiles/TestWithoutTextureVericesPolygon01.obj"));
            Model model = ObjReader.read(file);
            Assertions.assertTrue(false);
        } catch (Exception exception) {
            String error = "Error parsing OBJ file on line: 20. Polygon has no texture vertices.";
            Assertions.assertEquals(exception.getMessage(), error);
        }
    }

    @Test
    public void testParseFaceWord10() {
        try {
            String file = Files.readString(Path.of("src/main/ObjFiles/TestWithoutTextureVericesPolygon01.obj"));
            Model model = ObjReader.read(file);
            Assertions.assertTrue(false);
        } catch (Exception exception) {
            String error = "Error parsing OBJ file on line: 20. Polygon has no texture vertices.";
            Assertions.assertEquals(error,exception.getMessage());
        }
    }

    @Test
    public void testParseFaceWord11() {
        try {
            String file = Files.readString(Path.of("src/main/ObjFiles/TestIncorrectSpecifiedIndexPolygon01.obj"));
            Model model = ObjReader.read(file);
            Assertions.assertTrue(false);
        } catch (Exception exception) {
            String error = "Error parsing OBJ file on line: 5. The polygon is specified incorrectly: vertex index out of bounds.";
            Assertions.assertEquals(error,exception.getMessage());
        }
    }

    @Test
    public void testParseFaceWord12() {
        try {
            String file = Files.readString(Path.of("src/main/ObjFiles/TestIncorrectSpecifiedIndexPolygon02.obj"));
            Model model = ObjReader.read(file);
            Assertions.assertTrue(false);
        } catch (Exception exception) {
            String error = "Error parsing OBJ file on line: 5. The polygon is specified incorrectly: vertex index out of bounds.";
            Assertions.assertEquals(error,exception.getMessage());
        }
    }

    @Test
    public void testParseFaceWord13() {
        try {
            String file = Files.readString(Path.of("src/main/ObjFiles/TestIncorrectSpecifiedIndexPolygon03.obj"));
            Model model = ObjReader.read(file);
            Assertions.assertTrue(false);
        } catch (Exception exception) {
            String error = "Error parsing OBJ file on line: 11. The polygon is specified incorrectly: texture index out of bounds.";
            Assertions.assertEquals(error,exception.getMessage());
        }
    }

    @Test
    public void testParseFaceWord14() {
        try {
            String file = Files.readString(Path.of("src/main/ObjFiles/TestIncorrectSpecifiedIndexPolygon04.obj"));
            Model model = ObjReader.read(file);
            Assertions.assertTrue(false);
        } catch (Exception exception) {
            String error = "Error parsing OBJ file on line: 11. The polygon is specified incorrectly: texture index out of bounds.";
            Assertions.assertEquals(error,exception.getMessage());
        }
    }

    @Test
    public void testParseFaceWord15() {
        try {
            String file = Files.readString(Path.of("src/main/ObjFiles/TestIncorrectSpecifiedIndexPolygon05.obj"));
            Model model = ObjReader.read(file);
            Assertions.assertTrue(false);
        } catch (Exception exception) {
            String error = "Error parsing OBJ file on line: 17. The polygon is specified incorrectly: normal index out of bounds.";
            Assertions.assertEquals(error,exception.getMessage());
        }
    }

    @Test
    public void testParseFaceWord16() {
        try {
            String file = Files.readString(Path.of("src/main/ObjFiles/TestIncorrectSpecifiedIndexPolygon06.obj"));
            Model model = ObjReader.read(file);
            Assertions.assertTrue(false);
        } catch (Exception exception) {
            String error = "Error parsing OBJ file on line: 17. The polygon is specified incorrectly: normal index out of bounds.";
            Assertions.assertEquals(error,exception.getMessage());
        }
    }

    @Test
    public void testParseFaceWord17() {
        try {
            String file = Files.readString(Path.of("src/main/ObjFiles/TestInvalidElementSizePolygon01.obj"));
            Model model = ObjReader.read(file);
            Assertions.assertTrue(false);
        } catch (Exception exception) {
            String error = "Error parsing OBJ file on line: 19. Invalid element size.";
            Assertions.assertEquals(error,exception.getMessage());
        }
    }

    @Test
    public void testParseFaceWord18() {
        try {
            String file = Files.readString(Path.of("src/main/ObjFiles/TestInvalidElementSizePolygon02.obj"));
            Model model = ObjReader.read(file);
            Assertions.assertTrue(false);
        } catch (Exception exception) {
            String error = "Error parsing OBJ file on line: 19. Invalid element size.";
            Assertions.assertEquals(error,exception.getMessage());
        }
    }

    @Test
    public void testParseFaceWord19() {
        try {
            String file = Files.readString(Path.of("src/main/ObjFiles/TestInvalidElementSizePolygon03.obj"));
            Model model = ObjReader.read(file);
            Assertions.assertTrue(false);
        } catch (Exception exception) {
            String error = "Error parsing OBJ file on line: 19. Invalid element size.";
            Assertions.assertEquals(error,exception.getMessage());
        }
    }

    @Test
    public void testParseFaceWord20() {
        try {
            String file = Files.readString(Path.of("src/main/ObjFiles/TestFailedToParseInt01.obj"));
            Model model = ObjReader.read(file);
            Assertions.assertTrue(false);
        } catch (Exception exception) {
            String error = "Error parsing OBJ file on line: 19. Failed to parse int value.";
            Assertions.assertEquals(error,exception.getMessage());
        }
    }

    @Test
    public void testParseFaceWord21() {
        try {
            String file = Files.readString(Path.of("src/main/ObjFiles/TestFailedToParseInt02.obj"));
            Model model = ObjReader.read(file);
            Assertions.assertTrue(false);
        } catch (Exception exception) {
            String error = "Error parsing OBJ file on line: 19. Failed to parse int value.";
            Assertions.assertEquals(error,exception.getMessage());
        }
    }

    @Test
    public void testParseFaceWord22() {
        try {
            String file = Files.readString(Path.of("src/main/ObjFiles/TestFailedToParseInt03.obj"));
            Model model = ObjReader.read(file);
            Assertions.assertTrue(false);
        } catch (Exception exception) {
            String error = "Error parsing OBJ file on line: 19. Failed to parse int value.";
            Assertions.assertEquals(error,exception.getMessage());
        }
    }

    @Test
    public void testParseFaceWord23() {
        String nameOfFile = "stupidFile.obj";
        try {
            String file = Files.readString(Path.of(nameOfFile));
            Model model = ObjReader.read(file);
            Assertions.assertTrue(false);
        } catch (Exception exception) {
            String error = nameOfFile;
            Assertions.assertEquals(error, exception.getMessage());
        }
    }

    @Test
    public void testParseFaceWord24() {
        try {
            String file = Files.readString(Path.of("src/main/ObjFiles/TestIncorrectSpecifiedIndexPolygon07.obj"));
            Model model = ObjReader.read(file);
            Assertions.assertTrue(false);
        } catch (Exception exception) {
            String error = "Error parsing OBJ file on line: 19. The polygon can`t contain the same vertex indices";
            Assertions.assertEquals(error, exception.getMessage());
        }
    }

    @Test
    public void testParseFaceWord25() {
        try {
            String file = Files.readString(Path.of("src/main/ObjFiles/TestIncorrectSpecifiedIndexPolygon08.obj"));
            Model model = ObjReader.read(file);
            Assertions.assertTrue(false);
        } catch (Exception exception) {
            String error = "Error parsing OBJ file on line: 20. The polygon can`t contain the same vertex indices";
            Assertions.assertEquals(error, exception.getMessage());
        }
    }
}