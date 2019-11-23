package me.cadox8.deud.entities.statics.trees;

import lombok.NonNull;
import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.entities.EntityData;
import me.cadox8.deud.gfx.textures.Assets;
import me.cadox8.deud.tiles.Tile;

import java.awt.*;

public class DeadTree extends Tree {

    public DeadTree(@NonNull GameAPI gameAPI, float x, float y) {
        super(101, "DeadTree", EntityData.EntityType.DEADTREE, gameAPI, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);

        bounds.x = -5;
        bounds.y = (int) (height / 1.5f) - 17;
        bounds.width = width + 8;
        bounds.height = (int) (height - height / 1.5f);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.deadTree[6], (int) (x - gameAPI.getGameCamera().getXOffset() - width), (int) (y - gameAPI.getGameCamera().getYOffset()), width, height, null);
        g.drawImage(Assets.deadTree[7], (int) (x - gameAPI.getGameCamera().getXOffset()), (int) (y - gameAPI.getGameCamera().getYOffset()), width, height, null);
        g.drawImage(Assets.deadTree[8], (int) (x - gameAPI.getGameCamera().getXOffset() + width), (int) (y - gameAPI.getGameCamera().getYOffset()), width, height, null);


        g.drawImage(Assets.deadTree[3], (int) (x - gameAPI.getGameCamera().getXOffset() - width), (int) (y - gameAPI.getGameCamera().getYOffset() - height), width, height, null);
        if (getTreeType() == 0) {
            g.drawImage(Assets.deadTree[4], (int) (x - gameAPI.getGameCamera().getXOffset()), (int) (y - gameAPI.getGameCamera().getYOffset() - height), width, height, null);
        } else {
            g.drawImage(Assets.deadTree[9], (int) (x - gameAPI.getGameCamera().getXOffset()), (int) (y - gameAPI.getGameCamera().getYOffset() - height), width, height, null);
        }
        g.drawImage(Assets.deadTree[5], (int) (x - gameAPI.getGameCamera().getXOffset() + width), (int) (y - gameAPI.getGameCamera().getYOffset() - height), width, height, null);

        g.drawImage(Assets.deadTree[0], (int) (x - gameAPI.getGameCamera().getXOffset() - width), (int) (y - gameAPI.getGameCamera().getYOffset() - (height * 2)), width, height, null);
        g.drawImage(Assets.deadTree[1], (int) (x - gameAPI.getGameCamera().getXOffset()), (int) ((y - gameAPI.getGameCamera().getYOffset()) - (height * 2)), width, height, null);
        g.drawImage(Assets.deadTree[2], (int) (x - gameAPI.getGameCamera().getXOffset() + width), (int) ((y - gameAPI.getGameCamera().getYOffset()) - (height * 2)), width, height, null);
    }
}
