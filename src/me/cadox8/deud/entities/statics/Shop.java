package me.cadox8.deud.entities.statics;

import lombok.Getter;
import me.cadox8.deud.api.API;
import me.cadox8.deud.gfx.textures.Assets;
import me.cadox8.deud.items.Item;
import me.cadox8.deud.tiles.Tile;

import java.awt.*;

public class Shop extends StaticEntity {

    private boolean hasDroppped = false;
    @Getter private final Item drop;

    public Shop(API API, float x, float y, Item drop) {
        super(11, "Shop", API, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);

        this.drop = drop;

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
        hasDroppped = true;
        if (drop != null) dropItem(drop);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.shop9, (int) (x - API.getGameCamera().getXOffset()), (int) (y - API.getGameCamera().getYOffset()), width, height, null);
        g.drawImage(Assets.shop5, (int) (x - API.getGameCamera().getXOffset()), (int) ((y - API.getGameCamera().getYOffset()) - height), width, height, null);
        g.drawImage(Assets.shop1, (int) (x - API.getGameCamera().getXOffset()), (int) ((y - API.getGameCamera().getYOffset()) - (height * 2)), width, height, null);

        g.drawImage(Assets.shop10, (int) (x - API.getGameCamera().getXOffset() + width), (int) (y - API.getGameCamera().getYOffset()), width, height, null);
        g.drawImage(Assets.shop6, (int) (x - API.getGameCamera().getXOffset() + width), (int) ((y - API.getGameCamera().getYOffset()) - height), width, height, null);
        g.drawImage(Assets.shop2, (int) (x - API.getGameCamera().getXOffset() + width), (int) ((y - API.getGameCamera().getYOffset()) - (height * 2)), width, height, null);

        g.drawImage(Assets.shop11, (int) (x - API.getGameCamera().getXOffset() + (width * 2)), (int) (y - API.getGameCamera().getYOffset()), width, height, null);
        g.drawImage(Assets.shop7, (int) (x - API.getGameCamera().getXOffset() + (width * 2)), (int) ((y - API.getGameCamera().getYOffset()) - height), width, height, null);
        g.drawImage(Assets.shop3, (int) (x - API.getGameCamera().getXOffset() + (width * 2)), (int) ((y - API.getGameCamera().getYOffset()) - (height * 2)), width, height, null);

        g.drawImage(Assets.shop12, (int) (x - API.getGameCamera().getXOffset() + (width * 3)), (int) (y - API.getGameCamera().getYOffset()), width, height, null);
        g.drawImage(Assets.shop8, (int) (x - API.getGameCamera().getXOffset() + (width * 3)), (int) ((y - API.getGameCamera().getYOffset()) - height), width, height, null);
        g.drawImage(Assets.shop4, (int) (x - API.getGameCamera().getXOffset() + (width * 3)), (int) ((y - API.getGameCamera().getYOffset()) - (height * 2)), width, height, null);
    }
}
