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

package me.cadox8.deud.nysvaui;

import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.nysvaui.helpers.UIDimension;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class NysvaUI {

    private final long componentID;

    protected final GameAPI api;

    protected UIDimension UIDimension;

    private boolean draggable = false;
    protected boolean hovering = false;
    protected boolean enabled = true;

    protected Font font = GameAPI.getGameFont();

    protected int maxWidth = -1;

    private NysvaUI parent;
    protected List<NysvaUI> components;
    protected int marginX, marginY;

    /**
     * Default NysvaUI constructor
     */
    public NysvaUI(GameAPI api) {
        componentID = new Random().nextLong();
        this.api = api;

        components = new ArrayList<>();
        setUIDimension(new UIDimension());

        marginX = 5;
        marginY = 10;
    }

    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract void onClick();

    public void onMouseMove(MouseEvent e) {
        hovering = getUIDimension().getBounds().contains(e.getX(), e.getY());
    }

    public void onMouseDragged(MouseEvent e) {
        if (hovering && isDraggable()) {
            getUIDimension().setX(getUIDimension().getX() + (e.getX() - getUIDimension().getX()));
            getUIDimension().setY(getUIDimension().getY() + (e.getY() - getUIDimension().getY()));
        }

        // Move all components inside
        if (!components.isEmpty()) components.forEach(c -> {
            c.getUIDimension().setX(c.getParent().getUIDimension().getX() + c.getUIDimension().getRefX());
            c.getUIDimension().setY(c.getParent().getUIDimension().getY() + c.getUIDimension().getRefY());
        });
    }

    public void onMouseClicked(MouseEvent e) {
        if (hovering) onClick();
    }

    protected void drawImage(Graphics g, BufferedImage image, boolean resize) {
        if (resize) {
            if (hovering) {
                g.drawImage(image, getUIDimension().getX(), getUIDimension().getY(), getUIDimension().getWidth() + 5, getUIDimension().getHeight() + 5, null);
            } else {
                g.drawImage(image, getUIDimension().getX(), getUIDimension().getY(), getUIDimension().getWidth(), getUIDimension().getHeight(),null);
            }
        } else {
            g.drawImage(image, getUIDimension().getX(), getUIDimension().getY(), getUIDimension().getWidth(), getUIDimension().getHeight(),null);
        }
    }

    //
    public UIDimension getUIDimension() {
        return UIDimension == null ? UIDimension = new UIDimension() : UIDimension;
    }
    public void setUIDimension(UIDimension UIDimension) {
        this.UIDimension = UIDimension;
        setMaxWidth(UIDimension.getMaxWidth());
    }

    public void setMaxWidth(int maxWidth) {
        this.maxWidth = maxWidth;
    }

    public boolean isDraggable() {
        return draggable;
    }

    public void setDraggable(boolean draggable) {
        this.draggable = draggable;
    }

    public boolean isHovering() {
        return this.hovering;
    }

    public boolean isEnabled() {
        return this.enabled;
    }
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Font getFont() {
        return this.font;
    }
    public void setFont(Font font) {
        this.font = font;
    }
    public void newFont(String font, int style, int size) {
        setFont(new Font(font, style, size));
    }
    public void customizeFont(int style, int size) {
        setFont(getFont().deriveFont(style, size));
    }

    public NysvaUI getParent() {
        return parent;
    }

    public void setParent(NysvaUI parent) {
        this.parent = parent;
    }

    public long getComponentID() {
        return componentID;
    }
}
