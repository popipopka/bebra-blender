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

    public void trs(
            double sX, double sY, double sZ,
            double aX, double aY, double aZ,
            double tX, double tY, double tZ) {
        scale(sX, sY, sZ);

        rotateX(aX);
        rotateY(aY);
        rotateZ(aZ);

        translate(tX, tY, tZ);
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

    public void translate(double tX, double tY, double tZ) {
        Translate translate = new Translate(tX, tY, tZ);
        applyTransform(() -> translate);
    }

    public void scale(double sX, double sY, double sZ) {
        Scale scale = new Scale(sX, sY, sZ);
        applyTransform(() -> scale);
    }

    private void applyTransform(Supplier<AffineApplicable> transform) {
        List<Vector3d> vertices = this.model.getVertices();
        for (Vector3d v : vertices) {
            transform.get().apply(v);
        }
    }
}
