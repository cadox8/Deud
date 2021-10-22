package me.cadox8.deud.inventory;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.items.Item;
import me.cadox8.deud.ui.AarinManager;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public abstract class Inventory {

    protected final GameAPI gameAPI;
    @Getter protected AarinManager aarinManager;

    @Getter protected List<Item> items;

    @Getter @Setter protected int selectedSlot;

    @Getter protected HashMap<Equipment, Item> equipment;

    @Getter protected boolean active;

    public Inventory(@NonNull GameAPI gameAPI) {
        this.gameAPI = gameAPI;
        this.aarinManager = new AarinManager();

        this.items = new ArrayList<>();

        this.selectedSlot = -1;
        this.equipment = new HashMap<>();
        Arrays.asList(Equipment.values()).forEach(e -> this.equipment.put(e, null));

        this.active = false;
    }

    public abstract void tick();
    public abstract void render(Graphics g);

    public Item getEquipment(Equipment equipment) {
        return this.equipment.get(equipment);
    }

    // Inventory methods
    public void addItems(Item... items) {
        Arrays.asList(items).forEach(this::addItem);
    }
    public void addItem(Item item) {
        items.stream().filter(i -> i.getId() == item.getId()).findAny().ifPresentOrElse(i -> i.addCount(item.getCount()), () -> items.add(item));
    }

    public void setEquipment(Equipment equipment, Item item) {
        this.equipment.put(equipment, item);
    }

    public void removeItem(Item item) {
        this.removeItem(item, -1);
    }
    public void removeItem(Item item, int amount) {
        if (items.size() == 0) return;
        items.stream().filter(it -> it.getId() == item.getId()).findFirst().ifPresent(i -> {
            if (amount == -1) {
                items.remove(item);
                return;
            }
            if (i.getCount() - amount <= 0) {
                items.remove(item);
                return;
            }
            i.setCount(i.getCount() - amount);
        });
    }

    private void dropItem(Item item) {
        removeItem(item);
    }

    public boolean hasItem(Item item) {
        return hasItem(item.getId());
    }
    public boolean hasItem(int item) {
        return items.stream().anyMatch(i -> i.getId() == item);
    }

    public int itemCount(Item item) {
        if (!hasItem(item)) return 0;
        return items.stream().filter(i -> i.getId() == item.getId()).findAny().get().getCount();
    }

    public enum Equipment {
        HAND, HELMET, CHESTPLATE, RING, CHAIN;
    }
}
