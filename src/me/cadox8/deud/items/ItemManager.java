package me.cadox8.deud.items;

import lombok.Getter;
import lombok.Setter;
import me.cadox8.deud.api.API;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class ItemManager {

    @Getter @Setter private API API;
    public ArrayList<Item> items;

    public ItemManager(API API) {
        this.API = API;
        items = new ArrayList<>();
    }

    public void tick() {
        Iterator<Item> it = items.iterator();
        while(it.hasNext()) {
            Item i = it.next();
            i.tick();
            if (i.isPickedUp()) it.remove();
        }
    }

    public void render(Graphics g) {
        items.forEach(i -> i.render(g));
    }

    public void addItem(Item i) {
        if(i.isPickedUp()) i.setPickedUp(false);
        i.setAPI(API);
        items.add(i);
    }
}
