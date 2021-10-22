package es.cadox8.deud.inventory.statics;

import es.cadox8.deud.api.GameAPI;
import es.cadox8.deud.items.Item;
import lombok.Data;
import lombok.RequiredArgsConstructor;

public class ShopInventory extends StaticInventory {

    public ShopInventory(GameAPI gameAPI, int size) {
        super(gameAPI);

        //loadBaseInventory(676, 130, GUI.chest);

        gameAPI.getMouseManager().setAarinManager(getAarinManager());
    }

    @Override
    public void tick() {
        if (!isActive()) return;
        getAarinManager().tick();
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
