package es.cadox8.deud.ux.hud;

import lombok.RequiredArgsConstructor;
import es.cadox8.deud.entities.creatures.player.Player;
import es.cadox8.deud.graphics.fonts.Fonts;
import es.cadox8.deud.graphics.fonts.Text;
import es.cadox8.deud.graphics.textures.Assets;
import es.cadox8.deud.graphics.textures.GUI;
import es.cadox8.deud.inventory.Inventory;
import es.cadox8.deud.utils.Utils;

import java.awt.*;
import java.awt.image.BufferedImage;

@RequiredArgsConstructor
public class Hud {

    private final Player player;

    public void render(Graphics g) {
        g.drawImage(GUI.hud, 0, 5, 320, 96, null);
        g.drawImage(GUI.hud2, 12, 106, 50, 50, null);

        //Damage
        drawImage(g, Assets.sword, 0);
        drawString(g, player.getDamage(), 0);

        //Health
        drawImage(g, Assets.hearth, 1);
        drawString(g, player.getHealth() + "/" + player.getMaxHealth(), 1);

        //Food
        drawImage(g, Assets.chicken, 2);
        drawString(g, Utils.round(2, player.getHunger()) + "/" + Utils.round(2, player.getMaxHunger()), 2);

        //Armor
        drawImage(g, Assets.shield, 3);
        drawString(g, player.getArmor(),  3);

        //Item
        g.drawImage(player.getPlayerInventory().getEquipment().get(Inventory.Equipment.HAND).getTexture(), 22, 115, null);
        //Text.drawString(g, player.getPlayerInventory().getUsableItem().getName(), 1150, 686 + Assets.HEIGHT, false, Color.BLACK, 2);
    }

    //
    private void drawImage(Graphics g, BufferedImage image, int pos){
        final int infoY = 20;
        final int infoX = 25;
        int y = infoY;
        int x = infoX;

        if (pos != 0) y += Assets.HEIGHT * pos;

        if (pos >= 2) {
            y = infoY + (Assets.HEIGHT * (pos - 2));
            x = 150;
        }

        g.drawImage(image, x, y, 32, 32, null);
    }

    private void drawString(Graphics g, double value, int pos){
        drawString(g, value + "", pos);
    }
    private void drawString(Graphics g, String text, int pos){
        final int infoY = 41;
        final int infoX = 60;
        int y = infoY;
        int x = infoX;

        if (pos != 0) y += (Assets.HEIGHT * pos);

        if (pos >= 2) {
            y = infoY + (Assets.HEIGHT * (pos - 2));
            x = 185;
        }

        Text.drawString(g, text, x, y, false, Color.WHITE, Fonts.DEUD);
    }
}
