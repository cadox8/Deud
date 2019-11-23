package me.cadox8.deud.inventory;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.entities.Entity;
import me.cadox8.deud.items.Item;

public class CreatureInventory extends StaticInventory {

    @Getter @Setter protected Item usableItem;

    public CreatureInventory(GameAPI gameAPI) {
        super(gameAPI);

        setSize(20);
    }

    private void dropItem(@NonNull Entity entity, @NonNull Item item) {
        entity.dropItem(item);
        removeItem(item);
    }
}
