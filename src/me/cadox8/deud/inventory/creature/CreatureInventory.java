package me.cadox8.deud.inventory.creature;

import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.inventory.Inventory;

import java.awt.*;

public class CreatureInventory extends Inventory {

    public CreatureInventory(GameAPI gameAPI) {
        super(gameAPI);

        setSize(20);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {

    }
}
