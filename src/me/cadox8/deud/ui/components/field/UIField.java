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

package me.cadox8.deud.ui.components.field;

import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.graphics.fonts.Text;
import me.cadox8.deud.ui.ClickListener;
import me.cadox8.deud.ui.NysvaUI;
import me.cadox8.deud.ui.components.base.UIBlock;
import me.cadox8.deud.ui.helpers.NysvaColor;

import java.awt.*;

public class UIField extends NysvaUI {

    private UIBlock base;

    private final ClickListener clicker;

    private String text = "";
    private int maxCharacters;

    public UIField(GameAPI gameAPI) {
        super(gameAPI);

        base = new UIBlock(gameAPI, NysvaColor.WHITE);

        clicker = () -> {
            this.gameAPI.getKeyManager().setWritingTo(this);
        };
    }

    @Override
    public void tick() {
        base.setUiDimension(getUiDimension());
        if (gameAPI.getKeyManager().getWritingTo() == null) return;
    }

    @Override
    public void render(Graphics g) {
        base.render(g);
        Text.drawString(g, text, getUiDimension().getX() + 3, getUiDimension().getY() + getUiDimension().getHeight() / 2 + 6, false, Color.BLACK, 1);
    }

    @Override
    public void onClick() {
        clicker.onClick();
    }

    private boolean canWrite(Graphics g, String text) {
        return g.getFontMetrics(g.getFont()).stringWidth(text) > getUiDimension().getWidth() || text.length() < maxCharacters;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getMaxCharacters() {
        return maxCharacters;
    }

    public void setMaxCharacters(int maxCharacters) {
        this.maxCharacters = maxCharacters;
    }
}
