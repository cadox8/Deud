package es.cadox8.deud.tiles;

import es.cadox8.deud.animations.Animation;
import es.cadox8.deud.utils.Utils;
import lombok.Getter;
import lombok.ToString;

import java.awt.*;
import java.awt.image.BufferedImage;

@ToString
public class Tile {

    public static final int TILEWIDTH = 64, TILEHEIGHT = 64;

    @Getter protected final int id;
    @Getter private final int subID;
    @Getter private final boolean solid;

    @Getter protected BufferedImage texture;
    @Getter protected Animation animation;

    public Tile(final int id, final int subID, final BufferedImage texture, final boolean solid) {
        this.id = id;
        this.subID = subID;
        this.texture = texture;
        this.solid = solid;
    }

    public Tile(final int id, final int subID, final Animation animation, final boolean solid) {
        this.id = id;
        this.subID = subID;
        this.animation = animation;
        this.solid = solid;
    }

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
        return new Tile(id, subID, Utils.rotateImage(degrees, texture), solid);
    }
}
