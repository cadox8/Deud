package me.cadox8.deud.entities.statics;

import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.attributes.Explosion;
import me.cadox8.deud.entities.creatures.player.Player;
import me.cadox8.deud.gfx.textures.Assets;
import me.cadox8.deud.items.Item;
import me.cadox8.deud.tiles.Tile;

import java.awt.*;

public class Chest extends StaticEntity {

    public Chest(GameAPI gameAPI, float x, float y) {
        this(gameAPI,x, y, true);
    }
    public Chest(GameAPI gameAPI, float x, float y, boolean explosive) {
        super(8, "Chest", gameAPI, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);

        setDamageable(false);
        setExplosive(explosive);

        setDamage(5);
        setLevel(0);

        bounds.x = 2;
        bounds.y = (int) (height / 2f) - 5;
        bounds.width = width - 6;
        bounds.height = (int) (height - height / 2f);
    }

    public void open(Player p) {
        if (p.getInventory().keyCount() == 0) return;
        p.getInventory().removeItem(Item.keyItem);
        //p.getInventory().getInventoryItems().stream().filter(i -> i.getId() == Item.keyItem.getId()).findFirst().get().removeItem(p);

        p.getInventory().addItem(Item.getRandom(Item.hand, Item.keyItem));

        if (isExplosive()) new Explosion(this.getGameAPI(),5, 3).perform(this, null);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.chest, (int) (x - gameAPI.getGameCamera().getXOffset()), (int) (y - gameAPI.getGameCamera().getYOffset()), width, height, null);
    }
}
