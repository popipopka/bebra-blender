package com.meow.bebrablender.math.matrices;

import com.meow.bebrablender.math.vectors.Vector3d;

public class Matrix3d extends AbstractSquareMatrix<Matrix3d, Vector3d> {


    public Matrix3d(double[][] data) {
        super(3, data);
    }

    @Override
    protected Matrix3d initialReturnThis() {
        return this;
    }

    @Override
    protected Vector3d initialCreateVector(double[] data) {
        return new Vector3d(data);
    }

    @Override
    public Matrix3d copy() {
        return new Matrix3d(data);
    }
}
