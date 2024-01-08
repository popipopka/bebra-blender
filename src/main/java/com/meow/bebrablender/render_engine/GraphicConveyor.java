package com.meow.bebrablender.render_engine;

import com.meow.bebrablender.math.matrices.Matrix4d;
import com.meow.bebrablender.math.vectors.Vector3d;

public class GraphicConveyor {

    public static Matrix4d rotateScaleTranslate() {
        double[][] matrix = new double[][]{
                new double[]{1, 0, 0, 0},
                new double[]{1, 1, 0, 0},
                new double[]{1, 0, 1, 0},
                new double[]{1, 0, 0, 1}
        };
        return new Matrix4d(matrix);
    }

    public static Matrix4d lookAt(Vector3d eye, Vector3d target) {
        return lookAt(eye, target, new Vector3d(0F, 1.0F, 0F));
    }

    public static Matrix4d lookAt(Vector3d eye, Vector3d target, Vector3d up) {
        Vector3d resultX = new Vector3d();
        Vector3d resultY = new Vector3d();
        Vector3d resultZ = new Vector3d();

        resultZ.sub(target, eye);
        resultX.crossProd(up, resultZ);
        resultY.crossProd(resultZ, resultX);

        resultX.norm();
        resultY.norm();
        resultZ.norm();

        double[][] matrix = new double[][]{
                new double[]{resultX.x(), resultY.x(), resultZ.x(), 0},
                new double[]{resultX.y(), resultY.y(), resultZ.y(), 0},
                new double[]{resultX.z(), resultY.z(), resultZ.z(), 0},
                new double[]{-resultX.sclProd(eye), -resultY.sclProd(eye), -resultZ.sclProd(eye), 1}
        };
        return new Matrix4d(matrix);
    }

    public static Matrix4d perspective(
            final double fov,
            final double aspectRatio,
            final double nearPlane,
            final double farPlane) {
        Matrix4d result = new Matrix4d();
        double[][] resultData = result.data();

        double tangentMinusOnDegree = 1.0F / (Math.tan(fov * 0.5F));

        resultData[0][0] = tangentMinusOnDegree / aspectRatio;
        resultData[1][1] = tangentMinusOnDegree;
        resultData[2][2] = (farPlane + nearPlane) / (farPlane - nearPlane);
        resultData[2][3] = 1.0F;
        resultData[3][2] = 2 * (nearPlane * farPlane) / (nearPlane - farPlane);
        
        result.setData(resultData);
        
        return result;
    }

    public static Vector3d multiplyMatrix4ByVector3(final Matrix4d matrix, final Vector3d vertex) {
        double[][] matrixData = matrix.data();
        
        final double x = (vertex.x() * matrixData[0][0]) + (vertex.y() * matrixData[1][0]) + (vertex.z() * matrixData[2][0]) + matrixData[3][0];
        final double y = (vertex.x() * matrixData[0][1]) + (vertex.y() * matrixData[1][1]) + (vertex.z() * matrixData[2][1]) + matrixData[3][1];
        final double z = (vertex.x() * matrixData[0][2]) + (vertex.y() * matrixData[1][2]) + (vertex.z() * matrixData[2][2]) + matrixData[3][2];
        final double w = (vertex.x() * matrixData[0][3]) + (vertex.y() * matrixData[1][3])
                + (vertex.z() * matrixData[2][3]) + matrixData[3][3];
        return new Vector3d(x / w, y / w, z / w);
    }

    public static Point2f vertexToPoint(final Vector3d vertex, final int width, final int height) {
        return new Point2f(vertex.x() * width + width / 2.0F, -vertex.y() * height + height / 2.0F);
    }
}
