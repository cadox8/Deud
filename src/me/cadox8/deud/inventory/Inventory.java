package me.cadox8.deud.inventory;

import lombok.Getter;
import lombok.Setter;
import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.graphics.fonts.Text;
import me.cadox8.deud.graphics.textures.GUI;
import me.cadox8.deud.items.Item;
import me.cadox8.deud.items.Items;
import me.cadox8.deud.ui.NysvaManager;
import me.cadox8.deud.ui.NysvaUI;
import me.cadox8.deud.ui.components.images.UIImage;
import me.cadox8.deud.ui.components.images.UIImageButton;
import me.cadox8.deud.ui.helpers.UIDimension;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class Inventory {

    @Getter @Setter protected GameAPI gameAPI;

    @Getter protected ArrayList<Item> items;

    @Getter @Setter private int size;

    @Getter protected boolean active;

    @Getter @Setter private int selectedSlot;

    @Getter private final NysvaManager nysvaManager;

    @Getter @Setter protected Item usableItem = Items.getHand();

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

    protected void loadBaseInventory(int xPos, int yPos, BufferedImage baseTexture) {
        final UIImage gui = new UIImage(gameAPI, baseTexture);
        gui.setUiDimension(new UIDimension(650, 50, GUI.chest.getWidth(), GUI.chest.getHeight()));
        gui.setResize(false);
        gui.setClickable(false);

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
                if (selectedSlot == -1) selectedSlot = getItems().indexOf(i);

                if (getItems().get(selectedSlot).getId() == 7 && getUsableItem().getId() != 7) setUsableItem(getItems().get(selectedSlot));
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
        if (item == null || item.getId() == Items.getBugItem().getId()) infoText = "---------";
        Text.drawString(g, infoText, xPosText, yPosText, false, 2);
    }

    protected void hoverSelector(Graphics g, int xPosText, int yPosText) {
        final Optional<NysvaUI> hover = getNysvaManager().getObjects().stream().filter(n -> n instanceof UIImageButton).filter(NysvaUI::isHovering).findAny();
        hover.ifPresentOrElse(nysvaUI -> {
            if ((int)nysvaUI.getExtraData() == 109994) {
                drawItemInfo(g, null, xPosText, yPosText);
                return;
            }
            g.drawImage(GUI.invSelector, nysvaUI.getUiDimension().getX(), nysvaUI.getUiDimension().getY(), null);
            drawItemInfo(g, items.get((int)hover.get().getExtraData()), 855, 646);
        }, () -> drawItemInfo(g, null, xPosText, yPosText));
    }

    public void setActive(boolean active, BufferedImage baseTexture) {
        loadBaseInventory(676, 130, baseTexture);
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
