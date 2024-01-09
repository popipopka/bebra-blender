package com.meow.bebrablender.rasterization;

import com.meow.bebrablender.math.points.Point2d;
import com.meow.bebrablender.math.vectors.Vector2d;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

import java.util.Arrays;


public class TriangleRasterizationWithZBuffer {
    /**
     * A mutable vector used to speed up calculations.
     */
    private static final Vector2d p = new Vector2d();

    /**
     * A mutable color used to avoid creating new objects in loops.
     */
    private static final MutableColor color = new MutableColor();

    /**
     * Draws the specified triangle.
     *
     * @param pw   The pixel writer.
     * @param trig The triangle to draw.
     */
    public static void drawTriangle(PixelWriter pw, Triangle trig, ZBuffer zBuffer) {
        drawTriangle(pw, trig.getP1(), trig.getP2(), trig.getP3(),
                trig.getColor1(), trig.getColor2(), trig.getColor3(), zBuffer);
    }

    /**
     * Draws the specified triangle. The order of the vertexPoints is irrelevant.
     *
     * @param pw     The pixel writer.
     * @param point1 The first vertex.
     * @param point2 The second vertex.
     * @param point3 The third vertex.
     * @param color1 The first color.
     * @param color2 The second color.
     * @param color3 The third color.
     */
    public static void drawTriangle(
            final PixelWriter pw,
            final Point2d point1,
            final Point2d point2,
            final Point2d point3,
            final Color color1,
            final Color color2,
            final Color color3,
            ZBuffer zBuffer
    ) {
        Point2d[] vertexPoints = new Point2d[]{point1, point2, point3};
        Arrays.sort(vertexPoints);

        drawTopTriangle(pw, point1, point2, point3, color1, color2, color3, zBuffer);
        drawBottomTriangle(pw, point1, point2, point3, color1, color2, color3, zBuffer);
    }

    /**
     * Draws the top triangle as part of the bigger triangle.
     */
    private static void drawTopTriangle(
            final PixelWriter pw,
            final Point2d point1,
            final Point2d point2,
            final Point2d point3,
            final Color color1,
            final Color color2,
            final Color color3,
            ZBuffer zBuffer
    ) {
        final int x2x1 = (int) (point2.getX() - point1.getX());
        final int x3x1 = (int) (point3.getX() - point1.getX());
        final int y2y1 = (int) (point2.getY() - point1.getY());
        final int y3y1 = (int) (point3.getY() - point1.getY());

        for (int y = (int) point1.getY(); y < (int) point2.getY(); y++) {
            // No need to check if the divisors are null, because the loop will not execute if y1 == y2.
            int l = x2x1 * (y - (int) point1.getY()) / y2y1 + (int) point1.getX(); // Edge 1-2.
            int r = x3x1 * (y - (int) point1.getY()) / y3y1 + (int) point1.getX(); // Edge 1-3.
            if (l > r) { // Swap.
                int tmp = l;
                l = r;
                r = tmp;
            }

            for (int x = l; x <= r; x++) {
                Point2d currPoint = new Point2d(x, y);
                double initialZCoordinate = getInitialZCoordinate(currPoint, point1, point2, point3);
                if (zBuffer.isFrontestPoint(currPoint, initialZCoordinate)) {
                    zBuffer.setBufferValue((int) currPoint.getX(), (int) currPoint.getY(), initialZCoordinate);
                    pw.setColor(x, y, color1);
                }
            }
        }
    }

    /**
     * Draws the bottom triangle as part of the bigger triangle.
     */
    private static void drawBottomTriangle(
            final PixelWriter pw,
            final Point2d point1,
            final Point2d point2,
            final Point2d point3,
            final Color color1,
            final Color color2,
            final Color color3,
            ZBuffer zBuffer
    ) {
        final int x3x2 = (int) (point3.getX() - point2.getX());
        final int x3x1 = (int) (point3.getX() - point1.getX());
        final int y3y2 = (int) (point3.getY() - point2.getY());
        final int y3y1 = (int) (point3.getY() - point1.getY());

        // Draw the separating line and bottom triangle.
        if (y3y2 == 0 || y3y1 == 0) return; // Stop now if the bottom triangle is degenerate (avoids div by zero).
        for (int y = (int) point2.getY(); y <= (int) point3.getY(); y++) {
            int l = x3x2 * (y - (int) point2.getY()) / y3y2 + (int) point2.getX(); // Edge 2-3.
            int r = x3x1 * (y - (int) point1.getY()) / y3y1 + (int) point1.getX(); // Edge 1-3.
            if (l > r) {
                int tmp = l;
                l = r;
                r = tmp;
            }
            for (int x = l; x <= r; x++) {
//                final int colorBits = interpolateColor(x, y, v1, c1, v2, c2, v3, c3, area);
//                pw.setArgb(x, y, colorBits);
                Point2d currPoint = new Point2d(x, y);
                double initialZCoordinate = getInitialZCoordinate(currPoint, point1, point2, point3);
                if (zBuffer.isFrontestPoint(currPoint, initialZCoordinate)) {
                    zBuffer.setBufferValue((int) currPoint.getX(), (int) currPoint.getY(), initialZCoordinate);
                    pw.setColor(x, y, color1);
                }
            }
        }
    }

    /**
     * Method that calculates z-coordinate of input point in the camera coordinates system
     *
     * @param currPoint point to get z-coordinate from triangle
     * @param p1        the first point of the triangle
     * @param p2        the second point of the triangle
     * @param p3        the third point of the triangle
     * @return z-coordinate of input point in the camera coordinates system
     */
    public static double getInitialZCoordinate(Point2d currPoint, Point2d p1, Point2d p2, Point2d p3) {
        double bettaBarycentric =
                ((p2.getY() - p3.getY()) * (p1.getX() - p3.getX()) - (p2.getX() - p3.getX()) * (p1.getY()) - p3.getY())
                        / (currPoint.getY() * (p1.getX() - p3.getX()) - (p1.getY() - p3.getY()) * (currPoint.getX() - p3.getX()));
        double alphaBarycentric =
                (currPoint.getX() - p3.getX() - bettaBarycentric * (p2.getX() - p3.getX())) / (p1.getX() - p3.getX());
        double gammaBarycentric = 1 - alphaBarycentric - bettaBarycentric;

        double zCoordinate =
                alphaBarycentric * p1.getDepth()
                        + bettaBarycentric * p2.getDepth()
                        + gammaBarycentric * p3.getDepth();

        return zCoordinate;
    }

    /**
     * Interpolates the color for the given coordinate.
     *
     * @return The interpolated color bits in the ARGB format.
     */
    private static int interpolateColor(
            final int x, final int y,
            final Vector2d v1, final Color c1,
            final Vector2d v2, final Color c2,
            final Vector2d v3, final Color c3,
            final double area
    ) {
        p.setX(x);
        p.setY(y);

        final double w1 = Math.abs(v2.to(p).crossMagnitude(v2.to(v3))) / area;
        final double w2 = Math.abs(v1.to(p).crossMagnitude(v1.to(v3))) / area;
        final double w3 = Math.abs(v1.to(p).crossMagnitude(v1.to(v2))) / area;

        final double red = clamp((float) (w1 * c1.getRed() + w2 * c2.getRed() + w3 * c3.getRed()));
        final double green = clamp((float) (w1 * c1.getGreen() + w2 * c2.getGreen() + w3 * c3.getGreen()));
        final double blue = clamp((float) (w1 * c1.getBlue() + w2 * c2.getBlue() + w3 * c3.getBlue()));

        color.set(red, green, blue);
        return color.toArgb();
    }

    /**
     * Clamps the given double value between 0 and 1.
     *
     * @param v The value to clamp.
     * @return The clamped value.
     */
    private static double clamp(double v) {
        if (v < (float) 0.0) return (float) 0.0;
        if (v > (float) 1.0) return (float) 1.0;
        return v;
    }
}
