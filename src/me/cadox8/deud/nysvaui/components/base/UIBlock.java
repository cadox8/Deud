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

package me.cadox8.deud.nysvaui.components.base;

import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.nysvaui.NysvaUI;
import me.cadox8.deud.nysvaui.helpers.NysvaColor;
import me.cadox8.deud.nysvaui.helpers.UIDimension;

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
    public void tick() {
        if (!components.isEmpty()) components.forEach(NysvaUI::tick);
    }

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
        if (!components.isEmpty()) components.forEach(c -> c.render(g));
    }

    @Override
    public void onClick() {
        if (!components.isEmpty()) components.forEach(NysvaUI::onClick);
    }

    public void transparentBackground(int alpha) {
        setBackground(background.transparent(alpha));
    }

    public void addUIComponent(NysvaUI component) {
        final UIDimension childComponent = component.getUiDimension();
        final UIDimension rd = new UIDimension(childComponent.getRefX() + getUiDimension().getX() + 5, 5 + childComponent.getRefY() + getUiDimension().getY(), childComponent.getWidth(), childComponent.getHeight());
        rd.setRefX(component.getUiDimension().getRefX());
        rd.setRefY(component.getUiDimension().getRefY());
        component.setUiDimension(rd);
        component.setMaxWidth(getUiDimension().getMaxWidth());
        components.add(component);
        component.setParent(this);
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

    public void setRounded(boolean rounded) {
        this.rounded = rounded;
    }

    public int getRoundRadius() {
        return roundRadius;
    }

    public void setRoundRadius(int roundRadius) {
        if (roundRadius < 0 || roundRadius > 100) throw new IllegalArgumentException("The round radius must be between 0 and 100 (" + roundRadius + ")");
        this.roundRadius = roundRadius;
    }
}
