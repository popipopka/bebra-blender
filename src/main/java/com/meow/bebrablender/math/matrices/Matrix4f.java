package com.meow.bebrablender.math.matrices;

import com.meow.bebrablender.math.vectors.Vector4f;

public class Matrix4f extends AbstractSquareMatrix<Matrix4f, Vector4f> {
    public Matrix4f(double[][] data) {
        super(4, data);
    }

    public Matrix4f() {

        super(4);
    }

    @Override
    protected Matrix4f initialReturnThis() {
        return this;
    }

    @Override
    protected Vector4f initialCreateVector(double[] data) {
        return new Vector4f(data);
    }
}
