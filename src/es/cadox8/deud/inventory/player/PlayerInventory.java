package es.cadox8.deud.inventory.player;

import es.cadox8.deud.items.Item;
import es.cadox8.deud.items.Items;
import es.cadox8.deud.ui.AarinManager;
import es.cadox8.deud.ui.UiManager;
import es.cadox8.deud.ux.inventory.InventoryUx;
import lombok.NonNull;
import es.cadox8.deud.entities.creatures.player.Player;
import es.cadox8.deud.entities.statics.sign.Sign;
import es.cadox8.deud.graphics.textures.GUI;
import es.cadox8.deud.inventory.Inventory;

import java.awt.*;
import java.awt.event.KeyEvent;

public class PlayerInventory extends Inventory {

    private final InventoryUx inventoryUx;

    public PlayerInventory(@NonNull Player player) {
        super(player.getGameAPI());

        this.uiManager = new UiManager();

        this.inventoryUx = new UIPlayerInventory(player.getGameAPI(), GUI.inventory);
        this.getAarinManager().addObject(this.uiPlayerInventory);

        gameAPI.getMouseManager().setAarinManager(getAarinManager());
    }

    @Override
    public void tick() {
        if (this.gameAPI.getKeyManager().keyJustPressed(KeyEvent.VK_E)) {
            this.active = !this.active;
            gameAPI.getWorld().getPlayer().setFreeze(this.isActive());
            gameAPI.getEntityManager().getEntities().stream().filter(e -> e instanceof Sign).forEach(e -> ((Sign) e).setSign(null));
            setSelectedSlot(-1);

            this.uiPlayerInventory.addItems(this.items);
            //this.uiPlayerInventory.getItems().forEach(i -> this.getAarinManager().addObject(i));
        }
        if (this.isActive() && this.gameAPI.getKeyManager().keyJustPressed(KeyEvent.VK_ESCAPE)) {
            this.active = false;
            gameAPI.getWorld().getPlayer().setFreeze(this.isActive());
        }
        if (!this.isActive()) return;

        if (this.equipment.get(Equipment.HAND) == null) this.equipment.put(Equipment.HAND, Items.HAND.item());
        this.getAarinManager().tick();
    }

    @Override
    public void render(Graphics g) {
        if (!this.isActive()) return;

        getAarinManager().render(g);
    }

    public void setHandItem(Item item) {
        if (item == null) return;
        this.equipment.put(Equipment.HAND, item);
    }
}
