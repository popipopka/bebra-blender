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

}
