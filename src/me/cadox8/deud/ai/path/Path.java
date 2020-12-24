package me.cadox8.deud.ai.path;

import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.worlds.World;

public class Path {

    private final GameAPI gameAPI;

    private World.TileUtils[][] tiles;

    public Path(GameAPI gameAPI) {
        this.gameAPI = gameAPI;
        this.tiles = this.gameAPI.getWorld().getTiles();
    }
}
