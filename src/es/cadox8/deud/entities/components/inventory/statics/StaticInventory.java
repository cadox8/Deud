package es.cadox8.deud.entities.components.inventory.statics;

import es.cadox8.deud.api.GameAPI;
import es.cadox8.deud.items.Item;
import es.cadox8.deud.items.Items;
import lombok.Getter;
import lombok.Setter;
import es.cadox8.deud.entities.components.inventory.Inventory;

import java.awt.*;

public class StaticInventory extends Inventory {

    @Getter @Setter protected Item usableItem = Items.HAND.item();

    public StaticInventory(GameAPI gameAPI) {
        super(gameAPI);

    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {

    }
}
