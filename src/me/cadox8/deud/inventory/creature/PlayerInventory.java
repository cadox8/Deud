package me.cadox8.deud.inventory.creature;

import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.entities.creatures.player.Player;
import me.cadox8.deud.entities.statics.sign.Sign;
import me.cadox8.deud.graphics.textures.GUI;
import me.cadox8.deud.items.Item;
import me.cadox8.deud.items.Items;
import me.cadox8.deud.nysvaui.components.images.UIImageButton;
import me.cadox8.deud.nysvaui.helpers.UIDimension;

import java.awt.*;
import java.awt.event.KeyEvent;

public class PlayerInventory extends CreatureInventory {

    private final Player player;

    private final UIImageButton selectedItem;

    public PlayerInventory(GameAPI gameAPI, Player player) {
        super(gameAPI);
        this.player = player;

        gameAPI.getMouseManager().setNysvaUI(getNysvaManager());

        selectedItem = new UIImageButton(gameAPI, getUsableItem().getTexture(), () -> {});
        selectedItem.setUiDimension(new UIDimension(676 + 5 + 64, 130 + 5 + (64 * 6), 55, 55));
    }

    @Override
    public void tick() {
        if (gameAPI.getKeyManager().keyJustPressed(KeyEvent.VK_E)) {
            setActive(!isActive(), GUI.inventory);
            gameAPI.getWorld().getPlayer().setFreeze(isActive());
            gameAPI.getEntityManager().getEntities().stream().filter(e -> e instanceof Sign).forEach(e -> ((Sign) e).setSign(null));
            setSelectedSlot(-1);
        }

        if (!isActive()) return;

        if (!hasItem(getUsableItem())) setUsableItem(Items.getHand());

        getNysvaManager().tick(getItems());
        selectedItem.tick();
    }

    @Override
    public void render(Graphics g) {
        if (!isActive()) return;

        getNysvaManager().render(g);
        selectedItem.render(g);
        hoverSelector(g, 855, 646);

        //getNysvaManager().getObjects().forEach(o -> o.renderUIDimension(g));
    }

    public void setUsableItem(Item item) {
        if (item == null) return;
        this.usableItem = item;
    }
}
