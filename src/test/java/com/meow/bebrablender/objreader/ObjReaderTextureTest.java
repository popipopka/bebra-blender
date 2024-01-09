package com.meow.bebrablender.objreader;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.meow.bebrablender.math.vectors.Vector2d;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
class ObjReaderTextureTest {
    private final ObjReader reader = new ObjReader(Path.of("src/main/resources/com/meow/bebrablender/ObjFiles/Null.obj"));

    ObjReaderTextureTest() throws IOException {
    }

    @Test
    void testParseVertex2f01() {
        ArrayList<String> wordsInLineWithoutToken = new ArrayList<>(Arrays.asList("1.01", "1.02"));
        Vector2d result = reader.parseTextureVertex(wordsInLineWithoutToken, 5);
        Vector2d expectedResult = new Vector2d(1.01, 1.02);
        Assertions.assertEquals(result, expectedResult);
    }

    @Test
    void testParseVertex2f02() {
        ArrayList<String> wordsInLineWithoutToken = new ArrayList<>(Arrays.asList("1.01", "1.03"));
        Vector2d result = reader.parseTextureVertex(wordsInLineWithoutToken, 5);
        Vector2d expectedResult = new Vector2d(1.01, 1.10);
        Assertions.assertNotEquals(result, expectedResult);
    }

    @Test
    void testParseVertex2f03() {
        ArrayList<String> wordsInLineWithoutToken = new ArrayList<>(Arrays.asList("ab", "o"));
        try {
            reader.parseTextureVertex(wordsInLineWithoutToken, 10);
            Assertions.fail();
        } catch (ObjReaderException exception) {
            String expectedError = "Error parsing OBJ file on line: 10. Failed to parse double value.";
            Assertions.assertEquals(expectedError, exception.getMessage());
        }
    }

    @Test
    void testParseVertex2f04() {
        ArrayList<String> wordsInLineWithoutToken = new ArrayList<>(List.of("1.0"));
        try {
            reader.parseTextureVertex(wordsInLineWithoutToken, 10);
            Assertions.fail();
        } catch (ObjReaderException exception) {
            String expectedError = "Error parsing OBJ file on line: 10. Too few texture vertex arguments.";
            Assertions.assertEquals(expectedError, exception.getMessage());
        }
    }

    @Test
    void testParseVertex2f05() {
        ArrayList<String> wordsInLineWithoutToken = new ArrayList<>(Arrays.asList("1.0", "1.1", "1.2"));
        try {
            reader.parseTextureVertex(wordsInLineWithoutToken, 10);
            Assertions.fail();
        } catch (ObjReaderException exception) {
            String expectedError = "Error parsing OBJ file on line: 10. Too much texture vertex arguments.";
            Assertions.assertEquals(expectedError, exception.getMessage());
        }
    }

}
