package me.cadox8.deud.animations;

import lombok.Getter;
import me.cadox8.deud.entities.Entity;
import me.cadox8.deud.entities.Location;

import java.awt.image.BufferedImage;

public class AnimationBump extends Animation {

    private final float x, y;
    @Getter private float newX, newY;

    public AnimationBump(int speed, BufferedImage frame, Location location) {
        this(speed, new BufferedImage[]{frame}, location);
    }
    public AnimationBump(int speed, BufferedImage frame, Entity entity) {
        this(speed, new BufferedImage[]{frame}, entity.getLocation());
    }
    public AnimationBump(int speed, BufferedImage[] frames, Location location) {
        super(speed, frames);

        this.x = location.getX();
        this.y = location.getY();
    }

    @Override
    public void tick() {
        timer += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();

        if (end) return;
        if (timer > speed) {
            index++;
            timer = 0;
            newX = x;
            newY = y - 66;
            if (index >= frames.length) {
                if (hasEnd()) end = true;
                index = 0;
                newX = x;
                newY = y;
            }
        }
    }
}
