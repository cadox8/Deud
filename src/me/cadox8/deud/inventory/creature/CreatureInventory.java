package me.cadox8.deud.inventory.creature;

import lombok.Getter;
import lombok.Setter;
import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.inventory.Inventory;
import me.cadox8.deud.items.Item;
import me.cadox8.deud.items.Items;

import java.awt.*;

public class CreatureInventory extends Inventory {

    @Getter @Setter protected Item usableItem = Items.getHand();

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
