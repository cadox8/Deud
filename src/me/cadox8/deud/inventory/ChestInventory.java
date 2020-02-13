package me.cadox8.deud.inventory;

import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.items.Item;

import java.awt.*;

public class ChestInventory extends Inventory {

    public ChestInventory(GameAPI gameAPI, int size) {
        super(gameAPI);

        setSize(size);

        addItem(Item.sword);

        loadItems(676, 130);

        gameAPI.getMouseManager().setNysvaUI(getNysvaManager());
    }

    @Override
    public void tick() {
        if (!isActive()) return;
        getNysvaManager().tick();
    }

    @Override
    public void render(Graphics g) {
        if (!isActive()) return;
        getNysvaManager().render(g);
        hoverSelector(g, 855, 646);
    }
}
