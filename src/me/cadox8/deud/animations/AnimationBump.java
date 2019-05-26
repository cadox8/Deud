package me.cadox8.deud.animations;

import lombok.Getter;
import me.cadox8.deud.entities.Entity;

import java.awt.image.BufferedImage;

public class AnimationBump extends Animation {

    private final float x, y;
    @Getter private float newX, newY;

    public AnimationBump(int speed, BufferedImage frame, Entity entity) {
        this(speed, new BufferedImage[]{frame}, entity);
    }
    public AnimationBump(int speed, BufferedImage[] frames, Entity entity) {
        super(speed, frames);

        this.x = entity.getX();
        this.y = entity.getY() - (float)entity.getBounds().getHeight();
    }

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
