package me.cadox8.deud.entities.statics;

import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.gfx.textures.Assets;
import me.cadox8.deud.items.Item;
import me.cadox8.deud.tiles.Tile;

import java.awt.*;

public class Rock extends StaticEntity {

    public Rock(GameAPI gameAPI, float x, float y) {
        super(6, "Rock", gameAPI, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);

        bounds.x = 2;
        bounds.y = (int) (height / 2f);
        bounds.width = width - 6;
        bounds.height = (int) (height - height / 2f);
    }

    @Override
    public void die() {
        dropItem(Item.rockItem);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.stone, (int) (x - gameAPI.getGameCamera().getXOffset()), (int) (y - gameAPI.getGameCamera().getYOffset()), width, height, null);
    }
}
