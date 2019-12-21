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

import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.nysvaui.ClickListener;

import java.awt.image.BufferedImage;

public class UIImageButton extends UIImage {

    private ClickListener clicker;

    public UIImageButton(GameAPI api, BufferedImage image, ClickListener clicker) {
        super(api, image);

        this.clicker = clicker;
    }

    @Override
    public void onClick() {
        clicker.onClick();
    }
}
