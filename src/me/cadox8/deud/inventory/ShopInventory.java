package me.cadox8.deud.inventory;

import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.entities.creatures.player.Player;
import me.cadox8.deud.gfx.fonts.Text;
import me.cadox8.deud.gfx.textures.GUI;
import me.cadox8.deud.items.Item;

import java.awt.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ShopInventory extends StaticInventory {

    public ShopInventory(GameAPI gameAPI) {
        super(gameAPI);
    }

    public void render(Graphics g) {
        if (!active) return;

        g.drawImage(GUI.chest, 650, 50, null);
        //

        // Render items
        final AtomicInteger xSlot = new AtomicInteger(0);
        final AtomicInteger ySlot = new AtomicInteger(0);
        final int xStart = 676, yStart = 130;
        items.forEach(i -> {
            if (ySlot.get() > 6) return;
            g.drawImage(i.getTexture(), xStart + (xSlot.get() * 64), yStart + (ySlot.get() * 64), 60, 60, null);
            xSlot.incrementAndGet();
            if (xSlot.get() > 6) {
                xSlot.set(0);
                ySlot.incrementAndGet();
            }
        });

        // Draw item text
        String infoText;
        if (getItemStaticInv() == null) {
            infoText = "---------";
        } else {
            infoText = getItemStaticInv().getName() + " x" + getItemStaticInv().getCount() + "(" + getItemStaticInv().getBuyAmount() + "€)";
        }
        Text.drawString(g, infoText, 855, 646, false, 2);

        //
        if (selected) g.drawImage(GUI.invSelector, renderX, renderY, null);
    }

    public Item getItemStaticInv() {
        int slot;
        if (selectY > 0) {
            slot = ((6 * (selectY + 1)) + 1) - 6 + selectX;
        } else {
            slot = selectX;
        }
        if (slot > items.size() - 1) return null;
        return items.get(slot);
    }

    public void buyItem(Player player) {
        if (!player.hasMoney(getItemStaticInv().getBuyAmount())) return;
        player.setMoney(player.getMoney() - getItemStaticInv().getBuyAmount());
    }

    public void sellItem(Player player) {
        player.setMoney(player.getMoney() + getItemStaticInv().getSellAmount());
    }

    protected void sendToInventory(Inventory from, Inventory to, Item item) {
        if (item == null || to == null || from == null) return;
        from.removeItem(item);
        to.addItem(item);
    }
}
