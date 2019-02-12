package me.cadox8.deud.items;

import lombok.Getter;
import me.cadox8.deud.api.GameAPI;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class ItemManager {

    @Getter private final GameAPI GameAPI;
    public final ArrayList<Item> items;

    public ItemManager(GameAPI GameAPI) {
        this.GameAPI = GameAPI;
        items = new ArrayList<>();
    }

    public void tick() {
        final Iterator<Item> it = items.iterator();
        while(it.hasNext()) {
            final Item i = it.next();
            i.tick();
            if (i.isPickedUp()) it.remove();
        }
    }

    public void render(Graphics g) {
        items.forEach(i -> i.render(g));
    }

    public void addItem(Item i) {
        if(i.isPickedUp()) i.setPickedUp(false);
        i.setGameAPI(GameAPI);
        items.add(i);
    }
}
