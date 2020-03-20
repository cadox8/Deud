package me.cadox8.deud.inventory.statics;

import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.graphics.textures.GUI;
import me.cadox8.deud.items.Item;

public class ChestInventory extends StaticInventory {

    public ChestInventory(GameAPI gameAPI, int size) {
        super(gameAPI);

        setSize(size);

        addItems(Item.sword, Item.keyItem, Item.chickenItem);

        loadBaseInventory(676, 130, GUI.chest);

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
}
