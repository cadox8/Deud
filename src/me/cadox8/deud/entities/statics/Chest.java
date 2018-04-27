package me.cadox8.deud.entities.statics;

import me.cadox8.deud.api.API;
import me.cadox8.deud.attributes.Explosion;
import me.cadox8.deud.entities.creatures.player.Player;
import me.cadox8.deud.gfx.textures.Assets;
import me.cadox8.deud.items.Item;
import me.cadox8.deud.tiles.Tile;

import java.awt.*;

public class Chest extends StaticEntity {

    public Chest(API API, float x, float y) {
        this(API,x, y, false);
    }
    public Chest(API API, float x, float y, boolean explosive) {
        super(API, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);

        setDamageable(false);
        setExplosive(explosive);

        bounds.x = 3;
        bounds.y = (int) (height / 2f);
        bounds.width = width - 6;
        bounds.height = (int) (height - height / 2f);
    }

    public void open(Player p) {
        if (p.getInventory().keyCount() == 0) return;
        p.getInventory().removeItem(Item.keyItem);

        Item random = Item.getRandom();
        p.getInventory().addItem(random);

        if (isExplosive()) new Explosion(5, 3).perform();
    }


    @Override
    public void tick() {}

    @Override
    public void die() {}

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.chest, (int) (x - API.getGameCamera().getXOffset()), (int) (y - API.getGameCamera().getYOffset()), width, height, null);
    }

    @Override
    public void specialRender(Graphics g) {}
}
