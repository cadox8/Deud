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

package me.cadox8.deud.nysvaui.components.images;

import lombok.Setter;
import lombok.ToString;
import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.nysvaui.ClickListener;

import java.awt.*;
import java.awt.image.BufferedImage;

@ToString
public class UIImageButton extends UIImage {

    private ClickListener clicker;

    @Setter private BufferedImage pressedImage;
    private boolean pressed = false;

    public UIImageButton(GameAPI api, BufferedImage image, ClickListener clicker) {
        super(api, image);

        this.clicker = clicker;
    }

    @Override
    public void onClick() {
        clicker.onClick();
        if (pressedImage != null) pressed = true;
    }

    @Override
    public void render(Graphics g) {
        if (!pressed) {
            drawImage(g, getImage(), resize);
        } else {
            drawImage(g, pressedImage, false);
        }
    }
}
