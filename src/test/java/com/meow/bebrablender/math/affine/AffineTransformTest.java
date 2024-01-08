package com.meow.bebrablender.math.affine;

import com.meow.bebrablender.math.vectors.Vector3d;
import com.meow.bebrablender.model.Model;
import com.meow.bebrablender.objreader.ObjReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

class AffineTransformTest {
    private final Model model = new ObjReader(Path.of("src/main/resources/com/meow/bebrablender/ObjFiles/AffineCube.obj")).read();

    private final AffineTransform affine = new AffineTransform(model);

    AffineTransformTest() throws IOException {
    }

    @Test
    void rotateX() {
        double a = 45;
        RotateX r = new RotateX(a);

        List<Vector3d> initVert = List.copyOf(model.getVertices());

        affine.rotateX(a);
        List<Vector3d> expected = model.getVertices();

        List<Vector3d> actual = List.of(
                r.apply(initVert.getFirst()),
                r.apply(initVert.get(1)),
                r.apply(initVert.get(2)),
                r.apply(initVert.get(3)),
                r.apply(initVert.get(4)),
                r.apply(initVert.get(5)),
                r.apply(initVert.get(6)),
                r.apply(initVert.get(7))
        );

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void vRotateX() {
        double a = 45;
        RotateX r = new RotateX(a);

        List<Vector3d> initVert = List.copyOf(model.getVertices());

        affine.rotateX(a);
        List<Vector3d> expected = model.getVertices();

        List<Vector3d> actual = List.of(
                r.apply(initVert.getFirst()),
                r.apply(initVert.get(1)),
                r.apply(initVert.get(2)),
                r.apply(initVert.get(3)),
                r.apply(initVert.get(4)),
                r.apply(initVert.get(5)),
                r.apply(initVert.get(6)),
                r.apply(initVert.get(7))
        );

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void rotateY() {
        double a = 45;
        RotateY r = new RotateY(a);

        List<Vector3d> initVert = List.copyOf(model.getVertices());

        affine.rotateX(a);
        List<Vector3d> expected = model.getVertices();

        List<Vector3d> actual = List.of(
                r.apply(initVert.getFirst()),
                r.apply(initVert.get(1)),
                r.apply(initVert.get(2)),
                r.apply(initVert.get(3)),
                r.apply(initVert.get(4)),
                r.apply(initVert.get(5)),
                r.apply(initVert.get(6)),
                r.apply(initVert.get(7))
        );

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void rotateZ() {
        double a = 45;
        RotateZ r = new RotateZ(a);

        List<Vector3d> initVert = List.copyOf(model.getVertices());

        affine.rotateX(a);
        List<Vector3d> expected = model.getVertices();

        List<Vector3d> actual = List.of(
                r.apply(initVert.getFirst()),
                r.apply(initVert.get(1)),
                r.apply(initVert.get(2)),
                r.apply(initVert.get(3)),
                r.apply(initVert.get(4)),
                r.apply(initVert.get(5)),
                r.apply(initVert.get(6)),
                r.apply(initVert.get(7))
        );

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void translate() {
        double tX = 5;
        double tY = 3;
        double tZ = 2;
        Translate t = new Translate(tX, tY, tZ);

        List<Vector3d> initVert = List.copyOf(model.getVertices());

        affine.translate(tX, tY, tZ);
        List<Vector3d> expected = model.getVertices();

        List<Vector3d> actual = List.of(
                t.apply(initVert.getFirst()),
                t.apply(initVert.get(1)),
                t.apply(initVert.get(2)),
                t.apply(initVert.get(3)),
                t.apply(initVert.get(4)),
                t.apply(initVert.get(5)),
                t.apply(initVert.get(6)),
                t.apply(initVert.get(7))
        );

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void scale() {
        double sX = 5;
        double sY = 3;
        double sZ = 9;
        Translate t = new Translate(sX, sY, sZ);

        List<Vector3d> initVert = List.copyOf(model.getVertices());

        affine.translate(sX, sY, sZ);
        List<Vector3d> expected = model.getVertices();

        List<Vector3d> actual = List.of(
                t.apply(initVert.getFirst()),
                t.apply(initVert.get(1)),
                t.apply(initVert.get(2)),
                t.apply(initVert.get(3)),
                t.apply(initVert.get(4)),
                t.apply(initVert.get(5)),
                t.apply(initVert.get(6)),
                t.apply(initVert.get(7))
        );

        Assertions.assertEquals(expected, actual);
    }
}