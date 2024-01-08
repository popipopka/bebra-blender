package com.meow.bebrablender.view;

import com.meow.bebrablender.math.vectors.Vector2f;
import com.meow.bebrablender.model.Model;
import com.meow.bebrablender.rasterization.PolygonView;
import com.meow.bebrablender.rasterization.Triangle;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

import java.util.Arrays;

/**
 * Canvas Access Object - Single point of access to drawing
 */
public class CAO {
    Canvas canvas;

    public CAO(Canvas canvas) {
        this.canvas = canvas;
    }

    /**
     * method that draws the polygon (don't includes the Z-buffer algorithm)
     * @param polygonView polygonView to draw
     */
    private void drawPolygonView(PolygonView polygonView) {

    }

    public static void drawTriangle(PixelWriter pw, Triangle trig) {
        drawTriangle(pw, trig.v1, trig.v2, trig.v3, trig.c1, trig.c2, trig.c3);
    }

    /**
     * Draws the specified triangle. The order of the vertices is irrelevant.
     *
     * @param pw The pixel writer.
     * @param v1 The first vertex.
     * @param v2 The second vertex.
     * @param v3 The third vertex.
     * @param c1 The first color.
     * @param c2 The second color.
     * @param c3 The third color.
     */
    public static void drawTriangle(
            final PixelWriter pw,
            final Vector2f v1,
            final Vector2f v2,
            final Vector2f v3,
            final Color c1,
            final Color c2,
            final Color c3
    ) {
        final var vertices = new Vector2f[]{v1, v2, v3};
        Arrays.sort(vertices, COMPARATOR);
        final int x1 = (int) vertices[0].x();
        final int x2 = (int) vertices[1].x();
        final int x3 = (int) vertices[2].x();
        final int y1 = (int) vertices[0].y();
        final int y2 = (int) vertices[1].y();
        final int y3 = (int) vertices[2].y();

        // Double the area of the triangle. Used to calculate the barycentric coordinates later.
        final double area = Math.abs(v1.to(v2).crossMagnitude(v1.to(v3)));
        drawTopTriangle(pw, x1, y1, x2, y2, x3, y3, v1, c1, v2, c2, v3, c3, area);
        drawBottomTriangle(pw, x1, y1, x2, y2, x3, y3, v1, c1, v2, c2, v3, c3, area);
    }

    /**
     * Draws the top triangle as part of the bigger triangle.
     */
    private static void drawTopTriangle(
            final PixelWriter pw,
            final int x1, final int y1,
            final int x2, final int y2,
            final int x3, final int y3,
            final Vector2f v1, final Color c1,
            final Vector2f v2, final Color c2,
            final Vector2f v3, final Color c3,
            final double area
    ) {
        final int x2x1 = x2 - x1;
        final int x3x1 = x3 - x1;
        final int y2y1 = y2 - y1;
        final int y3y1 = y3 - y1;

        for (int y = y1; y < y2; y++) {
            // No need to check if the divisors are null, because the loop will not execute if y1 == y2.
            int l = x2x1 * (y - y1) / y2y1 + x1; // Edge 1-2.
            int r = x3x1 * (y - y1) / y3y1 + x1; // Edge 1-3.
            if (l > r) { // Swap.
                int tmp = l;
                l = r;
                r = tmp;
            }
            for (int x = l; x <= r; x++) {
                final int colorBits = interpolateColor(x, y, v1, c1, v2, c2, v3, c3, area);
                pw.setArgb(x, y, colorBits);
            }
        }
    }

    /**
     * Draws the bottom triangle as part of the bigger triangle.
     */
    private static void drawBottomTriangle(
            final PixelWriter pw,
            final int x1, final int y1,
            final int x2, final int y2,
            final int x3, final int y3,
            final Vector2f v1, final Color c1,
            final Vector2f v2, final Color c2,
            final Vector2f v3, final Color c3,
            final double area
    ) {
        final int x3x2 = x3 - x2;
        final int x3x1 = x3 - x1;
        final int y3y2 = y3 - y2;
        final int y3y1 = y3 - y1;

        // Draw the separating line and bottom triangle.
        if (y3y2 == 0 || y3y1 == 0) return; // Stop now if the bottom triangle is degenerate (avoids div by zero).
        for (int y = y2; y <= y3; y++) {
            int l = x3x2 * (y - y2) / y3y2 + x2; // Edge 2-3.
            int r = x3x1 * (y - y1) / y3y1 + x1; // Edge 1-3.
            if (l > r) {
                int tmp = l;
                l = r;
                r = tmp;
            }
            for (int x = l; x <= r; x++) {
                final int colorBits = interpolateColor(x, y, v1, c1, v2, c2, v3, c3, area);
                pw.setArgb(x, y, colorBits);
            }
        }
    }

    /**
     * method that draws the model (includes the Z-buffer algorithm)
     *
     * @param model model to draw
     */
    public void drawModel(Model model) {

    }
}
