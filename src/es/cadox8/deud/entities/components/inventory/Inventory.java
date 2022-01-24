package es.cadox8.deud.entities.components.inventory;

import es.cadox8.deud.api.GameAPI;
import es.cadox8.deud.entities.components.AbstractComponent;
import es.cadox8.deud.items.Item;
import es.cadox8.deud.items.Items;
import es.cadox8.deud.ui.UiManager;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public abstract class Inventory extends AbstractComponent {

    protected final GameAPI gameAPI;

    @Getter protected InventoryType inventoryType;

    @Getter protected List<Item> items;

    @Getter @Setter protected int selectedSlot;

    @Getter @Setter protected boolean active;

    @Getter protected UiManager uiManager;

    public Inventory(InventoryType inventoryType) {
        this.gameAPI = GameAPI.getInstance();
        this.inventoryType = inventoryType;
        this.items = new ArrayList<>();
        this.selectedSlot = -1;
        this.active = false;
        this.uiManager = new UiManager();
    }


    public abstract void open();
    public abstract void tick();
    public abstract void render(Graphics g);


    public void add(Item... items) {
        Arrays.asList(items).forEach(this::add);
    }

    public void add(Item item) {
        this.items.stream().filter(i -> i.getId() == item.getId()).findAny().ifPresentOrElse(i -> i.addCount(item.getCount()), () -> this.items.add(item));
    }

    public void remove(Item... items) {
        Arrays.asList(items).forEach(this::remove);
    }

    public void remove(Item item) {
        this.remove(item, this.amount(item));
    }

    public void remove(Item item, int amount) {
        if (this.items.isEmpty()) return;
        this.items.stream().filter(it -> it.getId() == item.getId()).findAny().ifPresent(i -> {
            if (i.getCount() - amount <= 0) {
                this.items.remove(item);
            } else {
                i.setCount(i.getCount() - amount);
            }
        });
    }

    public boolean has(Item item) {
        return this.has(item.getId());
    }

    public boolean has(int item) {
        return this.items.stream().anyMatch(i -> i.getId() == item);
    }

    public int amount(Item item) {
        return this.amount(item.getId());
    }

    public int amount(int item) {
        if (!this.has(item)) return 0;
        return this.items.stream().filter(i -> i.getId() == item).findAny().get().getCount();
    }

    public void clear() {
        this.items.clear();
    }

    public enum InventoryType {
        PLAYER, CHEST, SHOP, CREATURE;
    }
}
