package me.cadox8.deud.entities.statics;

import lombok.NonNull;
import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.entities.EntityData;
import me.cadox8.deud.entities.creatures.player.Player;
import me.cadox8.deud.graphics.textures.Assets;
import me.cadox8.deud.inventory.statics.ShopInventory;
import me.cadox8.deud.items.Item;
import me.cadox8.deud.tiles.Tile;

import java.awt.*;
import java.util.Random;

public class Shop extends StaticEntity {

    private boolean hasDropped = false;

    public Shop(@NonNull GameAPI gameAPI, float x, float y, Item... drops) {
        super("79ee0aa3-4815-4653-abbe-fcf09ff81c46", "Shop", EntityData.EntityType.SHOP, gameAPI, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);

        inventory = new ShopInventory(gameAPI, 20);

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
        if (!hasDropped) dropItem(inventory.getItems().get(new Random().nextInt(inventory.getItems().size())));
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

    public void open(@NonNull Player p) {
        //if (hasDropped) return;
        gameAPI.getEntityManager().freezePlayer();
    }

    public boolean hasDropped() {
        return hasDropped;
    }
}
