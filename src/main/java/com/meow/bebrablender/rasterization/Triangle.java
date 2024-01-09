package com.meow.bebrablender.rasterization;

import javafx.scene.paint.Color;
import com.meow.bebrablender.math.points.Point2d;

import static com.meow.bebrablender.rasterization.RasterizationConstants.*;

public class Triangle {
    private final Point2d p1;
    private final Point2d p2;
    private final Point2d p3;
    private Color color1 = DEFAULT_COLOR_1;
    private Color color2 = DEFAULT_COLOR_2;
    private Color color3 = DEFAULT_COLOR_3;

    public Triangle(Point2d p1, Point2d p2, Point2d p3) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
    }

    public Triangle(Point2d p1, Point2d p2, Point2d p3, Color c1, Color c2, Color c3) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.color1 = c1;
        this.color2 = c2;
        this.color3 = c3;
    }

    public Point2d getP1() {
        return p1;
    }

    public Point2d getP2() {
        return p2;
    }

    public Point2d getP3() {
        return p3;
    }

    public Color getColor1() {
        return color1;
    }

    public Color getColor2() {
        return color2;
    }

    public Color getColor3() {
        return color3;
    }
}
