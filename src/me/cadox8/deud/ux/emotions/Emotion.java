package me.cadox8.deud.ux.emotions;

import lombok.Getter;
import me.cadox8.deud.animations.AnimationBump;
import me.cadox8.deud.entities.Entity;
import me.cadox8.deud.entities.Location;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Emotion {

    @Getter private final AnimationBump anim;

    public Emotion(BufferedImage image, Entity entity) {
        this(image, entity.getLocation());
    }
    public Emotion(BufferedImage image, Location location) {
        anim = new AnimationBump(1, image, location);
    }

    public void render(Graphics g) {
        g.drawImage(anim.getCurrentFrame(), (int)anim.getNewX(), (int)anim.getNewY(), null);
    }

    public void tick() {
        anim.tick();
    }
}
