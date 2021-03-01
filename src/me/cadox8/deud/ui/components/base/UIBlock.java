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

package me.cadox8.deud.ui.components.base;

import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.ui.NysvaUI;
import me.cadox8.deud.ui.helpers.NysvaColor;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class UIBlock extends NysvaUI {

    private NysvaColor background;

    private boolean rounded = false;
    private int roundRadius = 35;

    public UIBlock(GameAPI api) {
        this(api, NysvaColor.DARK_GRAY);
    }
    public UIBlock(GameAPI api, NysvaColor background) {
        super(api);
        this.background = background;
    }

    @Override
    public void tick() {}

    @Override
    public void render(Graphics g) {
        final Graphics2D g2 = (Graphics2D) g;
        g2.setColor(background.getColor());
        final Rectangle r = getUiDimension().getBounds();

        if (isRounded()) {
            final RoundRectangle2D r2 = new RoundRectangle2D.Double(r.getX(), r.getY(), r.getWidth(), r.getHeight(), roundRadius, roundRadius);
            g2.draw(r2);
            g2.fill(r2);
        } else {
            g2.draw(r);
            g2.fill(r);
        }
    }

    @Override
    public void onClick() {}

    public void transparentBackground(int alpha) {
        setBackground(background.transparent(alpha));
    }

    public NysvaColor getBackground() {
        return background;
    }

    public void setBackground(NysvaColor background) {
        setBackground(background, 255);
    }
    public void setBackground(NysvaColor background, int alpha) {
        this.background = background.transparent(alpha);
    }

    public boolean isRounded() {
        return rounded;
    }

    public UIBlock setRounded(boolean rounded) {
        this.rounded = rounded;
        return this;
    }

    public int getRoundRadius() {
        return roundRadius;
    }

    public void setRoundRadius(int roundRadius) {
        if (roundRadius < 0 || roundRadius > 100) throw new IllegalArgumentException("The round radius must be between 0 and 100 (" + roundRadius + ")");
        setRounded(true);
        this.roundRadius = roundRadius;
    }
}
