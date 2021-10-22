/*
 * Copyright (C) AthoneDevs, Inc - All Rights Reserved (Krork Engine)
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * You are not allowed to edit or use fragments of this code for any uses
 * You are allowed to use the Engine as a dependency for your code/game
 *
 * For any question/bug/suggestion, please, mail me at cadox8@gmail.com
 * Written by Cadox8 <cadox8@gmail.com>, 24 October 2018
 *
 */

package es.cadox8.deud.ui.helpers;

import java.awt.*;
import java.awt.image.ColorModel;
import java.beans.ConstructorProperties;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AarinColor {

    public static AarinColor DARK_GRAY = new AarinColor(52, 73, 94);
    public static AarinColor PURPLE = new AarinColor(142, 68, 173);
    public static AarinColor BLUE = new AarinColor(41, 128, 185);
    public static AarinColor GREEN = new AarinColor(39, 174, 96);
    public static AarinColor TURQUOISE = new AarinColor(22, 160, 133);
    public static AarinColor WHITE = new AarinColor(236, 240, 241);
    public static AarinColor BLACK = new AarinColor(0, 0, 0);
    public static AarinColor RED = new AarinColor(192, 57, 43);
    public static AarinColor ORANGE = new AarinColor(211, 84, 0);
    public static AarinColor YELLOW = new AarinColor(241, 196, 15);

    public static AarinColor TRANSPARENT = new AarinColor(0, 0, 0, 0);

    //
    private int value;

    public AarinColor(int r, int g, int b) {
        this(r, g, b, 255);
    }
    @ConstructorProperties({"red", "green", "blue", "alpha"})
    private AarinColor(int r, int g, int b, int a) {
        value = ((a & 0xFF) << 24) | ((r & 0xFF) << 16) | ((g & 0xFF) << 8)  | (b & 0xFF);
        testColorValueRange(r, g, b, a);
    }

    public AarinColor transparent(int alpha) {
        if (alpha < 0 || alpha > 255) throw new IllegalArgumentException("Alpha must be a value between 0 and 255");
        return new AarinColor(getRed(), getGreen(), getGreen(), alpha);
    }

    private static void testColorValueRange(int r, int g, int b, int a) {
        boolean rangeError = false;
        String badComponentString = "";

        if ( a < 0 || a > 255) {
            rangeError = true;
            badComponentString += " Alpha";
        }
        if ( r < 0 || r > 255) {
            rangeError = true;
            badComponentString += " Red";
        }
        if ( g < 0 || g > 255) {
            rangeError = true;
            badComponentString += " Green";
        }
        if ( b < 0 || b > 255) {
            rangeError = true;
            badComponentString += " Blue";
        }
        if (rangeError) throw new IllegalArgumentException("Color parameter outside of expected range:" + badComponentString);
    }

    /**
     * Returns the red component in the range 0-255 in the default sRGB
     * space.
     * @return the red component.
     * @see #getRGB
     */
    public int getRed() {
        return (getRGB() >> 16) & 0xFF;
    }

    /**
     * Returns the green component in the range 0-255 in the default sRGB
     * space.
     * @return the green component.
     * @see #getRGB
     */
    public int getGreen() {
        return (getRGB() >> 8) & 0xFF;
    }

    /**
     * Returns the blue component in the range 0-255 in the default sRGB
     * space.
     * @return the blue component.
     * @see #getRGB
     */
    public int getBlue() {
        return (getRGB()) & 0xFF;
    }

    /**
     * Returns the alpha component in the range 0-255.
     * @return the alpha component.
     * @see #getRGB
     */
    public int getAlpha() {
        return (getRGB() >> 24) & 0xff;
    }

    /**
     * Returns the RGB value representing the color in the default sRGB
     * {@link ColorModel}.
     * (Bits 24-31 are alpha, 16-23 are red, 8-15 are green, 0-7 are
     * blue).
     * @return the RGB value of the color in the default sRGB
     *         {@code ColorModel}.
     * @see ColorModel#getRGBdefault
     * @see #getRed
     * @see #getGreen
     * @see #getBlue
     * @since 1.0
     */
    public int getRGB() {
        return value;
    }

    public Color getColor() {
        return new Color(getRed(), getGreen(), getBlue(), getAlpha());
    }

    public static AarinColor[] allColors() {
        final List<AarinColor> colors = new ArrayList<>(Arrays.asList(AarinColor.DARK_GRAY, AarinColor.BLUE, AarinColor.GREEN, AarinColor.ORANGE, AarinColor.PURPLE, AarinColor.RED, AarinColor.TURQUOISE, AarinColor.YELLOW));
        colors.add(WHITE);
        return colors.toArray(new AarinColor[]{});
    }
}
