package me.cadox8.deud.particles;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.cadox8.deud.gfx.Animation;
import me.cadox8.deud.gfx.textures.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

public enum Particle {

    EXPLOSION(Assets.explosion, 100);

    @Getter private final Animation animation;

    Particle(BufferedImage[] images, int velocity) {
        animation = new Animation(velocity, images);
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
