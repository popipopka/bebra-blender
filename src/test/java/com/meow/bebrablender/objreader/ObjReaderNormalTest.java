package com.meow.bebrablender.objreader;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;

import com.meow.bebrablender.math.vectors.Vector3d;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ObjReaderNormalTest {
    private final ObjReader reader = new ObjReader(Path.of("src/main/resources/com/meow/bebrablender/ObjFiles/Null.obj"));

    ObjReaderNormalTest() throws IOException {
    }

    @Test
    void testParseNormal01() {
        ArrayList<String> wordsInLineWithoutToken = new ArrayList<>(Arrays.asList("1.01", "1.02", "1.03"));
        Vector3d result = reader.parseNormal(wordsInLineWithoutToken, 5);
        Vector3d actual = new Vector3d(1.01, 1.02, 1.03);
        Assertions.assertEquals(result, actual);
    }

}