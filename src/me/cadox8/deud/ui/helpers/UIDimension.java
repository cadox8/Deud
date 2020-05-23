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

package me.cadox8.deud.ui.helpers;

import lombok.Data;
import lombok.ToString;

import java.awt.*;

@Data
@ToString
public class UIDimension {

    private int x = 0, y = 0, width = 0, height = 0;

    private int refX = 0, refY = 0;

    public UIDimension() {}

    public UIDimension(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public int getMaxWidth() {
        return x + width;
    }

    public int getMaxHeight() {
        return y + height;
    }

    public UIDimension add(int x, int y, int width, int height) {
        this.x += x;
        this.y += y;
        this.width += width;
        this.height += height;
        return this;
    }

    public UIDimension set(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        return this;
    }

    public UIDimension addX(int x) {
        setX(getX() + x);
        return this;
    }
    public UIDimension addY(int y) {
        setY(getY() + y);
        return this;
    }
    public UIDimension setWidth(int width) {
        this.width = width;
        return this;
    }

    public static UIDimension fromUIDimension(UIDimension uiDimension) {
        return new UIDimension(uiDimension.getX(), uiDimension.getY(), uiDimension.getWidth(), uiDimension.getHeight());
    }
}
