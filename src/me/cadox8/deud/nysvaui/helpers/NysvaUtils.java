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

package me.cadox8.deud.nysvaui.helpers;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class NysvaUtils {

    private static Random r = new Random();
    private static NysvaColor lastColor = null;

    public static NysvaColor randomColor(NysvaColor... exclude) {
        final List<NysvaColor> colors =Arrays.asList(NysvaColor.allColors());
        colors.removeAll(Arrays.asList(exclude));
        return randomColor(colors);
    }

    public static NysvaColor randomColor() {
        return randomColor(NysvaColor.allColors());
    }

    public static NysvaColor randomColor(List<NysvaColor> selectedColors) {
        final NysvaColor color = selectedColors.get(r.nextInt(selectedColors.size()));
        if (lastColor != null && color.getRGB() == lastColor.getRGB()) return randomColor(selectedColors);
        return color;
    }
}
