package com.meow.bebrablender.objreader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ObjReaderFindLineInObjTest  {

    @Test
    public void testFindLine01() throws IOException{
        Path path = Path.of("src/main/ObjFiles/TestFindLineInObj.obj");
        String file = Files.readString(path);
        Scanner scanner = new Scanner(file);

        int expected = ObjReader.findLineInObj(scanner, "v", 1);
        Assertions.assertEquals(expected, 1);
    }

    @Test
    public void testFindLine02() throws IOException{
        Path path = Path.of("src/main/ObjFiles/TestFindLineInObj.obj");
        String file = Files.readString(path);
        Scanner scanner = new Scanner(file);

        int expected = ObjReader.findLineInObj(scanner, "v", 0);
        Assertions.assertEquals(expected, -1);
    }

    @Test
    public void testFindLine03() throws IOException{
        Path path = Path.of("src/main/ObjFiles/TestFindLineInObj.obj");
        String file = Files.readString(path);
        Scanner scanner = new Scanner(file);

        int expected = ObjReader.findLineInObj(scanner, "v", 5);
        Assertions.assertEquals(expected, 5);
    }

    @Test
    public void testFindLine04() throws IOException{
        Path path = Path.of("src/main/ObjFiles/TestFindLineInObj.obj");
        String file = Files.readString(path);
        Scanner scanner = new Scanner(file);

        int expected = ObjReader.findLineInObj(scanner, "v", 6);
        Assertions.assertEquals(expected, -1);
    }

    @Test
    public void testFindLine05() throws IOException{
        Path path = Path.of("src/main/ObjFiles/TestFindLineInObj.obj");
        String file = Files.readString(path);
        Scanner scanner = new Scanner(file);

        int expected = ObjReader.findLineInObj(scanner, "vt", 0);
        Assertions.assertEquals(expected, -1);
    }

    @Test
    public void testFindLine06() throws IOException{
        Path path = Path.of("src/main/ObjFiles/TestFindLineInObj.obj");
        String file = Files.readString(path);
        Scanner scanner = new Scanner(file);

        int expected = ObjReader.findLineInObj(scanner, "vt", 2);
        Assertions.assertEquals(expected, 8);
    }
    @Test
    public void testFindLine07() throws IOException{
        Path path = Path.of("src/main/ObjFiles/TestFindLineInObj.obj");
        String file = Files.readString(path);
        Scanner scanner = new Scanner(file);

        int expected = ObjReader.findLineInObj(scanner, "vt", 1);
        Assertions.assertEquals(expected, 7);
    }

    @Test
    public void testFindLine08() throws IOException{
        Path path = Path.of("src/main/ObjFiles/TestFindLineInObj.obj");
        String file = Files.readString(path);
        Scanner scanner = new Scanner(file);

        int expected = ObjReader.findLineInObj(scanner, "v", 123);
        Assertions.assertEquals(expected, -1);
    }

    @Test
    public void testFindLine09() throws IOException{
        Path path = Path.of("src/main/ObjFiles/TestFindLineInObj.obj");
        String file = Files.readString(path);
        Scanner scanner = new Scanner(file);

        int expected = ObjReader.findLineInObj(scanner, "vn", 0);
        Assertions.assertEquals(expected, -1);
    }

    @Test
    public void testFindLine10() throws IOException{
        Path path = Path.of("src/main/ObjFiles/TestFindLineInObj.obj");
        String file = Files.readString(path);
        Scanner scanner = new Scanner(file);

        int expected = ObjReader.findLineInObj(scanner, "vn", 1);
        Assertions.assertEquals(expected, 13);
    }

    @Test
    public void testFindLine11() throws IOException{
        Path path = Path.of("src/main/ObjFiles/TestFindLineInObj.obj");
        String file = Files.readString(path);
        Scanner scanner = new Scanner(file);

        int expected = ObjReader.findLineInObj(scanner, "vn", -123);
        Assertions.assertEquals(expected, -1);
    }

    @Test
    public void testFindLine12() throws IOException{
        Path path = Path.of("src/main/ObjFiles/TestFindLineInObj.obj");
        String file = Files.readString(path);
        Scanner scanner = new Scanner(file);

        int expected = ObjReader.findLineInObj(scanner, "vn", 123);
        Assertions.assertEquals(expected, -1);
    }
}
