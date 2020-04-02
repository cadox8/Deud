package me.cadox8.deud.particles;

import lombok.Getter;
import lombok.Setter;
import me.cadox8.deud.animations.Animation;
import me.cadox8.deud.graphics.textures.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

public enum Particle {

    EXPLOSION(Assets.explosion, 1),
    EXCLAMATION(Assets.exclamation, 2);

    @Getter private final Animation animation;

    @Getter private int priority, x, y;

    Particle(BufferedImage image, int velocity) {
        this(new BufferedImage[]{image}, velocity);
    }
    Particle(BufferedImage[] images, int velocity) {
        animation = new Animation(velocity * 100, images);

        this.priority = 0;
        this.x = 0;
        this.y = 0;
    }

    public void render(Graphics g) {
        g.drawImage(animation.getCurrentFrame(), x, y, null);
    }

    public void tick() {
        animation.tick();
    }

    public Particle withEnd() {
        animation.withEnd(true);
        return this;
    }

    public Particle setPriority(int priority) {
        this.priority = priority;
        return this;
    }

    public Particle setPosition(int x, int y) {
        this.x = x;
        this.y = y;
        return this;
    }
}
