package me.cadox8.deud.inventory;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class InventoryData {

    private final InventoryType type;
    private final StaticInventory inventory;

    public enum InventoryType {
        PLAYER, CHEST, SHOP
    }
}
