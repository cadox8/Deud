package me.cadox8.deud.tiles;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import me.cadox8.deud.animations.Animation;
import me.cadox8.deud.utils.Utils;

import java.awt.*;
import java.awt.image.BufferedImage;

@RequiredArgsConstructor
@ToString
public class Tile {

    public static final int TILEWIDTH = 64, TILEHEIGHT = 64;


    @Getter protected final int id;
    @Getter protected final BufferedImage texture;
    @Getter private final int subID;
    @Getter private final boolean solid;

    @Getter protected Animation animation = null;

    public void tick() {
        if (getAnimation() != null) animation.tick();
    }


    public void render(Graphics g, int x, int y) {
        render(g, x, y,  TILEWIDTH, TILEHEIGHT);
    }
    public void render(Graphics g, int x, int y, int width, int height) {
        if (getAnimation() != null) {
            g.drawImage(animation.getCurrentFrame(), x, y, width, height, null);
        } else {
            g.drawImage(texture, x, y, width, height, null);
        }
    }

    public Tile createNewRotated(int degrees) {
        return new Tile(id, Utils.rotateImage(degrees, texture), subID, solid);
    }

    public Tile createNewRotatedWithAnimation(int degrees) {
        return new Tile(id, Utils.rotateImage(degrees, texture), subID, solid).withAnimation(animation);
    }

    public Tile withAnimation(Animation animation) {
        this.animation = animation;
        return this;
    }
}
