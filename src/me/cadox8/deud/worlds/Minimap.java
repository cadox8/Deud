package me.cadox8.deud.worlds;

import me.cadox8.deud.entities.creatures.player.Player;
import me.cadox8.deud.tiles.Tile;
import me.cadox8.deud.tiles.TileData;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Minimap {

    private World world;
    private Player player;

    private int imgWidth = 8;

    private List<TileData> tiles;

    public Minimap(World world, Player player){
        this.world = world;
        this.player = player;
        tiles = loadMap();
    }

    public void paintMap(Graphics g){
        drawRect(g);

        for (TileData td : tiles) {
            if (td == null) continue;
            g.drawImage(td.getBi(), 641 + td.getX(), 6 + td.getY(), null);
            //td.getTile().render(g, 641 + td.getX(), 6 + td.getY(), 20, 20);
        }
    }


    private void drawRect(Graphics g){
        g.setColor(Color.BLACK);
        g.drawRect(640, 5, 155, 105);
    }

    private List<TileData> loadMap() {
        List<TileData> list = new ArrayList<>();

        for (int y = 0; y < world.getHeight() + 2; y++) {
            for (int x = 0; x < world.getWidth() + 2; x++) {
                final Tile t = world.getTile(x, y);

                if (t.getId() == 8) continue;

                final TileData td = new TileData(t, new Color(t.getTexture().getRGB(0, 0)));
                final BufferedImage bi = new BufferedImage(imgWidth, imgWidth, BufferedImage.TYPE_INT_RGB);

                for (int d = 0; d < imgWidth; d++){
                    for (int da = 0; da < imgWidth; da++){
                        bi.setRGB(d, da, td.getColor().getRGB());
                    }
                }
                td.setBi(bi);
                td.setX(x);
                td.setY(y);
                list.add(td);
            }
        }
        return list;
    }
}


