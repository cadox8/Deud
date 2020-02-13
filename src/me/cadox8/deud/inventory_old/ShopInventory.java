package me.cadox8.deud.inventory_old;

import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.entities.creatures.player.Player;
import me.cadox8.deud.gfx.fonts.Text;
import me.cadox8.deud.gfx.textures.Assets;

import java.awt.*;

@Deprecated
public class ShopInventory extends StaticInventory {

    public ShopInventory(GameAPI gameAPI) {
        super(gameAPI);
    }

    public void render(Graphics g) {
        if (!active) return;

        // Draw item price
        String infoText;
        if (getItem().getId() == 5) {
            infoText = "--";
        } else {
            infoText = getItem().getBuyAmount() + "";
        }
        Text.drawString(g, infoText, 855, 680, false, 2);
        g.drawImage(Assets.coin, 835, 680, null);
    }

    public boolean buyItem(Player player) {
        if (!player.hasMoney(getItem().getBuyAmount())) return false;
        player.setMoney(player.getMoney() - getItem().getBuyAmount());
        return true;
    }

    public void sellItem(Player player) {
        player.setMoney(player.getMoney() + getItem().getSellAmount());
    }
}
