package me.cadox8.deud.inventory_old;

import lombok.Getter;
import lombok.Setter;
import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.items.Item;

@Deprecated
public class CreatureInventory extends StaticInventory {

    @Getter @Setter protected Item usableItem;

    public CreatureInventory(GameAPI gameAPI) {
        super(gameAPI);
        setSize(20);
    }
}
