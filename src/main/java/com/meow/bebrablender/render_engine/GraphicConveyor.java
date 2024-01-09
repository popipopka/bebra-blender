package com.meow.bebrablender.render_engine;

import com.meow.bebrablender.math.affine.AffineTransform;
import com.meow.bebrablender.math.matrices.Matrix4d;
import com.meow.bebrablender.math.points.Point2d;
import com.meow.bebrablender.math.vectors.Vector3d;

public class GraphicConveyor {
    private final AffineTransform affine;

    public GraphicConveyor() {
        this.affine = new AffineTransform();
    }

    public Matrix4d rotateScaleTranslate(Vector3d scale, Vector3d rotate, Vector3d translate) {
        return this.affine.trs(scale, rotate, translate);
    }

    public Matrix4d getModelMatrix() {
        return affine.getAffine();
    }

    protected static Matrix4d view(Vector3d eye, Vector3d target) {
        return lookAt(eye, target, new Vector3d(0, 1.0, 0));
    }

    private static Matrix4d lookAt(Vector3d eye, Vector3d target, Vector3d up) {
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

    protected static Matrix4d perspective(
            final double fov,
            final double aspectRatio,
            final double nearPlane,
            final double farPlane) {
        Matrix4d perspective = new Matrix4d();
        double[][] perspectiveData = perspective.data();

        double tangentMinusOnDegree = 1.0 / (Math.tan(fov * 0.5));

        perspectiveData[0][0] = tangentMinusOnDegree / aspectRatio;
        perspectiveData[1][1] = tangentMinusOnDegree;
        perspectiveData[2][2] = (farPlane + nearPlane) / (farPlane - nearPlane);
        perspectiveData[2][3] = 1.0;
        perspectiveData[3][2] = 2 * (nearPlane * farPlane) / (nearPlane - farPlane);
        
        perspective.setData(perspectiveData);
        return perspective;
    }

    public static Point2d vertexToPoint(final Vector3d vertex, final int width, final int height) {
        return new Point2d(vertex.x() * width + width / 2.0, -vertex.y() * height + height / 2.0);
    }
}
