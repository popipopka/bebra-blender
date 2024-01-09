package com.meow.bebrablender.rasterization;

/**
 * A color with mutable red, green, and blue values.
 */
public class MutableColor {
    /**
     * The red component of the color.
     */
    public double red;

    /**
     * The green component of the color.
     */
    public double green;

    /**
     * The blue component of the color.
     */
    public double blue;

    /**
     * Sets the new values for the red, green, and blue components.
     */
    public void set(double r, double g, double b) {
        this.red = r;
        this.green = g;
        this.blue = b;
    }

    /**
     * Converts this color to a 32-bit integer containing this color in the ARGB format.
     */
    public int toArgb() {
        int a = 255 << 24;
        int r = (int) (red * 255) << 16;
        int g = (int) (green * 255) << 8;
        int b = (int) (blue * 255);
        return a | r | g | b;
    }
}
