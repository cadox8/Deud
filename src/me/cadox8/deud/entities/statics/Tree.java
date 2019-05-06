package me.cadox8.deud.entities.statics;

import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.gfx.textures.Assets;
import me.cadox8.deud.items.Item;
import me.cadox8.deud.tiles.Tile;

import java.awt.*;

public class Tree extends StaticEntity {

    public Tree(GameAPI gameAPI, float x, float y) {
        super(5, "Tree", gameAPI, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);

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
        g.drawImage(Assets.tree2, (int) (x - gameAPI.getGameCamera().getXOffset()), (int) (y - gameAPI.getGameCamera().getYOffset()), width, height, null);
        g.drawImage(Assets.tree, (int) (x - gameAPI.getGameCamera().getXOffset()), (int) ((y - gameAPI.getGameCamera().getYOffset()) - height), width, height, null);
    }
}
