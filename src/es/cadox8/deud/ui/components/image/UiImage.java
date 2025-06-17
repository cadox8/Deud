package es.cadox8.deud.ui.components.image;

import es.cadox8.deud.ui.UiComponent;
import es.cadox8.deud.ui.helpers.UiDimension;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.awt.*;
import java.awt.image.BufferedImage;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class UiImage extends UiComponent {

    private BufferedImage image;

    public UiImage(BufferedImage image) {
        this.image = image;
    }

    @Override
    public void tick() {}

    @Override
    public void render(Graphics g) {
        g.drawImage(this.image, this.getUiDimension().getX(), this.getUiDimension().getY(), this.getUiDimension().getWidth(), this.getUiDimension().getHeight(),null);
    }

    @Override
    public void onClick() {}

    public void setUiDimension(int x, int y) {
        this.setUiDimension(x, y, this.image.getWidth(), this.image.getHeight());
    }
}
