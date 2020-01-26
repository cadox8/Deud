package me.cadox8.deud.inventory;

import lombok.NonNull;
import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.entities.Entity;
import me.cadox8.deud.gfx.fonts.Text;
import me.cadox8.deud.gfx.textures.GUI;
import me.cadox8.deud.items.Item;
import me.cadox8.deud.utils.Log;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class StaticInventory extends Inventory {

    protected int selectX = 0;
    protected int selectY = 0;

    protected int renderX = 676, renderY = 130;

    public StaticInventory(GameAPI gameAPI) {
        super(gameAPI);
    }

    public void checkKeys() {
        if (!selected || !active) return;

        if (gameAPI.getKeyManager().keyJustPressed(KeyEvent.VK_A)) selectX--;
        if (gameAPI.getKeyManager().keyJustPressed(KeyEvent.VK_D)) selectX++;
        if (gameAPI.getKeyManager().keyJustPressed(KeyEvent.VK_W)) selectY--;
        if (gameAPI.getKeyManager().keyJustPressed(KeyEvent.VK_S)) selectY++;

        if (selectY < 0) selectY = 0;
        if (selectY > 5) selectY = 5;
        if (selectX < 0) selectX = 0;
        if (selectX > 6) selectX = 6;
    }

    public void tick() {
        if (!active) {
            selectX = 0;
            selectY = 0;
            return;
        }

        if (!selected) return;

        renderY = 130 + (selectY * 64);
        renderX = 676 + (selectX * 64);
    }

    public void render(Graphics g) {
        if (!active) return;

        g.drawImage(GUI.chest, 650, 50, null);
        //

        // Render items
        renderItems(g, getItems(), 676, 130, 855, 646);

        //
        if (selected) g.drawImage(GUI.invSelector, renderX, renderY, null);
    }

    public Item getItem() {
        if (!selected) return Item.bugItem;
        int slot;
        if (selectY > 0) {
            slot = ((6 * (selectY + 1)) + 1) - 6 + selectX;
        } else {
            slot = selectX;
        }
        if (slot > items.size() - 1) return Item.bugItem;
        return items.get(slot);
    }

    protected void dropItem(@NonNull Entity entity, @NonNull Item item) {
        entity.dropItem(item);
        removeItem(item);
    }

    protected void sendToInventory(Inventory from, Inventory to, Item item) {
        if (item == null || to == null || from == null || item.getId() == 5) return;
        from.removeItem(item);
        to.addItem(item);
    }

    protected void renderItems(Graphics g, ArrayList<Item> items, int xPos, int yPos, int xPosText, int yPosText) {
        final AtomicInteger xSlot = new AtomicInteger(0);
        final AtomicInteger ySlot = new AtomicInteger(0);
        items.forEach(i -> {
            if (ySlot.get() > 6) return;
            g.drawImage(i.getTexture(), xPos + (xSlot.get() * 64), yPos + (ySlot.get() * 64), 60, 60, null);
            xSlot.incrementAndGet();
            if (xSlot.get() > 6) {
                xSlot.set(0);
                ySlot.incrementAndGet();
            }
        });

        // Draw item text
        String infoText;
        if (getItem().getId() == 5) {
            infoText = "---------";
        } else {
            infoText = getItem().getName() + " x" + getItem().getCount();
        }
        Text.drawString(g, infoText, xPosText, yPosText, false, 2);
    }
}
