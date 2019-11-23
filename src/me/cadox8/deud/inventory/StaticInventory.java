package me.cadox8.deud.inventory;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.entities.Entity;
import me.cadox8.deud.items.Item;

public class StaticInventory extends Inventory {

    public StaticInventory(GameAPI gameAPI) {
        super(gameAPI);
    }

    private void dropItem(@NonNull Entity entity, @NonNull Item item) {
        entity.dropItem(item);
        removeItem(item);
    }
}
