package com.meow.bebrablender;

import com.meow.bebrablender.math.affine.AffineTransform;
import com.meow.bebrablender.math.matrices.Matrix4d;
import com.meow.bebrablender.math.vectors.Vector3d;
import com.meow.bebrablender.model.Model;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.Random;

public class Party {
    private final Timeline timeline;
    private final KeyFrame movement = new KeyFrame(
            Duration.millis(10), event -> {
                move();
    });

    private final KeyFrame changeColor = new KeyFrame(
            Duration.millis(100), event -> {
                changeColor();
    });
    private final Random rnd;
    private final AffineTransform transform;

    private final Matrix4d modelMatrix;

    private Model model;

    public Party(Matrix4d modelMatrix, Model model) {
        this.modelMatrix = modelMatrix;
        this.model = model;

        this.timeline = new Timeline(movement, changeColor);
        timeline.setCycleCount(Animation.INDEFINITE);

        this.rnd = new Random();
        this.transform = new AffineTransform();
    }

    public void start() {
        timeline.play();
    }

    public void stop() {
        timeline.stop();
    }

    private void move() {
        int angleX = rnd.nextInt(0, 360) + 1;
        int angleY = rnd.nextInt(0, 360) + 1;
        int angleZ = rnd.nextInt(0, 360) + 1;
        Vector3d angles = new Vector3d(angleX, angleY, angleZ);

        modelMatrix.mul(transform.rotate(angles));
    }

    private void changeColor() {
        float r = rnd.nextFloat(0, 1);
        float g = rnd.nextFloat(0, 1);
        float b = rnd.nextFloat(0, 1);

        this.model.setColor(new Color(r, g, b, 1));
    }

    private Color interpolateColor(Color c1, Color c2) {
        double r1 = c1.getRed();
        double g1 = c1.getGreen();
        double b1 = c1.getBlue();

        double r2 = c2.getRed();
        double g2 = c2.getGreen();
        double b2 = c2.getBlue();

        double ratio = 0.1;

        double r = cosineInterpolate(r1, r2, ratio);
        double g = cosineInterpolate(g1, g2, ratio);
        double b = cosineInterpolate(b1, b2, ratio);

        return new Color(r, g, b, 1.0);
    }

    private double cosineInterpolate(double a, double b, double ratio) {
        double f = (1 - Math.cos(ratio * Math.PI)) * 0.5;
        return a * (1 - f) + b * ratio;
    }
}