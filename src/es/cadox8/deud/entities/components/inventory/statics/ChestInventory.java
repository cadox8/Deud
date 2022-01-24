package es.cadox8.deud.entities.components.inventory.statics;

import es.cadox8.deud.entities.components.inventory.Inventory;
import lombok.Getter;

import java.awt.*;

public class ChestInventory extends Inventory {

    @Getter private final int size;

    public ChestInventory(int size) {
        super(InventoryType.CHEST);
        this.size = size;
    }

    @Override
    public void open() {

    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {

    }
}
