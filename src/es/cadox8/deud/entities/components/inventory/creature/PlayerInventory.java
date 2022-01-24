package es.cadox8.deud.entities.components.inventory.creature;

import es.cadox8.deud.entities.creatures.player.Player;
import es.cadox8.deud.graphics.textures.GUI;
import es.cadox8.deud.ui.components.button.UiImageButton;
import es.cadox8.deud.ui.components.button.UiSelectedImageButton;
import es.cadox8.deud.ui.components.image.UiImage;
import es.cadox8.deud.ui.helpers.UiDimension;
import lombok.NonNull;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.security.Guard;
import java.util.concurrent.atomic.AtomicInteger;

public class PlayerInventory extends CreatureInventory {

    public PlayerInventory(@NonNull Player player) {
        super(player);
    }

    @Override
    public void open() {
        this.setActive(!this.isActive());

        this.creature.setFreeze(this.isActive());

        this.setSelectedSlot(-1);

        // --- Load UX ---
        final AtomicInteger xSlot = new AtomicInteger(0);
        final AtomicInteger ySlot = new AtomicInteger(0);

        final UiImage base = new UiImage(GUI.inventory);
        base.setUiDimension(676, 130);

        this.getUiManager().addComponent(base);

        this.items.forEach(item -> {
            if (ySlot.get() > 6) return;

            final UiSelectedImageButton itemButton = new UiSelectedImageButton(item.getTexture(), GUI.invSelector, () -> {
                this.setSelectedSlot(this.items.indexOf(item));
            });
            itemButton.setLayer(4);
            itemButton.setUiDimension(new UiDimension(676 + (xSlot.get() * 64) + 1, 130 + (ySlot.get() * 64) + 1, 60, 60));

            this.getUiManager().addComponent(itemButton);

            xSlot.incrementAndGet();
            if (xSlot.get() > 6) {
                xSlot.set(0);
                ySlot.incrementAndGet();
            }
        });

        // --- ---

        this.gameAPI.getMouseManager().setUiManager(this.getUiManager());
    }

    @Override
    public void tick() {
        if (this.gameAPI.getKeyManager().keyJustPressed(KeyEvent.VK_E)) this.open();

        if (this.isActive() && this.gameAPI.getKeyManager().keyJustPressed(KeyEvent.VK_ESCAPE)) {
            this.setActive(false);
            gameAPI.getWorld().getPlayer().setFreeze(this.isActive());
        }
        if (this.isActive()) this.getUiManager().tick();
    }

    @Override
    public void render(Graphics g) {
        if (this.isActive()) this.getUiManager().render(g);
    }
}
