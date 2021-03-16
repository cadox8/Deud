package me.cadox8.deud.inventory.statics;

import lombok.Getter;
import lombok.Setter;
import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.inventory.Inventory;
import me.cadox8.deud.items.Item;
import me.cadox8.deud.items.Items;

import java.awt.*;

public class StaticInventory extends Inventory {

    @Getter @Setter protected Item usableItem = Items.HAND.item();

    public StaticInventory(GameAPI gameAPI) {
        super(gameAPI);

    }

    @Override
    protected void loadItems() {

    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {

    }
}
