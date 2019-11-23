package me.cadox8.deud.inventory;

import lombok.Getter;
import lombok.Setter;
import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.items.Item;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public abstract class Inventory {

    @Getter @Setter protected GameAPI gameAPI;

    @Getter protected ArrayList<Item> items;

    @Getter @Setter protected int size;

    public Inventory(GameAPI gameAPI) {
        this.gameAPI = gameAPI;
        items = new ArrayList<>();

        size = 10;
    }

    public void tick() {}

    public void render(Graphics g) {}

    // Inventory methods
    public void addItems(Item... items) {
        Arrays.asList(items).forEach(this::addItem);
    }
    public void addItem(Item item) {
        items.stream().filter(i -> i.getId() == item.getId()).findFirst().ifPresentOrElse(i -> i.addCount(item.getCount()), () -> {
            if (items.size() == size) return;
            items.add(item);
        });
    }

    public void removeItem(Item item) {
        if (items.size() == 0) return;
        items.stream().filter(it -> it.getId() == item.getId()).findFirst().ifPresent(i -> {
            if (i.getCount() - item.getCount() <= 0) {
                items.remove(item);
                return;
            }
            i.setCount(i.getCount() - item.getCount());
        });
    }

    private void dropItem(Item item) {
        removeItem(item);
    }

    public boolean hasItem(int item) {
        return items.stream().anyMatch(i -> i.getId() == item);
    }

    public int keyCount() {
        return (int) items.stream().filter(i -> i.getId() == 2).count();
    }
}
