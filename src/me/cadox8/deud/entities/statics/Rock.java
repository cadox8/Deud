package me.cadox8.deud.entities.statics;

import me.cadox8.deud.api.API;
import me.cadox8.deud.gfx.textures.Assets;
import me.cadox8.deud.items.Item;
import me.cadox8.deud.tiles.Tile;

import java.awt.*;

public class Rock extends StaticEntity {

    public Rock(API API, float x, float y) {
        super(API, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);

        bounds.x = 3;
        bounds.y = (int) (height / 2f);
        bounds.width = width - 6;
        bounds.height = (int) (height - height / 2f);
    }

    @Override
    public void tick() {
    }

    @Override
    public void die() {
        dropItem(Item.rockItem);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.stone, (int) (x - API.getGameCamera().getXOffset()), (int) (y - API.getGameCamera().getYOffset()), width, height, null);
    }

    @Override
    public void specialRender(Graphics g) {
    }
}
