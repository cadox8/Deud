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

import java.awt.*;

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

    //
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getRefX() {
        return refX;
    }

    public void setRefX(int refX) {
        this.refX = refX;
    }

    public int getRefY() {
        return refY;
    }

    public void setRefY(int refY) {
        this.refY = refY;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "UIDimension{" +
                "x=" + x +
                ", y=" + y +
                ", width=" + width +
                ", height=" + height +
                ", refX=" + refX +
                ", refY=" + refY +
                '}';
    }
}
