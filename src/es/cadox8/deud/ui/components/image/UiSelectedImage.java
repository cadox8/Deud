package es.cadox8.deud.ui.components.image;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UiSelectedImage extends UiImage {

    private final UiImage selector;

    public UiSelectedImage(BufferedImage image, BufferedImage selector) {
        super(image);

        this.selector = new UiImage(selector);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.selector.getUiDimension() == null) this.selector.setUiDimension(this.getUiDimension());
    }

    @Override
    public void render(Graphics g) {
        super.render(g);
        if (this.isHovering()) this.selector.render(g);
    }
}
