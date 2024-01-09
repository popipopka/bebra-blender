package com.meow.bebrablender.render_engine;
import com.meow.bebrablender.math.matrices.Matrix4d;
import com.meow.bebrablender.math.vectors.Vector3d;

public class Camera {
    private Vector3d position;
    private Vector3d target;
    private float fov;
    private float aspectRatio;
    private float nearPlane;
    private float farPlane;

    public Camera(
            final Vector3d position,
            final Vector3d target,
            final float fov,
            final float aspectRatio,
            final float nearPlane,
            final float farPlane) {
        this.position = position;
        this.target = target;
        this.fov = fov;
        this.aspectRatio = aspectRatio;
        this.nearPlane = nearPlane;
        this.farPlane = farPlane;
    }

    public void setPosition(final Vector3d position) {
        this.position = position;
    }

    public void setTarget(final Vector3d target) {
        this.target = target;
    }

    public void setAspectRatio(final float aspectRatio) {
        this.aspectRatio = aspectRatio;
    }

    public Vector3d getPosition() {
        return position;
    }

    public Vector3d getTarget() {
        return target;
    }

    public void movePosition(final Vector3d translation) {
        this.position.add(translation);
    }

    public void moveTarget(final Vector3d translation) {
        this.target.add(target);
    }

    public Matrix4d getViewMatrix() {
        return GraphicConveyor.view(position, target);
    }

    public Matrix4d getProjectionMatrix() {
        return GraphicConveyor.perspective(fov, aspectRatio, nearPlane, farPlane);
    }
}