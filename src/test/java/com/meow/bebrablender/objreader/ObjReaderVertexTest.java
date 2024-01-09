package com.meow.bebrablender.objreader;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;

import com.meow.bebrablender.math.vectors.Vector3d;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
class ObjReaderVertexTest {
    private final ObjReader reader = new ObjReader(Path.of("src/main/resources/com/meow/bebrablender/ObjFiles/Null.obj"));

    ObjReaderVertexTest() throws IOException {
    }

    @Test
    void testParseVertex3f01() {
        ArrayList<String> wordsInLineWithoutToken = new ArrayList<>(Arrays.asList("1.01", "1.02", "1.03"));
        Vector3d result = reader.parseVertex(wordsInLineWithoutToken, 5);
        Vector3d expectedResult = new Vector3d(1.01, 1.02, 1.03);
        Assertions.assertEquals(result, expectedResult);
    }


}