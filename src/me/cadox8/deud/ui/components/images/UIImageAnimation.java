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

import lombok.Getter;
import lombok.Setter;
import me.cadox8.deud.animations.Animation;
import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.nysvaui.NysvaUI;

import java.awt.*;

public class UIImageAnimation extends NysvaUI {

    private Animation anim;

    @Getter @Setter protected boolean resize = true;

    /**
     * Generates a Image Animation Object
     *
     * @see Animation
     *
     * @param api
     * @param anim The animation to be shown
     */
    public UIImageAnimation(GameAPI api, Animation anim) {
        super(api);
        this.anim = anim;
    }

    @Override
    public void tick() {
        anim.tick();
    }

    @Override
    public void render(Graphics g) {
        drawImage(g, anim.getCurrentFrame(), resize);
    }

    @Override
    public void onClick() {

    }
}
