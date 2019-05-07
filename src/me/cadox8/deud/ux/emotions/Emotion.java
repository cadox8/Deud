package me.cadox8.deud.ux.emotions;

import me.cadox8.deud.animations.AnimationBump;
import me.cadox8.deud.entities.Entity;
import me.cadox8.deud.gfx.textures.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Emotion {

    private final AnimationBump anim;

    public Emotion(BufferedImage image, Entity entity) {
        anim = new AnimationBump(1, Assets.explosion, entity);
    }

    public void render(Graphics g) {
        g.drawImage(anim.getCurrentFrame(), (int)anim.getNewX(), (int)anim.getNewY(), null);
    }

    public void tick() {
        anim.tick();
    }
}
