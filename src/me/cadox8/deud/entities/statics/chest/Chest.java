package me.cadox8.deud.entities.statics.chest;

import lombok.NonNull;
import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.entities.EntityData;
import me.cadox8.deud.entities.creatures.player.Player;
import me.cadox8.deud.entities.statics.StaticEntity;
import me.cadox8.deud.graphics.textures.Assets;
import me.cadox8.deud.graphics.textures.GUI;
import me.cadox8.deud.inventory.statics.ChestInventory;
import me.cadox8.deud.items.Item;
import me.cadox8.deud.tiles.Tile;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Chest extends StaticEntity {

    public Chest(@NonNull GameAPI gameAPI, float x, float y) {
        super(501, "Chest", EntityData.EntityType.CHEST, gameAPI, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);

        setDamageable(false);

        setDamage(0);
        setLevel(0);

        inventory = new ChestInventory(gameAPI, 20);

        bounds.x = 2;
        bounds.y = (int) (height / 2f) - 5;
        bounds.width = width - 3;
        bounds.height = (int) (height - height / 2f);
    }

    public void open(@NonNull Player p) {
        getInventory().setActive(true, GUI.chest);
        p.getPlayerInventory().setActive(true, GUI.inventory);
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
