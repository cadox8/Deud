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

package me.cadox8.deud.ui;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.ui.helpers.UIDimension;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Random;

@ToString
public abstract class NysvaUI {

    private final long componentID;

    protected final GameAPI gameAPI;

    protected UIDimension uiDimension;

    protected boolean hovering = false;
    protected boolean enabled = true;

    @Getter @Setter private boolean clickable = true;

    protected Font font = GameAPI.getGameFont();

    @Getter @Setter protected Object extraData = null;
    @Getter @Setter protected boolean reorder = false;

    /**
     * Default NysvaUI constructor
     */
    public NysvaUI(GameAPI gameAPI) {
        componentID = new Random().nextLong();
        this.gameAPI = gameAPI;

        setUiDimension(new UIDimension());
    }

    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract void onClick();

    public void renderUIDimension(Graphics g) {
        g.setColor(Color.RED);
        g.drawRect(uiDimension.getX(), uiDimension.getY(), uiDimension.getWidth(), uiDimension.getHeight());
    }

    public void onMouseMove(MouseEvent e) {
        hovering = getUiDimension().getBounds().contains(e.getX(), e.getY());
    }

    public void onMouseClicked(MouseEvent e) {
        if (hovering) onClick();
    }

    protected void drawImage(Graphics g, BufferedImage image, boolean resize) {
        if (resize) {
            if (hovering) {
                g.drawImage(image, getUiDimension().getX(), getUiDimension().getY(), getUiDimension().getWidth() + 5, getUiDimension().getHeight() + 5, null);
            } else {
                g.drawImage(image, getUiDimension().getX(), getUiDimension().getY(), getUiDimension().getWidth(), getUiDimension().getHeight(),null);
            }
        } else {
            g.drawImage(image, getUiDimension().getX(), getUiDimension().getY(), getUiDimension().getWidth(), getUiDimension().getHeight(),null);
        }
    }

    //
    public UIDimension getUiDimension() {
        return uiDimension == null ? uiDimension = new UIDimension() : uiDimension;
    }
    public void setUiDimension(UIDimension uiDimension) {
        this.uiDimension = uiDimension;
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
    public void resizeFont(int size) {
        customizeFont(0, size);
    }

    public long getComponentID() {
        return componentID;
    }
}
