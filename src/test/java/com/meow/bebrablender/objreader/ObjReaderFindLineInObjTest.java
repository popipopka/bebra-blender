package com.meow.bebrablender.objreader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ObjReaderFindLineInObjTest  {
    private final ObjReader reader = new ObjReader(Path.of("src/main/ObjFiles/TestFindLineInObj.obj"));

    ObjReaderFindLineInObjTest() throws IOException {
    }

    @Test
    void testFindLine01() throws IOException{
        int expected = reader.findLineInObj("v", 1);
        Assertions.assertEquals(expected, 1);
    }

    @Test
    void testFindLine02() throws IOException{
        int expected = reader.findLineInObj("v", 0);
        Assertions.assertEquals(expected, -1);
    }

    @Test
    void testFindLine03() throws IOException{
        int expected = reader.findLineInObj("v", 5);
        Assertions.assertEquals(expected, 5);
    }

    @Test
    void testFindLine04() throws IOException{
        int expected = reader.findLineInObj("v", 6);
        Assertions.assertEquals(expected, -1);
    }

    @Test
    void testFindLine05() throws IOException{
        int expected = reader.findLineInObj("vt", 0);
        Assertions.assertEquals(expected, -1);
    }

    @Test
    void testFindLine06() throws IOException{
        int expected = reader.findLineInObj("vt", 2);
        Assertions.assertEquals(expected, 8);
    }
    @Test
    void testFindLine07() throws IOException{
        int expected = reader.findLineInObj("vt", 1);
        Assertions.assertEquals(expected, 7);
    }

    @Test
    void testFindLine08() throws IOException{
        int expected = reader.findLineInObj("v", 123);
        Assertions.assertEquals(expected, -1);
    }

    @Test
    void testFindLine09() throws IOException{
        int expected = reader.findLineInObj("vn", 0);
        Assertions.assertEquals(expected, -1);
    }

    @Test
    void testFindLine10() throws IOException{
        int expected = reader.findLineInObj("vn", 1);
        Assertions.assertEquals(expected, 13);
    }

    @Test
    void testFindLine11() throws IOException{
        int expected = reader.findLineInObj("vn", -123);
        Assertions.assertEquals(expected, -1);
    }

    @Test
    void testFindLine12() throws IOException{
        int expected = reader.findLineInObj("vn", 123);
        Assertions.assertEquals(expected, -1);
    }
}
