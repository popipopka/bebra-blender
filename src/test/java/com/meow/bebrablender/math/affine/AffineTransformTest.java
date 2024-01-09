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
//    private final AffineTransform affine = new AffineTransform();
//
//    AffineTransformTest() throws IOException {
//    }
//
//    @Test
//    void rotateX() {
//        double a = 45;
//        RotateX r = new RotateX(a);
//
//        List<Vector3d> initVert = List.copyOf(model.getVertices());
//
//        affine.rotateX(a);
//        List<Vector3d> expected = model.getVertices();
//
//        List<Vector3d> actual = List.of(
//                r.applyToVector(initVert.getFirst()),
//                r.applyToVector(initVert.get(1)),
//                r.applyToVector(initVert.get(2)),
//                r.applyToVector(initVert.get(3)),
//                r.applyToVector(initVert.get(4)),
//                r.applyToVector(initVert.get(5)),
//                r.applyToVector(initVert.get(6)),
//                r.applyToVector(initVert.get(7))
//        );
//
//        Assertions.assertEquals(expected, actual);
//    }
//
//
//    @Test
//    void rotateY() {
//        double a = 45;
//        RotateY r = new RotateY(a);
//
//        List<Vector3d> initVert = List.copyOf(model.getVertices());
//
//        affine.rotateX(a);
//        List<Vector3d> expected = model.getVertices();
//
//        List<Vector3d> actual = List.of(
//                r.applyToVector(initVert.getFirst()),
//                r.applyToVector(initVert.get(1)),
//                r.applyToVector(initVert.get(2)),
//                r.applyToVector(initVert.get(3)),
//                r.applyToVector(initVert.get(4)),
//                r.applyToVector(initVert.get(5)),
//                r.applyToVector(initVert.get(6)),
//                r.applyToVector(initVert.get(7))
//        );
//
//        Assertions.assertEquals(expected, actual);
//    }
//
//    @Test
//    void rotateZ() {
//        double a = 45;
//        RotateZ r = new RotateZ(a);
//
//        List<Vector3d> initVert = List.copyOf(model.getVertices());
//
//        affine.rotateX(a);
//        List<Vector3d> expected = model.getVertices();
//
//        List<Vector3d> actual = List.of(
//                r.applyToVector(initVert.getFirst()),
//                r.applyToVector(initVert.get(1)),
//                r.applyToVector(initVert.get(2)),
//                r.applyToVector(initVert.get(3)),
//                r.applyToVector(initVert.get(4)),
//                r.applyToVector(initVert.get(5)),
//                r.applyToVector(initVert.get(6)),
//                r.applyToVector(initVert.get(7))
//        );
//
//        Assertions.assertEquals(expected, actual);
//    }
//
//    @Test
//    void translate() {
//        double tX = 5;
//        double tY = 3;
//        double tZ = 2;
//        Translate t = new Translate(tX, tY, tZ);
//
//        List<Vector3d> initVert = List.copyOf(model.getVertices());
//
//        affine.translate(tX, tY, tZ);
//        List<Vector3d> expected = model.getVertices();
//
//        List<Vector3d> actual = List.of(
//                t.applyToVector(initVert.getFirst()),
//                t.applyToVector(initVert.get(1)),
//                t.applyToVector(initVert.get(2)),
//                t.applyToVector(initVert.get(3)),
//                t.applyToVector(initVert.get(4)),
//                t.applyToVector(initVert.get(5)),
//                t.applyToVector(initVert.get(6)),
//                t.applyToVector(initVert.get(7))
//        );
//
//        Assertions.assertEquals(expected, actual);
//    }
//
//    @Test
//    void scale() {
//        double sX = 5;
//        double sY = 3;
//        double sZ = 9;
//        Translate t = new Translate(sX, sY, sZ);
//
//        List<Vector3d> initVert = List.copyOf(model.getVertices());
//
//        affine.translate(sX, sY, sZ);
//        List<Vector3d> expected = model.getVertices();
//
//        List<Vector3d> actual = List.of(
//                t.applyToVector(initVert.getFirst()),
//                t.applyToVector(initVert.get(1)),
//                t.applyToVector(initVert.get(2)),
//                t.applyToVector(initVert.get(3)),
//                t.applyToVector(initVert.get(4)),
//                t.applyToVector(initVert.get(5)),
//                t.applyToVector(initVert.get(6)),
//                t.applyToVector(initVert.get(7))
//        );
//
//        Assertions.assertEquals(expected, actual);
//    }
}