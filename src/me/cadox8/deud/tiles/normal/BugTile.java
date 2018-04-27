package me.cadox8.deud.tiles.normal;

import me.cadox8.deud.gfx.textures.Assets;
import me.cadox8.deud.tiles.Tile;

public class BugTile extends Tile {

    public BugTile(int id) {
        super(Assets.bug, id);
    }

    @Override
    public boolean isSolid() {
        return true;
    }
}
