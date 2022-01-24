package es.cadox8.deud.ux.hud;

import es.cadox8.deud.ui.UiComponent;
import es.cadox8.deud.ui.components.image.UiImage;
import es.cadox8.deud.ui.components.text.UiText;
import es.cadox8.deud.ui.helpers.UiDimension;
import es.cadox8.deud.entities.creatures.player.Player;
import es.cadox8.deud.graphics.textures.Assets;
import es.cadox8.deud.graphics.textures.GUI;
import es.cadox8.deud.entities.components.inventory.Inventory;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Hud {

    private final Player player;

    private final List<UiComponent> components;

    public Hud(Player player) {
        this.player = player;
        this.components = new ArrayList<>();

        // --- Hud ---
        final UiImage hud = new UiImage(GUI.hud);
        hud.setUiDimension(new UiDimension(0, 5, 320, 96));
        final UiImage hud2 = new UiImage(GUI.hud2);
        hud2.setUiDimension(new UiDimension(12, 106, 50, 50));
        // --- ---

        // --- Health ---
        final UiImage healthIcon = new UiImage(Assets.hearth);
        healthIcon.setUiDimension(new UiDimension(25, 20, 32, 32));

        final UiText health = new UiText(player.getHealth() + "/" + player.getMaxHealth());
        health.setUiDimension(new UiDimension(60, 41, 32, 32));
        // --- ---

        // --- Stamina ---
        final UiImage staminaIcon = new UiImage(Assets.chicken);
        healthIcon.setUiDimension(new UiDimension(25, 52, 32, 32));

        final UiText stamina = new UiText(player.getStamina() + "/" + player.getMaxStamina());
        stamina.setUiDimension(new UiDimension(60, 73, 32, 32));
        // --- ---

        // --- Item ---
        final UiImage mainItem = new UiImage(this.player.getPlayerInventory().getItemInHand().getTexture());
        healthIcon.setUiDimension(new UiDimension(22, 115, 32, 32));
        // --- ---

        this.components.addAll(Arrays.asList(hud, hud2, healthIcon, health, staminaIcon, stamina, mainItem));
    }

    public void tick() {
        ((UiImage) this.components.get(6)).setImage(this.player.getPlayerInventory().getItemInHand().getTexture());
    }

    public void render(Graphics g) {
        this.components.forEach(c -> c.render(g));
/*        g.drawImage(GUI.hud, 0, 5, 320, 96, null);
        g.drawImage(GUI.hud2, 12, 106, 50, 50, null);

        //Damage
        drawImage(g, Assets.sword, 0);
        drawString(g, player.getDamage(), 0);

        //Health
        drawImage(g, Assets.hearth, 1);
        drawString(g, player.getHealth() + "/" + player.getMaxHealth(), 1);

        //Food
        drawImage(g, Assets.chicken, 2);
        drawString(g, Utils.round(2, player.getStamina()) + "/" + Utils.round(2, player.getMaxStamina()), 2);

        //Armor
        drawImage(g, Assets.shield, 3);
        drawString(g, player.getArmor(),  3);

        //Item
        g.drawImage(player.getPlayerInventory().getEquipment().get(Inventory.Equipment.HAND).getTexture(), 22, 115, null);
        //Text.drawString(g, player.getPlayerInventory().getUsableItem().getName(), 1150, 686 + Assets.HEIGHT, false, Color.BLACK, 2);*/
    }
}
