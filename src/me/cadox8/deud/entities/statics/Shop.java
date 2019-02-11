package me.cadox8.deud.entities.statics;

import lombok.Getter;
import me.cadox8.deud.api.API;
import me.cadox8.deud.gfx.textures.Assets;
import me.cadox8.deud.items.Item;
import me.cadox8.deud.tiles.Tile;

import java.awt.*;
import java.util.Arrays;

public class Shop extends StaticEntity {

    private boolean hasDropped = false;
    @Getter private final Item[] drops;

    public Shop(API API, float x, float y, Item... drops) {
        super(11, "Shop", API, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);

        this.drops = drops;

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
        if (!hasDropped) Arrays.asList(drops).forEach(this::dropItem);
        hasDropped = true;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.shop[8], (int) (x - API.getGameCamera().getXOffset()), (int) (y - API.getGameCamera().getYOffset()), width, height, null);
        g.drawImage(Assets.shop[4], (int) (x - API.getGameCamera().getXOffset()), (int) ((y - API.getGameCamera().getYOffset()) - height), width, height, null);
        g.drawImage(Assets.shop[0], (int) (x - API.getGameCamera().getXOffset()), (int) ((y - API.getGameCamera().getYOffset()) - (height * 2)), width, height, null);

        g.drawImage(Assets.shop[9], (int) (x - API.getGameCamera().getXOffset() + width), (int) (y - API.getGameCamera().getYOffset()), width, height, null);
        g.drawImage(Assets.shop[5], (int) (x - API.getGameCamera().getXOffset() + width), (int) ((y - API.getGameCamera().getYOffset()) - height), width, height, null);
        g.drawImage(Assets.shop[1], (int) (x - API.getGameCamera().getXOffset() + width), (int) ((y - API.getGameCamera().getYOffset()) - (height * 2)), width, height, null);

        g.drawImage(Assets.shop[10], (int) (x - API.getGameCamera().getXOffset() + (width * 2)), (int) (y - API.getGameCamera().getYOffset()), width, height, null);
        g.drawImage(Assets.shop[6], (int) (x - API.getGameCamera().getXOffset() + (width * 2)), (int) ((y - API.getGameCamera().getYOffset()) - height), width, height, null);
        g.drawImage(Assets.shop[2], (int) (x - API.getGameCamera().getXOffset() + (width * 2)), (int) ((y - API.getGameCamera().getYOffset()) - (height * 2)), width, height, null);

        g.drawImage(Assets.shop[11], (int) (x - API.getGameCamera().getXOffset() + (width * 3)), (int) (y - API.getGameCamera().getYOffset()), width, height, null);
        g.drawImage(Assets.shop[7], (int) (x - API.getGameCamera().getXOffset() + (width * 3)), (int) ((y - API.getGameCamera().getYOffset()) - height), width, height, null);
        g.drawImage(Assets.shop[3], (int) (x - API.getGameCamera().getXOffset() + (width * 3)), (int) ((y - API.getGameCamera().getYOffset()) - (height * 2)), width, height, null);
    }
}
