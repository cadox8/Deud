package es.cadox8.deud.ui.components.button;

import es.cadox8.deud.ui.components.image.UiImage;
import es.cadox8.deud.ui.helpers.ClickListener;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UiImageButton extends UiButton {

    private final UiImage image;

    public UiImageButton(BufferedImage image, ClickListener clickListener) {
        super(clickListener);

        this.image = new UiImage(image);
        this.setTransparent();
    }

    @Override
    public void tick() {
        super.tick();
        this.image.setUiDimension(this.getUiDimension());
    }

    @Override
    public void render(Graphics g) {
        super.render(g);
        this.image.render(g);
    }
}
