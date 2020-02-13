package me.cadox8.deud.inventory_old;

import lombok.Getter;
import lombok.Setter;
import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.entities.creatures.player.Player;
import me.cadox8.deud.entities.statics.sign.Sign;
import me.cadox8.deud.gfx.textures.GUI;
import me.cadox8.deud.items.Item;

import java.awt.*;
import java.awt.event.KeyEvent;

@Deprecated
public class PlayerInventory extends CreatureInventory {

    @Getter @Setter private Player player;

    private int selectedItem = 0;
    private int selectY = 0;
    private int renderX = 76, renderY = 130;

    private boolean canSelect;

    public PlayerInventory(GameAPI gameAPI, Player player) {
        super(gameAPI);
        this.player = player;
        canSelect = false;
    }

    public void tick() {
        final InventoryData entityInventoryData = player.getEntityInventory();
        StaticInventory entityInventory = null;
        if (gameAPI.getKeyManager().keyJustPressed(KeyEvent.VK_E)) {
            active = !active;
            selected = active;
            if (entityInventoryData != null) {
                entityInventory = entityInventoryData.getInventory();
                entityInventory.setActive(false);
                entityInventory.setSelected(false);
                player.setEntityInventory(null);
                selected = true;
            }
            gameAPI.getWorld().getPlayer().setFreeze(active);
            gameAPI.getEntityManager().getEntities().stream().filter(e -> e instanceof Sign).forEach(e -> ((Sign) e).setSign(null));
        }

        if (!hasItem(getUsableItem())) setUsableItem(Item.hand);

        if (!active) {
            selectedItem = 0;
            selectY = 0;
            return;
        }

        if (!canSelect) { // Prevent sending items on Open
            canSelect = true;
            return;
        }

        if (entityInventoryData != null) entityInventory = entityInventoryData.getInventory();

        if (gameAPI.getKeyManager().keyJustPressed(KeyEvent.VK_RIGHT)) {
            if (entityInventory == null) return;
            selected = false;
            entityInventory.selected = true;
        }
        if (gameAPI.getKeyManager().keyJustPressed(KeyEvent.VK_LEFT)) {
            if (entityInventory == null) return;
            selected = true;
            entityInventory.selected = false;
        }

        if (gameAPI.getKeyManager().keyJustPressed(KeyEvent.VK_SPACE)) {
            if (entityInventoryData == null) {
                setUsableItem(getItem());
                return;
            }

            if (selected) {
                if (entityInventoryData.getType() == InventoryData.InventoryType.SHOP) ((ShopInventory)entityInventory).sellItem(getPlayer());
                sendToInventory(this, entityInventory, getItem());
            } else {
                if (entityInventoryData.getType() == InventoryData.InventoryType.SHOP) if (!((ShopInventory)entityInventory).buyItem(getPlayer())) return;
                sendToInventory(entityInventory, this, entityInventory.getItem());
            }
        }

        if (!selected) return;

        if (gameAPI.getKeyManager().keyJustPressed(KeyEvent.VK_Q)) dropItem(player, getItem());
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
        renderItems(g, getItems(), 76, 130, 255, 646);

        //
        if (selected) g.drawImage(GUI.invSelector, renderX, renderY, null);
    }

    public void setUsableItem(Item item) {
        if (item == null) return;
        this.usableItem = item;
    }
}
