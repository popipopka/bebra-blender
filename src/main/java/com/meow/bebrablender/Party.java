package com.meow.bebrablender;

import com.meow.bebrablender.math.affine.AffineTransform;
import com.meow.bebrablender.math.matrices.Matrix4d;
import com.meow.bebrablender.math.vectors.Vector3d;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;

import java.util.Random;

public class Party {
    private final Color color;
    private final Timeline timeline;
    private final Random rnd;
    private final AffineTransform transform;

    public Party(int keyframe) {
        this.color = Color.GREEN;
        this.timeline = new Timeline(keyframe);
        this.rnd = new Random();
        this.transform = new AffineTransform();
    }

    public void start(Matrix4d modelMatrix) {
        int angleX = rnd.nextInt(0, 360) + 1;
        int angleY = rnd.nextInt(0, 360) + 1;
        int angleZ = rnd.nextInt(0, 360) + 1;
        Vector3d angles = new Vector3d(angleX, angleY, angleZ);

        modelMatrix.mul(transform.rotate(angles));
    }
}