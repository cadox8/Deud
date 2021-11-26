package es.cadox8.deud.entities.statics.chest;

import es.cadox8.deud.api.GameAPI;
import es.cadox8.deud.graphics.textures.Assets;
import lombok.Getter;
import lombok.NonNull;
import es.cadox8.deud.entities.creatures.player.Player;
import es.cadox8.deud.entities.enums.EntityType;
import es.cadox8.deud.entities.statics.StaticEntity;
import es.cadox8.deud.inventory.statics.ChestInventory;
import es.cadox8.deud.tiles.Tile;

import java.awt.*;

public class Chest extends StaticEntity {

    @Getter private final ChestType chestType;

    public Chest(@NonNull GameAPI gameAPI, float x, float y, ChestType chestType) {
        super("fc356233-0700-49a3-98bd-bd2350acd339", "Chest", EntityType.CHEST, gameAPI, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);

        this.chestType = chestType;

        setDamageable(false);

        setDamage(0);

        inventory = new ChestInventory(gameAPI, 20);

        bounds.x = 2;
        bounds.y = (int) (height / 2f) - 5;
        bounds.width = width - 3;
        bounds.height = (int) (height - height / 2f);
    }

    public void open(@NonNull Player p) {
        gameAPI.getEntityManager().freezePlayer();
    }

    public void tick() {
        inventory.tick();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.chest, (int) (x - gameAPI.getGameCamera().getXOffset()), (int) (y - gameAPI.getGameCamera().getYOffset()), width, height, null);
    }

    public enum ChestType {
        NORMAL, REWARD, TRAP
    }
}
