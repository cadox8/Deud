package me.cadox8.deud.ui;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UIImage extends UIObject {

    private BufferedImage[] images;

    public UIImage(float x, float y, int width, int height, BufferedImage[] images) {
        super(x, y, width, height);
        this.images = images;
    }

    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(images[0], (int) x, (int) y, width, height, null);
/*        if(hovering){
            g.drawImage(images[1], (int) x, (int) y, width, height, null);
        }else{
            g.drawImage(images[0], (int) x, (int) y, width, height, null);
        }*/
    }

    @Override
    public void onClick() {
    }
}
