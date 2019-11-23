package me.cadox8.deud.inventory;

import lombok.Getter;
import lombok.Setter;
import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.entities.creatures.player.Player;
import me.cadox8.deud.items.Item;

import java.awt.*;
import java.awt.event.KeyEvent;

public class PlayerInventory extends CreatureInventory {

    @Getter @Setter private Player player;
    @Getter @Setter private boolean active = false;

    private int selectedItem = 0;

    public PlayerInventory(GameAPI gameAPI, Player player) {
        super(gameAPI);
        this.player = player;
    }

    public void tick() {
        if (gameAPI.getKeyManager().keyJustPressed(KeyEvent.VK_E)) {
            active = !active;
            gameAPI.getWorld().getPlayer().setFreeze(active);
        }
        if (!active) return;

        if (gameAPI.getKeyManager().keyJustPressed(KeyEvent.VK_W)) selectedItem--;
        if (gameAPI.getKeyManager().keyJustPressed(KeyEvent.VK_S)) selectedItem++;
        if (gameAPI.getKeyManager().keyJustPressed(KeyEvent.VK_Q)) dropItem(items.get(selectedItem));
        if (gameAPI.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER) && items.get(selectedItem) != null) setUsableItem(items.get(selectedItem));

        if (selectedItem < 0) selectedItem = items.size() - 1;
        if (selectedItem >= items.size()) selectedItem = 0;
    }

    public void render(Graphics g) {
        if (!active) return;


    }

    private void dropItem(Item item) {
        player.dropItem(item);
        removeItem(item);
    }
}
