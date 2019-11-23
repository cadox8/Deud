package me.cadox8.deud.entities.statics;

import lombok.NonNull;
import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.gfx.textures.Assets;
import me.cadox8.deud.inventory.StaticInventory;
import me.cadox8.deud.items.Item;
import me.cadox8.deud.tiles.Tile;

import java.awt.*;

public class Shop extends StaticEntity {

    private boolean hasDropped = false;

    public Shop(@NonNull GameAPI gameAPI, float x, float y, Item... drops) {
        super(252, "Shop", gameAPI, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);

        inventory = new StaticInventory(gameAPI);

        setDamageable(false);

        bounds.x = 32;
        bounds.y = height - 100;
        bounds.width = width * 3 + 33;
        bounds.height = height * 2 - 30;
    }

    @Override
    public void tick() {}

    @Override
    public void die() {}

    @Override
    public void getHurt() {
        if (!hasDropped) inventory.getItems().forEach(this::dropItem);
        hasDropped = true;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.post_Shop[8], (int) (x - gameAPI.getGameCamera().getXOffset()), (int) (y - gameAPI.getGameCamera().getYOffset()), width, height, null);
        g.drawImage(Assets.post_Shop[4], (int) (x - gameAPI.getGameCamera().getXOffset()), (int) ((y - gameAPI.getGameCamera().getYOffset()) - height), width, height, null);
        g.drawImage(Assets.post_Shop[0], (int) (x - gameAPI.getGameCamera().getXOffset()), (int) ((y - gameAPI.getGameCamera().getYOffset()) - (height * 2)), width, height, null);

        g.drawImage(Assets.post_Shop[9], (int) (x - gameAPI.getGameCamera().getXOffset() + width), (int) (y - gameAPI.getGameCamera().getYOffset()), width, height, null);
        g.drawImage(Assets.post_Shop[5], (int) (x - gameAPI.getGameCamera().getXOffset() + width), (int) ((y - gameAPI.getGameCamera().getYOffset()) - height), width, height, null);
        g.drawImage(Assets.post_Shop[1], (int) (x - gameAPI.getGameCamera().getXOffset() + width), (int) ((y - gameAPI.getGameCamera().getYOffset()) - (height * 2)), width, height, null);

        g.drawImage(Assets.post_Shop[10], (int) (x - gameAPI.getGameCamera().getXOffset() + (width * 2)), (int) (y - gameAPI.getGameCamera().getYOffset()), width, height, null);
        g.drawImage(Assets.post_Shop[6], (int) (x - gameAPI.getGameCamera().getXOffset() + (width * 2)), (int) ((y - gameAPI.getGameCamera().getYOffset()) - height), width, height, null);
        g.drawImage(Assets.post_Shop[2], (int) (x - gameAPI.getGameCamera().getXOffset() + (width * 2)), (int) ((y - gameAPI.getGameCamera().getYOffset()) - (height * 2)), width, height, null);

        g.drawImage(Assets.post_Shop[11], (int) (x - gameAPI.getGameCamera().getXOffset() + (width * 3)), (int) (y - gameAPI.getGameCamera().getYOffset()), width, height, null);
        g.drawImage(Assets.post_Shop[7], (int) (x - gameAPI.getGameCamera().getXOffset() + (width * 3)), (int) ((y - gameAPI.getGameCamera().getYOffset()) - height), width, height, null);
        g.drawImage(Assets.post_Shop[3], (int) (x - gameAPI.getGameCamera().getXOffset() + (width * 3)), (int) ((y - gameAPI.getGameCamera().getYOffset()) - (height * 2)), width, height, null);
    }
}
