package es.cadox8.deud.ui.components.button;

import es.cadox8.deud.ui.components.image.UiSelectedImage;
import es.cadox8.deud.ui.helpers.ClickListener;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UiSelectedImageButton extends UiButton {

    private final UiSelectedImage selectedImage;

    public UiSelectedImageButton(BufferedImage image, BufferedImage selector, ClickListener clickListener) {
        super(clickListener);

        this.selectedImage = new UiSelectedImage(image, selector);
    }

    @Override
    public void tick() {
        super.tick();
        this.selectedImage.setUiDimension(this.getUiDimension());
        this.selectedImage.tick();
    }

    @Override
    public void render(Graphics g) {
        super.render(g);
        this.selectedImage.render(g);
    }
}
