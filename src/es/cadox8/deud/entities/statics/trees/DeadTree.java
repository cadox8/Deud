package es.cadox8.deud.entities.statics.trees;

import es.cadox8.deud.api.GameAPI;
import es.cadox8.deud.entities.enums.EntityType;
import es.cadox8.deud.graphics.textures.Assets;
import es.cadox8.deud.items.objects.WoodItem;
import lombok.NonNull;
import es.cadox8.deud.tiles.Tile;

import java.awt.*;

public class DeadTree extends Tree {

    public DeadTree(@NonNull GameAPI gameAPI, float x, float y) {
        super("2a774d31-d937-46ef-bdde-ad97a4ece468", "DeadTree", EntityType.DEADTREE, gameAPI, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);

        bounds.x = 0;
        bounds.y = (int) (height / 1.5f) - 17;
        bounds.width = width - 10;
        bounds.height = (int) (height - height / 1.5f);
    }

    @Override
    public void die() {
        dropItem(new WoodItem().randomAmount(1, 2));
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
