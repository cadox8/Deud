package me.cadox8.deud.inventory;

import lombok.Getter;
import lombok.Setter;
import me.cadox8.deud.api.API;
import me.cadox8.deud.entities.creatures.player.Player;
import me.cadox8.deud.gfx.fonts.Text;
import me.cadox8.deud.gfx.textures.GUI;
import me.cadox8.deud.items.Item;
import me.cadox8.deud.utils.DeudColor;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Inventory {

    @Getter @Setter private API API;
    @Getter @Setter private Player player;
    @Getter @Setter private boolean active = false;

    @Getter private ArrayList<Item> inventoryItems;

    @Getter @Setter private Item usableItem;

    private int selectedItem = 0;

    //Inventory
    private final int invX = 64, invY = 48, invWidth = 512, invHeight = 384, invListCenterX = invX + 171, invListCenterY = invY + invHeight / 2 + 5, invListSpacing = 30;

    private final int invImageX = 452, invImageY = 82, invImageWidth = 64, invImageHeight = 64;

    private final int invCountX = 484, invCountY = 172;

    public Inventory(API API, Player player) {
        this.API = API;
        this.player = player;
        inventoryItems = new ArrayList<>();
    }

    public void tick() {
        if (API.getKeyManager().keyJustPressed(KeyEvent.VK_E)) {
            active = !active;
            API.getWorld().getEntityManager().getPlayer().setFreeze(active);
        }

        if (!active) return;

        if (API.getKeyManager().keyJustPressed(KeyEvent.VK_W)) selectedItem--;
        if (API.getKeyManager().keyJustPressed(KeyEvent.VK_S)) selectedItem++;
        if (API.getKeyManager().keyJustPressed(KeyEvent.VK_Q)) dropItem(inventoryItems.get(selectedItem));
        if (API.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER) && inventoryItems.get(selectedItem) != null) setUsableItem(inventoryItems.get(selectedItem));

        if (selectedItem < 0) selectedItem = inventoryItems.size() - 1;
        if (selectedItem >= inventoryItems.size()) selectedItem = 0;
    }

    public void render(Graphics g) {
        if (!active) return;

        g.drawImage(GUI.inventoryScreen, invX, invY, invWidth, invHeight, null);
        renderInventory(g);
    }

    private void renderInventory(Graphics g) {
        int len = inventoryItems.size();
        if (len == 0) return;

        for (int i = -5; i < 6; i++) {
            if (selectedItem + i < 0 || selectedItem + i >= len) continue;

            switch (i) {
                case 0:
                    Text.drawString(g, "> " + inventoryItems.get(selectedItem + i).getName() + " <", invListCenterX, invListCenterY + i * invListSpacing, true, DeudColor.YELLOW, 0);
                    break;
                default:
                    Text.drawString(g, inventoryItems.get(selectedItem + i).getName(), invListCenterX, invListCenterY + i * invListSpacing, true, DeudColor.WHITE, 0);
                    break;
            }
        }
        Item item = inventoryItems.get(selectedItem);
        g.drawImage(item.getTexture(), invImageX, invImageY, invImageWidth, invImageHeight, null);
        Text.drawString(g, Integer.toString(item.getCount()), invCountX, invCountY, true, DeudColor.WHITE, 0);
    }


    // Inventory methods
    public void addItem(Item item) {
        for (Item i : inventoryItems) {
            if (i.getId() == item.getId()) {
                i.setCount(i.getCount() + item.getCount());
                return;
            }
        }
        inventoryItems.add(item);
    }

    public void removeItem(Item item) {
        if (inventoryItems.size() == 0) return;

        for (Item i : inventoryItems) {
            if (i.getId() == item.getId()) {
                if (i.getCount() - item.getCount() <= 0) {
                    inventoryItems.remove(item);
                    return;
                }
                i.setCount(i.getCount() - item.getCount());
                return;
            }
        }
    }

    private void dropItem(Item item) {
        player.dropItem(item);
        removeItem(item);
    }

    public int keyCount() {
        return inventoryItems.stream().filter(i -> i.getId() == 2).collect(Collectors.toList()).size();
 /*       for (Item i : inventoryItems) if (i.getId() == 2) return i.getCount();
        return 0;*/
    }
}
