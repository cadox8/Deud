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

package me.cadox8.deud.nysvaui.components.text;

import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.nysvaui.NysvaUI;
import me.cadox8.deud.nysvaui.helpers.NysvaColor;

import java.awt.*;

public class UIText extends NysvaUI {

    protected NysvaColor textColor = NysvaColor.WHITE;
    protected String text;

    /**
     * Generates a Text Object
     */
    public UIText(GameAPI api, String text) {
        super(api);

        this.text = text;
    }

    @Override
    public void tick() {}

    @Override
    public void render(Graphics g) {
        drawString(g);
    }

    @Override
    public void onClick() {}

    private void drawString(Graphics g) {
        g.setColor(textColor.getColor());
        g.setFont(getFont());
        g.drawString(text, getUIDimension().getX() + 5, getUIDimension().getY() + g.getFont().getSize());
    }

    //
    public NysvaColor getTextColor() {
        return textColor;
    }

    public void setTextColor(NysvaColor textColor) {
        this.textColor = textColor;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
