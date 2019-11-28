package me.cadox8.deud.inventory;

import lombok.Getter;
import lombok.Setter;
import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.entities.creatures.player.Player;
import me.cadox8.deud.entities.statics.sign.SignEntity;
import me.cadox8.deud.gfx.fonts.Text;
import me.cadox8.deud.gfx.textures.GUI;
import me.cadox8.deud.items.Item;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class PlayerInventory extends CreatureInventory {

    @Getter @Setter private Player player;

    private int selectedItem = 0;
    private int selectY = 0;
    private int renderX = 76, renderY = 130;

    public PlayerInventory(GameAPI gameAPI, Player player) {
        super(gameAPI);
        this.player = player;
    }

    public void tick() {
        final StaticInventory chest = player.getChest();
        if (gameAPI.getKeyManager().keyJustPressed(KeyEvent.VK_E)) {
            active = !active;
            selected = active;
            if (chest != null) {
                chest.setActive(false);
                chest.setSelected(false);
                player.setChest(null);
                selected = true;
            }
            gameAPI.getWorld().getPlayer().setFreeze(active);
            gameAPI.getEntityManager().getEntities().stream().filter(e -> e instanceof SignEntity).forEach(e -> ((SignEntity) e).setSign(null));
        }

        if (!hasItem(getUsableItem())) setUsableItem(Item.hand);

        if (!active) {
            selectedItem = 0;
            selectY = 0;
            return;
        }

        if (gameAPI.getKeyManager().keyJustPressed(KeyEvent.VK_RIGHT)) {
            if (chest == null) return;
            selected = false;
            chest.selected = true;
        }
        if (gameAPI.getKeyManager().keyJustPressed(KeyEvent.VK_LEFT)) {
            if (chest == null) return;
            selected = true;
            chest.selected = false;
        }

        if (gameAPI.getKeyManager().keyJustPressed(KeyEvent.VK_SPACE)) {
            if (selected) {
                if (chest == null) {
                    setUsableItem(getItem());
                } else {
                    sendToInventory(this, chest, getItem());
                }
            } else {
                sendToInventory(chest, this, chest.getItemStaticInv());
            }
        }

        if (!selected) return;

        if (gameAPI.getKeyManager().keyJustPressed(KeyEvent.VK_Q)) dropItem(getItem());
        if (gameAPI.getKeyManager().keyJustPressed(KeyEvent.VK_BACK_SPACE)) setUsableItem(Item.hand);

        if (gameAPI.getKeyManager().keyJustPressed(KeyEvent.VK_A)) selectedItem--;
        if (gameAPI.getKeyManager().keyJustPressed(KeyEvent.VK_D)) selectedItem++;
        if (gameAPI.getKeyManager().keyJustPressed(KeyEvent.VK_W)) selectY--;
        if (gameAPI.getKeyManager().keyJustPressed(KeyEvent.VK_S)) selectY++;

        if (selectY < 0) selectY = 0;
        if (selectY > 5) selectY = 5;
        if (selectedItem < 0) selectedItem = 0;
        if (selectedItem > 6) selectedItem = 6;

        renderY = 130 + (selectY * 64);
        renderX = 76 + (selectedItem * 64);
    }

    public void render(Graphics g) {
        if (!active) return;

        g.drawImage(GUI.inventory, 50, 50, null);
        //

        // Render items
        final AtomicInteger xSlot = new AtomicInteger(0);
        final AtomicInteger ySlot = new AtomicInteger(0);
        final int xStart = 76, yStart = 130;
        items.forEach(i -> {
            if (ySlot.get() > 6) return;
            g.drawImage(i.getTexture(), xStart + (xSlot.get() * 64), yStart + (ySlot.get() * 64), 60, 60, null);
            xSlot.incrementAndGet();
            if (xSlot.get() > 6) {
                xSlot.set(0);
                ySlot.incrementAndGet();
            }
        });

        // Render Selected Items
        g.drawImage(getUsableItem().getTexture(), xStart + 64, yStart + (64 * 6), 60, 60, null);

        // Draw item text
        String infoText;
        if (getItem() == null) {
            infoText = "---------";
        } else {
            infoText = getItem().getName() + " x" + getItem().getCount();
        }
        Text.drawString(g, infoText, 255, 646, false, 2);

        //
        if (selected) g.drawImage(GUI.invSelector, renderX, renderY, null);
    }

    private Item getItem() {
        int slot;
        if (selectY > 0) {
            slot = ((6 * (selectY + 1)) + 1) - 6 + selectedItem;
        } else {
            slot = selectedItem;
        }
        if (slot > items.size() - 1) return null;
        return items.get(slot);
    }

    private void dropItem(Item item) {
        if (item == null) return;
        final Random r = new Random();
        final int amount = r.nextInt(15) + 45;

        player.dropItem(item, 1, (int)player.getX() + (r.nextBoolean() ? amount : -amount), (int)player.getY() + (r.nextBoolean() ? amount : -amount));
        removeItem(item);
    }

    public void setUsableItem(Item item) {
        if (item == null) return;
        this.usableItem = item;
    }
}
