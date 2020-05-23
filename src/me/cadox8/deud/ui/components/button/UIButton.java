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

package me.cadox8.deud.ui.components.button;

import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.ui.ClickListener;
import me.cadox8.deud.ui.components.base.UIBlock;
import me.cadox8.deud.ui.components.text.UIText;
import me.cadox8.deud.ui.helpers.NysvaColor;

import java.awt.*;

public class UIButton extends UIText {

    private ClickListener clicker;

    private final UIBlock block = new UIBlock(gameAPI, NysvaColor.YELLOW);

    /**
     * Generates a Button Object
     */
    public UIButton(GameAPI api, ClickListener clicker) {
        super(api, "");
        this.clicker = clicker;
        setTextColor(NysvaColor.DARK_GRAY);
    }

    @Override
    public void tick() {
        block.setUiDimension(getUiDimension());
    }

    @Override
    public void render(Graphics g) {
        g.setFont(GameAPI.getGameFont());
        block.render(g);
        super.render(g);
    }

    @Override
    public void onClick() {
        clicker.onClick();
    }

    public UIBlock getBlock() {
        return block;
    }
}
