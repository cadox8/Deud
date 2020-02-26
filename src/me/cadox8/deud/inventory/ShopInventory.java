package me.cadox8.deud.inventory;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.items.Item;

public class ShopInventory extends StaticInventory {

    public ShopInventory(GameAPI gameAPI, int size) {
        super(gameAPI);

        setSize(size);

        addItems(Item.sword, Item.keyItem, Item.chickenItem);

        loadBaseInventory(676, 130);

        gameAPI.getMouseManager().setNysvaUI(getNysvaManager());
    }

    @Override
    public void tick() {
        if (!isActive()) return;
        getNysvaManager().tick();
    }

/*    @Override
    public void render(Graphics g) {
        if (!isActive()) return;
        getNysvaManager().render(g);
        hoverSelector(g, 855, 646);
    }*/


    @RequiredArgsConstructor
    @Data
    public static class ShopItem {

        private final Item item;
        private final double price;
    }
}
