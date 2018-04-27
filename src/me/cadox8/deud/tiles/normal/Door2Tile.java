package me.cadox8.deud.tiles.normal;

import me.cadox8.deud.gfx.textures.Assets;
import me.cadox8.deud.tiles.Tile;

public class Door2Tile extends Tile {

    public Door2Tile(int id) {
        super(Assets.door2, id);
    }

    public boolean isSolid() {
        return true;
    }
}
