package me.cadox8.deud.inventory.statics;

import lombok.Getter;
import lombok.Setter;
import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.inventory.Inventory2;
import me.cadox8.deud.items.Item;
import me.cadox8.deud.items.Items;

import java.awt.*;

public class StaticInventory extends Inventory2 {

    @Getter @Setter protected Item usableItem = Items.HAND.item();

    public StaticInventory(GameAPI gameAPI) {
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
