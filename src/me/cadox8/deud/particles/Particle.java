package me.cadox8.deud.particles;

import lombok.Getter;
import me.cadox8.deud.animations.Animation;
import me.cadox8.deud.graphics.textures.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

public enum Particle {

    EXPLOSION(Assets.explosion, 1);

    @Getter private final Animation animation;

    Particle(BufferedImage[] images, int velocity) {
        animation = new Animation(velocity * 100, images);
    }

    public void render(Graphics g, int x, int y) {
        g.drawImage(animation.getCurrentFrame(), x, y, null);
    }

    public void tick() {
        animation.tick();
    }

    public Particle withEnd() {
        animation.withEnd(true);
        return this;
    }
}
