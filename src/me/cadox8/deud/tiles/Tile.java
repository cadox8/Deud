package me.cadox8.deud.tiles;

import lombok.*;
import me.cadox8.deud.utils.Utils;

import java.awt.*;
import java.awt.image.BufferedImage;

@RequiredArgsConstructor
@AllArgsConstructor
@ToString
public class Tile {

    public static final int TILEWIDTH = 64, TILEHEIGHT = 64;


    @Getter protected final int id;
    @Getter protected final BufferedImage texture;
    @Getter @Setter private int subID;
    @Getter @Setter private boolean solid;

    public void tick() {}


    public void render(Graphics g, int x, int y) {
        render(g, x, y,  TILEWIDTH, TILEHEIGHT);
    }
    public void render(Graphics g, int x, int y, int width, int height) {
        g.drawImage(texture, x, y, width, height, null);
    }

    public Tile createNewRotated(int degrees) {
        return new Tile(id, Utils.rotateImage(degrees, texture), subID, solid);
    }
}
