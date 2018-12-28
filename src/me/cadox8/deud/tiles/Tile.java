package me.cadox8.deud.tiles;

import lombok.Getter;
import me.cadox8.deud.tiles.normal.*;
import me.cadox8.deud.tiles.variations.RotateRoadTile;
import me.cadox8.deud.utils.Utils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {

    public static Tile[] tiles = new Tile[256];

    //Tiles
    public static Tile grassTile = new GrassTile(0);
    public static Tile dirtTile = new DirtTile(1);
    public static Tile brickTile = new BrickTile(2);
    public static Tile doorTile = new DoorTile(3);
    public static Tile roadTile = new RoadTile(4);
    public static Tile road2Tile = new Road2Tile(5);
    public static Tile sandTile = new SandTile(6);
    public static Tile rotateDoorTile = new Door2Tile(8);
    public static Tile bug = new BugTile(9);
    public static Tile voidTile = new VoidTile(10);

    //Variations
    public static Tile rotateRoadTile = new RotateRoadTile(7, 90);



    //------------------------------------------------------------------------------//
    public static final int TILEWIDTH = 64, TILEHEIGHT = 64;

    @Getter protected BufferedImage texture;
    @Getter protected final int id;
    //protected final double degrees;

    public Tile(BufferedImage texture, int id) {
        this.texture = texture;
        this.id = id;

        tiles[id] = this;
    }

    public Tile(BufferedImage texture, double degrees, int id) {
        this.texture = Utils.rotateImage(degrees, texture);
        this.id = id;

        tiles[id] = this;
    }

    public void tick() {}

    public void render(Graphics g, int x, int y) {
        render(g, x, y,  TILEWIDTH, TILEHEIGHT);
    }
    public void render(Graphics g, int x, int y, int width, int height) {
        g.drawImage(texture, x, y, width, height, null);
    }

    public boolean isSolid() {
        return false;
    }

    public Tile createNewRotated(int degrees) {
        this.texture = Utils.rotateImage(degrees, texture);
        return this;
    }

    @Override
    public String toString() {
        return "Tile{Id: " + id + ", Class: " + getClass() + "}";
    }
}
