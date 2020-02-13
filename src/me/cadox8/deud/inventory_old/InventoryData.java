package me.cadox8.deud.inventory_old;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Deprecated
@RequiredArgsConstructor
@Data
public class InventoryData {

    private final InventoryType type;
    private final StaticInventory inventory;

    public enum InventoryType {
        PLAYER, CHEST, SHOP
    }
}
