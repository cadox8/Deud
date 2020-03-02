package me.cadox8.deud.inventory;

import lombok.Getter;
import lombok.Setter;
import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.entities.Entity;
import me.cadox8.deud.gfx.fonts.Text;
import me.cadox8.deud.gfx.textures.GUI;
import me.cadox8.deud.items.Item;
import me.cadox8.deud.nysvaui.NysvaManager;
import me.cadox8.deud.nysvaui.NysvaUI;
import me.cadox8.deud.nysvaui.components.images.UIImage;
import me.cadox8.deud.nysvaui.components.images.UIImageButton;
import me.cadox8.deud.nysvaui.helpers.UIDimension;
import me.cadox8.deud.utils.Log;

import java.awt.*;
import java.lang.reflect.Array;
import java.rmi.server.UID;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class Inventory {

    @Getter @Setter protected GameAPI gameAPI;

    @Getter protected ArrayList<Item> items;

    @Getter @Setter private int size;

    @Getter protected boolean active;

    @Getter @Setter private int selectedSlot;

    @Getter private final NysvaManager nysvaManager;

    public Inventory(GameAPI gameAPI) {
        this.gameAPI = gameAPI;
        items = new ArrayList<>();

        size = 10;
        active = false;
        selectedSlot = -1;

        nysvaManager = new NysvaManager();
    }

    public abstract void tick();
    public abstract void render(Graphics g);

    protected void loadBaseInventory(int xPos, int yPos) {
        final UIImage gui = new UIImage(gameAPI, GUI.chest);
        gui.setUiDimension(new UIDimension(650, 50, GUI.chest.getWidth(), GUI.chest.getHeight()));
        gui.setResize(false);

        getNysvaManager().addObject(gui);

        loadItems(xPos, yPos);

        gameAPI.getMouseManager().setNysvaUI(nysvaManager);
    }

    private void loadItems(int xPos, int yPos) {
        final AtomicInteger xSlot = new AtomicInteger(0);
        final AtomicInteger ySlot = new AtomicInteger(0);

        items.forEach(i -> {
            if (ySlot.get() > 6) return;

            final UIImageButton item = new UIImageButton(gameAPI, i.getTexture(), () -> {
                if (selectedSlot == -1) {
                    selectedSlot = items.indexOf(i);
                } else {
                    final Item selectedItem = getItems().get(selectedSlot);
                    final Item newItem = getItems().get(getItems().indexOf(i));
                    getItems().set(getItems().indexOf(i), selectedItem);
                    getItems().set(selectedSlot, newItem);

                    selectedSlot = -1;
                }
            });
            item.setUiDimension(new UIDimension(xPos + (xSlot.get() * 64) + 1, yPos + (ySlot.get() * 64) + 1, 60, 60));
            item.setExtraData(items.indexOf(i));
            item.setReorder(true);

            getNysvaManager().addObject(item);

            xSlot.incrementAndGet();
            if (xSlot.get() > 6) {
                xSlot.set(0);
                ySlot.incrementAndGet();
            }
        });
    }

    protected void drawItemInfo(Graphics g, Item item, int xPosText, int yPosText) {
        String infoText = "";
        if (item != null) infoText = item.getName() + " x" + item.getCount();
        if (item == null || item.getId() == 5) infoText = "---------";
        Text.drawString(g, infoText, xPosText, yPosText, false, 2);
    }

    protected void hoverSelector(Graphics g, int xPosText, int yPosText) {
        final Optional<NysvaUI> hover = getNysvaManager().getObjects().stream().filter(n -> n instanceof UIImageButton).filter(NysvaUI::isHovering).findAny();
        hover.ifPresentOrElse(nysvaUI -> {
            g.drawImage(GUI.invSelector, nysvaUI.getUiDimension().getX(), nysvaUI.getUiDimension().getY(), null);
            drawItemInfo(g, items.get((int)hover.get().getExtraData()), 855, 646);
        }, () -> drawItemInfo(g, null, xPosText, yPosText));
    }

    public void setActive(boolean active) {
        loadBaseInventory(676, 130);
        this.active = active;
    }

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

    public boolean hasItem(Item item) {
        return hasItem(item.getId());
    }
    public boolean hasItem(int item) {
        return items.stream().anyMatch(i -> i.getId() == item);
    }

    public int itemCount(Item item) {
        return (int) items.stream().filter(i -> i.getId() == item.getId()).count();
    }
}
