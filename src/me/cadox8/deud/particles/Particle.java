package me.cadox8.deud.particles;

import lombok.Getter;
import me.cadox8.deud.gfx.Animation;

import java.awt.*;

public class Particle {

    @Getter private final Particles particle;
    @Getter private final Animation animation;

    public Particle(Particles particle) {
        this.particle = particle;
        animation = new Animation(particle.getVelocity(), particle.getImages());
        animation.setEnd(true);
    }

    public void render(Graphics g, int x, int y) {
        g.drawImage(animation.getCurrentFrame(), x, y, null);
    }

    public void tick() {
        animation.tick();
    }
}
