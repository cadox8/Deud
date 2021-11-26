package es.cadox8.deud.ui.components.image;

import es.cadox8.deud.api.GameAPI;
import lombok.Getter;
import lombok.Setter;
import es.cadox8.deud.ui.AarinUI;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UIImage extends AarinUI {

    @Getter @Setter protected BufferedImage image;
    @Getter @Setter protected boolean resizable;
    
    public UIImage(GameAPI gameAPI, BufferedImage image) {
        super(gameAPI);
        this.image = image;
        
        this.resizable = true;
    } 
    
    @Override
    public void tick() {}

    @Override
    public void render(Graphics g) {
        final Rectangle r = this.getArea().getPolygon().getBounds();
        if (this.isResizable()) {
            if (hovering) {
                g.drawImage(image, (int)r.getX(), (int)r.getY(), (int)r.getWidth() + 5, (int)r.getHeight() + 5, null);
            } else {
                g.drawImage(image, (int)r.getX(), (int)r.getY(), (int)r.getWidth(), (int)r.getHeight(),null);
            }
        } else {
            g.drawImage(image, (int)r.getX(), (int)r.getY(), (int)r.getWidth(), (int)r.getHeight(),null);
        }
    }

    @Override
    public void onClick() {}

    /**
     * Changes an image on the fly
     *
     * @param newImage The new image
     */
    public synchronized void changeImage(BufferedImage newImage) {
        this.image = newImage;
    }
}
