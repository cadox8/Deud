package es.cadox8.deud.managers;

import es.cadox8.deud.api.GameAPI;
import es.cadox8.deud.items.Item;
import lombok.Getter;
import lombok.NonNull;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class ItemManager {

    @Getter private final GameAPI gameAPI;
    public final ArrayList<Item> items;

    public ItemManager(@NonNull GameAPI gameAPI) {
        this.gameAPI = gameAPI;
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
        i.setGameAPI(gameAPI);
        items.add(i);
    }
}
