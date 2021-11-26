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

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class AarinUtils {

    private static final Random r = new Random();
    private static AarinColor lastColor = null;

    public static AarinColor randomColor(AarinColor... exclude) {
        final List<AarinColor> colors = Arrays.asList(AarinColor.allColors());
        colors.removeAll(Arrays.asList(exclude));
        return randomColor(colors);
    }

    public static AarinColor randomColor() {
        return randomColor(AarinColor.allColors());
    }

    public static AarinColor randomColor(List<AarinColor> selectedColors) {
        final AarinColor color = selectedColors.get(r.nextInt(selectedColors.size()));
        if (lastColor != null && color.getRGB() == lastColor.getRGB()) return randomColor(selectedColors);
        lastColor = color;
        return color;
    }
}
