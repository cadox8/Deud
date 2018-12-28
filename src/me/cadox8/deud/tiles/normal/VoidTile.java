package me.cadox8.deud.tiles.normal;

import me.cadox8.deud.gfx.textures.Assets;
import me.cadox8.deud.tiles.Tile;

public class VoidTile extends Tile {

    public VoidTile(int id) {
        super(Assets.voidImg, id);
    }

    @Override
    public boolean isSolid() {
        return true;
    }
}
