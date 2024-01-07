package com.meow.bebrablender.math.matrices;

import com.meow.bebrablender.math.vectors.Vector4f;

public class Matrix4f extends AbstractSquareMatrix<Matrix4f, Vector4f> {
    protected Matrix4f(double[][] data) {
        super(4, data);
    }

    @Override
    protected Matrix4f initialCreateMatrix(double[][] data) {
        return new Matrix4f(data);
    }

    @Override
    protected Vector4f initialCreateVector(double[] data) {
        return new Vector4f(data);
    }
}
