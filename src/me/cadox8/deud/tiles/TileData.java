package me.cadox8.deud.tiles;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.image.BufferedImage;

public class TileData {

    @Getter private Tile tile;
    @Getter private Color color;

    @Getter @Setter private BufferedImage bi;
    @Getter @Setter private int x;
    @Getter @Setter private int y;

    public TileData(Tile tile, Color color){
        this.tile = tile;
        this.color = color;
    }
}
