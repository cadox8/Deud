package me.cadox8.deud.particles;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.cadox8.deud.gfx.textures.Assets;

import java.awt.image.BufferedImage;

@RequiredArgsConstructor
public enum Particles {

    EXPLOSION(Assets.explosion, 100);

    @Getter private final BufferedImage[] images;
    @Getter private final int velocity;

    public Particle build() {
        return new Particle(this);
    }
}
