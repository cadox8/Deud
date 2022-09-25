package es.cadox8.deud.animations;

import lombok.Getter;
import lombok.Setter;

import java.awt.image.BufferedImage;

public class Animation {

    @Getter @Setter protected int speed, index;
    protected long lastTime, timer;
    protected BufferedImage[] frames;

    protected boolean withEnd;
    @Getter protected boolean end;

    public Animation(int speed, BufferedImage[] frames) {
        this.speed = speed;
        this.frames = frames;
        this.index = 0;
        this.timer = 0;
        this.lastTime = System.currentTimeMillis();
        this.withEnd(false);
        this.end = false;
    }

    public void tick() {
        if (this.end) return;
        this.timer += System.currentTimeMillis() - this.lastTime;
        this.lastTime = System.currentTimeMillis();

        if (this.timer > this.speed) {
            this.index++;
            this.timer = 0;
            if (this.index >= this.frames.length) {
                if (this.hasEnd()) this.end = true;
                this.index = 0;
            }
        }
    }

    public BufferedImage getCurrentFrame() {
        return this.frames[this.index];
    }

    public BufferedImage getFirstFrame() {
        return this.frames[0];
    }

    public void withEnd(boolean end) {
        this.withEnd = end;
    }
    public boolean hasEnd() {
        return this.withEnd;
    }
}
