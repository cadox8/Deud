package me.cadox8.deud.gfx;

import lombok.Getter;
import lombok.Setter;

import java.awt.image.BufferedImage;

public class Animation {

    @Getter @Setter private int speed, index;
    private long lastTime, timer;
    private BufferedImage[] frames;

    private boolean withEnd;
    @Getter private boolean end;

    public Animation(int speed, BufferedImage[] frames) {
        this.speed = speed;
        this.frames = frames;
        index = 0;
        timer = 0;
        lastTime = System.currentTimeMillis();
        withEnd(false);
        end = false;
    }

    public void tick() {
        timer += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();

        if (timer > speed) {
            index++;
            timer = 0;
            if (index >= frames.length) {
                if (hasEnd()) end = true;
                index = 0;
            }
        }
    }

    public BufferedImage getCurrentFrame() {
        return frames[index];
    }

    public void withEnd(boolean end) {
        withEnd = end;
    }
    public boolean hasEnd() {
        return withEnd;
    }
}
