package com.meow.bebrablender.math.matrices;

import com.meow.bebrablender.math.vectors.Vector4d;

import java.util.Arrays;

public class Matrix4d extends AbstractSquareMatrix<Matrix4d, Vector4d> {
    public Matrix4d(double[][] data) {
        super(4, data);
    }

    public Matrix4d() {
        super(4);
    }

    @Override
    protected Matrix4d initialReturnThis() {
        return this;
    }

    @Override
    protected Vector4d initialCreateVector(double[] data) {
        return new Vector4d(data);
    }

    @Override
    public Matrix4d copy() {
        return new Matrix4d(deepDataCopy());
    }
}
