package me.cadox8.deud.entities.statics;

import lombok.NonNull;
import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.entities.EntityData;
import me.cadox8.deud.entities.creatures.player.Player;
import me.cadox8.deud.gfx.textures.Assets;
import me.cadox8.deud.inventory.StaticInventory;
import me.cadox8.deud.tiles.Tile;

import java.awt.*;

public class Chest extends StaticEntity {

    // Only for instances
    protected Chest(int id, String name, EntityData.EntityType type, @NonNull GameAPI gameAPI, float x, float y) {
        super(id, name, type, gameAPI, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);
    }

    public Chest(@NonNull GameAPI gameAPI, float x, float y) {
        this(gameAPI,x, y, false);
    }
    public Chest(@NonNull GameAPI gameAPI, float x, float y, boolean explosive) {
        super(501, "Chest", EntityData.EntityType.CHEST, gameAPI, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);

        setDamageable(false);
        setExplosive(explosive);

        setDamage(3);
        setLevel(0);

        inventory = new StaticInventory(gameAPI);

        bounds.x = 2;
        bounds.y = (int) (height / 2f) - 5;
        bounds.width = width - 6;
        bounds.height = (int) (height - height / 2f);
    }

    public void open(@NonNull Player p) {
        getInventory().setActive(true);
        p.setChest(getInventory());
        p.getPlayerInventory().setActive(true);
        p.getPlayerInventory().setSelected(true);
        gameAPI.getEntityManager().freezePlayer();
    }

    public void tick() {
        inventory.tick();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.chest, (int) (x - gameAPI.getGameCamera().getXOffset()), (int) (y - gameAPI.getGameCamera().getYOffset()), width, height, null);
    }
}
