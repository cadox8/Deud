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

package me.cadox8.deud.ui.nysvaui.components.base;

import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.ui.nysvaui.helpers.NysvaColor;
import me.cadox8.deud.ui.nysvaui.helpers.NysvaUtils;

import java.util.Arrays;
import java.util.List;

public class UIRainbowBlock extends UIBlock {

    private double speed;
    private long lastTime, timer = 0;

    private List<NysvaColor> colors;
    private int alpha = 255;

    public UIRainbowBlock(GameAPI api, double speedInSeconds) {
        super(api);
        this.speed = 1000 * speedInSeconds;
        colors = Arrays.asList(NysvaColor.allColors());
    }

    @Override
    public void tick() {
        timer += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();

        if (timer > speed) {
            timer = 0;
            setBackground(finalColor(NysvaUtils.randomColor(colors)));
        }
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void transparentBackground(int alpha) {
        this.alpha = alpha;
        setBackground(getBackground().transparent(alpha));
    }

    public void rainbowColors(NysvaColor... colors) {
        this.colors.addAll(Arrays.asList(colors));
    }

    private NysvaColor finalColor(NysvaColor color) {
        return color.transparent(alpha);
    }
}
