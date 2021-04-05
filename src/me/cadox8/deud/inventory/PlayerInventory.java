package me.cadox8.deud.inventory;

import lombok.NonNull;
import me.cadox8.deud.entities.creatures.player.Player;
import me.cadox8.deud.entities.statics.sign.Sign;
import me.cadox8.deud.graphics.textures.GUI;
import me.cadox8.deud.items.Item;
import me.cadox8.deud.items.Items;
import me.cadox8.deud.ui.NysvaUI;
import me.cadox8.deud.ui.components.images.UIImageButton;
import me.cadox8.deud.ui.helpers.UIDimension;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class PlayerInventory extends Inventory {

    public PlayerInventory(@NonNull Player player) {
        super(player.getGameAPI());

        this.baseX = 650;
        this.baseY = 50;
        this.itemX = 676;
        this.itemY = 130;

        gameAPI.getMouseManager().setNysvaUI(getNysvaManager());

        this.base(GUI.inventory);
    }

    @Override
    public void tick() {
        if (this.gameAPI.getKeyManager().keyJustPressed(KeyEvent.VK_E)) {
            this.active = !this.active;
            gameAPI.getWorld().getPlayer().setFreeze(this.isActive());
            gameAPI.getEntityManager().getEntities().stream().filter(e -> e instanceof Sign).forEach(e -> ((Sign) e).setSign(null));
            setSelectedSlot(-1);
        }
        if (this.isActive() && this.gameAPI.getKeyManager().keyJustPressed(KeyEvent.VK_ESCAPE)) {
            this.active = false;
            gameAPI.getWorld().getPlayer().setFreeze(this.isActive());
        }
        if (!this.isActive()) return;

        if (this.equipment.get(Equipment.HAND) == null) this.equipment.put(Equipment.HAND, Items.HAND.item());
        this.getNysvaManager().tick();
    }

    @Override
    public void render(Graphics g) {
        if (!this.isActive()) return;

        getNysvaManager().render(g);

        final Optional<NysvaUI> item = this.getNysvaManager().getObjects().stream().filter(n -> n instanceof UIImageButton).filter(NysvaUI::isHovering).findAny();
        if (item.isPresent()) {
            final UIImageButton button = (UIImageButton) item.get();
            g.drawImage(GUI.invSelector, button.getUiDimension().getX(), button.getUiDimension().getY(), null);
            drawItemInfo(g, items.get((int)button.getExtraData()), 855, 646);
        } else {
            drawItemInfo(g, null, 855, 646);
        }
    }

    @Override
    protected void loadItems() {
        final AtomicInteger xSlot = new AtomicInteger(0);
        final AtomicInteger ySlot = new AtomicInteger(0);

        this.items.forEach(i -> {
            if (ySlot.get() > 6) return;

            final UIImageButton item = new UIImageButton(this.gameAPI, i.getTexture(), () -> {
                this.selectedSlot = this.items.indexOf(i);
                // ToDo: move
            });
            item.setUiDimension(new UIDimension(this.itemX + (xSlot.get() * 64) + 1, this.itemY + (ySlot.get() * 64) + 1, 60, 60));
            item.setReorder(true);
            item.setResize(false);
            item.setExtraData(this.items.indexOf(i));

            this.nysvaManager.addObject(item);
        });

/*        this.getEquipment().values().forEach(i -> {
            if (i == null) return;
            final UIImageButton item = new UIImageButton(this.gameAPI, i.getTexture(), () -> {
                this.selectedSlot = this.items.indexOf(i);
                // ToDo: move
            });
            item.setUiDimension(new UIDimension(this.itemX + (xSlot.get() * 64) + 1, this.itemY + (ySlot.get() * 64) + 1, 60, 60));
            item.setReorder(true);
            item.setResize(false);
            item.setExtraData(this.items.indexOf(i));

            getNysvaManager().addObject(item);
        });*/
    }

    public void setHandItem(Item item) {
        if (item == null) return;
        this.equipment.put(Equipment.HAND, item);
    }
}
