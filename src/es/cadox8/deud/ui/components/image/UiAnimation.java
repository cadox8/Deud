package es.cadox8.deud.ui.components.image;

import es.cadox8.deud.animations.Animation;
import es.cadox8.deud.ui.UiComponent;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UiAnimation extends UiComponent {

    private Animation animation;

    public UiAnimation(Animation animation) {
        this.animation = animation;
    }

    @Override
    public void tick() {
        this.animation.tick();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(this.animation.getCurrentFrame(), this.getUiDimension().getX(), this.getUiDimension().getY(), this.getUiDimension().getWidth(), this.getUiDimension().getHeight(),null);
    }

    @Override
    public void onClick() {}
}
