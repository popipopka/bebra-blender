package com.meow.bebrablender.objreader;

import java.util.ArrayList;
import java.util.Arrays;

import com.meow.bebrablender.math.vectors.Vector2f;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
public class ObjReaderTextureTest {
    @Test
    public void testParseVertex2f01() {
        ArrayList<String> wordsInLineWithoutToken = new ArrayList<>(Arrays.asList("1.01", "1.02"));
        Vector2f result = ObjReader.parseTextureVertex(wordsInLineWithoutToken, 5);
        Vector2f expectedResult = new Vector2f(1.01f, 1.02f);
        Assertions.assertTrue(result.equals(expectedResult));
    }

    @Test
    public void testParseVertex2f02() {
        ArrayList<String> wordsInLineWithoutToken = new ArrayList<>(Arrays.asList("1.01", "1.03"));
        Vector2f result = ObjReader.parseTextureVertex(wordsInLineWithoutToken, 5);
        Vector2f expectedResult = new Vector2f(1.01f, 1.10f);
        Assertions.assertFalse(result.equals(expectedResult));
    }

    @Test
    public void testParseVertex2f03() {
        ArrayList<String> wordsInLineWithoutToken = new ArrayList<>(Arrays.asList("ab", "o"));
        try {
            ObjReader.parseTextureVertex(wordsInLineWithoutToken, 10);
            Assertions.assertTrue(false);
        } catch (ObjReaderException exception) {
            String expectedError = "Error parsing OBJ file on line: 10. Failed to parse float value.";
            Assertions.assertEquals(expectedError, exception.getMessage());
        }
    }

    @Test
    public void testParseVertex2f04() {
        ArrayList<String> wordsInLineWithoutToken = new ArrayList<>(Arrays.asList("1.0"));
        try {
            ObjReader.parseTextureVertex(wordsInLineWithoutToken, 10);
            Assertions.assertTrue(false);
        } catch (ObjReaderException exception) {
            String expectedError = "Error parsing OBJ file on line: 10. Too few texture vertex arguments.";
            Assertions.assertEquals(expectedError, exception.getMessage());
        }
    }

    @Test
    public void testParseVertex2f05() {
        ArrayList<String> wordsInLineWithoutToken = new ArrayList<>(Arrays.asList("1.0", "1.1", "1.2"));
        try {
            ObjReader.parseTextureVertex(wordsInLineWithoutToken, 10);
            Assertions.assertTrue(false);
        } catch (ObjReaderException exception) {
            String expectedError = "Error parsing OBJ file on line: 10. Too much texture vertex arguments.";
            Assertions.assertEquals(expectedError, exception.getMessage());
        }
    }

}
