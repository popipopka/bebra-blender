package com.meow.bebrablender.math.affine;

import com.meow.bebrablender.math.matrices.Matrix4d;
import com.meow.bebrablender.math.vectors.Vector3d;
import com.meow.bebrablender.model.Model;

import java.util.List;
import java.util.function.Supplier;

public class AffineTransform {
    private final Matrix4d result;

    public AffineTransform() {
        this.result = new Matrix4d(new double[][]{
                {1, 0, 0, 0},
                {0, 1, 0, 0},
                {0, 0, 1, 0},
                {0, 0, 0, 1}
        });
    }

    public Matrix4d trs(Vector3d scale, Vector3d rotate, Vector3d translate) {
        scale(scale);
        rotate(rotate);
        translate(translate);
        return result;
    }

    public Matrix4d rotateX(double ang) {
        RotateX rotate = new RotateX(ang);
        applyTransform(() -> rotate);
        return result;
    }

    public Matrix4d rotateY(double ang) {
        RotateY rotate = new RotateY(ang);
        applyTransform(() -> rotate);
        return result;
    }

    public Matrix4d rotateZ(double ang) {
        RotateZ rotate = new RotateZ(ang);
        applyTransform(() -> rotate);
        return result;
    }

    public Matrix4d rotate(Vector3d rV) {
        rotateX(rV.x());
        rotateX(rV.y());
        rotateX(rV.z());
        return result;
    }

    public Matrix4d translate(double tX, double tY, double tZ) {
        Translate translate = new Translate(tX, tY, tZ);
        applyTransform(() -> translate);
        return result;
    }

    public Matrix4d translate(Vector3d tV) {
        return translate(tV.x(), tV.y(), tV.z());
    }

    public Matrix4d scale(double sX, double sY, double sZ) {
        Scale scale = new Scale(sX, sY, sZ);
        applyTransform(() -> scale);
        return result;
    }

    public Matrix4d scale(Vector3d tS) {
        return translate(tS.x(), tS.y(), tS.z());
    }

    public Matrix4d getAffine() {
        return result;
    }

    private void applyTransform(Supplier<AffineApplicable> transform) {
        transform.get().apply(result);
    }
}
