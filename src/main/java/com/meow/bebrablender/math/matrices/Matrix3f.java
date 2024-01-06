package com.meow.bebrablender.math.matrices;

import com.meow.vectors.Vector3f;

public class Matrix3f extends AbstractSquareMatrix<Matrix3f, Vector3f> {


    public Matrix3f(double[][] data) {
        super(3, data);
    }

    @Override
    protected Matrix3f initialCreateMatrix(double[][] data) {
        return new Matrix3f(data);
    }

    @Override
    protected Vector3f initialCreateVector(double[] data) {
        return new Vector3f(data);
    }
}
