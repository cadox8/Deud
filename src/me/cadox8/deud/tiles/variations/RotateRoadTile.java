package me.cadox8.deud.tiles.variations;

import me.cadox8.deud.gfx.textures.Assets;
import me.cadox8.deud.tiles.Tile;

public class RotateRoadTile extends Tile {

    public RotateRoadTile(int id, double degrees) {
        super(Assets.road, degrees, id);
    }

}
