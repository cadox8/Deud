package me.cadox8.deud.tiles.normal;

import me.cadox8.deud.gfx.textures.Assets;
import me.cadox8.deud.tiles.Tile;

public class BrickTile extends Tile {

    public BrickTile(int id) {
        super(Assets.brick, id);
    }

    public boolean isSolid() {
        return true;
    }
}
