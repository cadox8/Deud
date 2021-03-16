package me.cadox8.deud.inventory;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.graphics.fonts.Text;
import me.cadox8.deud.items.Item;
import me.cadox8.deud.ui.NysvaManager;
import me.cadox8.deud.ui.components.images.UIImage;
import me.cadox8.deud.ui.helpers.UIDimension;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public abstract class Inventory {

    protected final GameAPI gameAPI;
    @Getter private final NysvaManager nysvaManager;

    @Getter protected List<Item> items;

    @Getter @Setter protected int selectedSlot;

    @Getter protected HashMap<Equipment, Item> equipment;

    @Getter protected boolean active;

    protected int baseX, baseY, itemX, itemY;

    public Inventory(@NonNull GameAPI gameAPI) {
        this.gameAPI = gameAPI;
        this.nysvaManager = new NysvaManager();

        this.items = new ArrayList<>();

        this.selectedSlot = -1;
        this.equipment = new HashMap<>();
        Arrays.asList(Equipment.values()).forEach(e -> this.equipment.put(e, null));

        //
        this.baseX = 0;
        this.baseY = 0;
        this.itemX = 0;
        this.itemY = 0;
        //

        this.active = false;
    }

    public abstract void tick();
    public abstract void render(Graphics g);
    protected abstract void loadItems();

    protected void drawItemInfo(Graphics g, Item item, int xPosText, int yPosText) {
        String infoText = "";
        if (item != null) infoText = item.getName() + " x" + item.getCount();
        if (item == null) infoText = "---------";
        Text.drawString(g, infoText, xPosText, yPosText, false, 2);
    }

    protected void base(BufferedImage baseImage) {
        final UIImage base = new UIImage(gameAPI, baseImage);
        base.setUiDimension(new UIDimension(this.baseX, this.baseY, baseImage.getWidth(), baseImage.getHeight()));
        // Prevents any interaction
        base.setResize(false);
        base.setClickable(false);
        base.setHoverable(false);
        //

        this.nysvaManager.addObject(base);

        this.loadItems();
    }


    // Inventory methods
    public void addItems(Item... items) {
        Arrays.asList(items).forEach(this::addItem);
    }
    public void addItem(Item item) {
        items.stream().filter(i -> i.getId() == item.getId()).findFirst().ifPresentOrElse(i -> i.addCount(item.getCount()), () -> items.add(item));
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
