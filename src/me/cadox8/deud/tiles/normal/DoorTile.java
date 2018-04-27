package me.cadox8.deud.tiles.normal;

import me.cadox8.deud.gfx.textures.Assets;
import me.cadox8.deud.tiles.Tile;

public class DoorTile extends Tile {

    public DoorTile(int id) {
        super(Assets.door, id);
    }

    public boolean isSolid() {
        return true;
    }
}
