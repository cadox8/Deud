package me.cadox8.deud.entities.statics;

import me.cadox8.deud.api.API;
import me.cadox8.deud.gfx.textures.Assets;
import me.cadox8.deud.items.Item;
import me.cadox8.deud.tiles.Tile;

import java.awt.*;

public class Tree extends StaticEntity {

    public Tree(API API, float x, float y) {
        super(API, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);

        bounds.x = 10;
        bounds.y = (int) (height / 1.5f);
        bounds.width = width - 20;
        bounds.height = (int) (height - height / 1.5f);
    }

    @Override
    public void tick() {
    }

    @Override
    public void die() {
        dropItem(Item.woodItem);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.tree2, (int) (x - API.getGameCamera().getXOffset()), (int) (y - API.getGameCamera().getYOffset()), width, height, null);
        g.drawImage(Assets.tree, (int) (x - API.getGameCamera().getXOffset()), (int) ((y - API.getGameCamera().getYOffset()) - height), width, height, null);
    }
}
