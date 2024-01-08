package com.meow.bebrablender.math.affine;

import com.meow.bebrablender.math.vectors.Vector3d;
import com.meow.bebrablender.model.Model;

import java.util.List;
import java.util.function.Supplier;

public class AffineTransform {
    private final Model model;

    public AffineTransform(Model model) {
        this.model = model;
    }

    public void trs(Vector3d scale, Vector3d rotate, Vector3d translate) {
        scale(scale);
        rotate(rotate);
        translate(translate);
    }

    public void rotateX(double ang) {
        RotateX rotate = new RotateX(ang);
        applyTransform(() -> rotate);
    }

    public void rotateY(double ang) {
        RotateY rotate = new RotateY(ang);
        applyTransform(() -> rotate);
    }

    public void rotateZ(double ang) {
        RotateZ rotate = new RotateZ(ang);
        applyTransform(() -> rotate);
    }

    public void rotate(Vector3d rV) {
        rotateX(rV.x());
        rotateX(rV.y());
        rotateX(rV.z());
    }

    public void translate(double tX, double tY, double tZ) {
        Translate translate = new Translate(tX, tY, tZ);
        applyTransform(() -> translate);
    }

    public void translate(Vector3d tV) {
        translate(tV.x(), tV.y(), tV.z());
    }

    public void scale(double sX, double sY, double sZ) {
        Scale scale = new Scale(sX, sY, sZ);
        applyTransform(() -> scale);
    }

    public void scale(Vector3d tS) {
        translate(tS.x(), tS.y(), tS.z());
    }

    private void applyTransform(Supplier<AffineApplicable> transform) {
        List<Vector3d> vertices = this.model.getVertices();
        for (Vector3d v : vertices) {
            transform.get().apply(v);
        }
    }
}
