package es.cadox8.deud.items.weapons;

import es.cadox8.deud.entities.creatures.player.Player;
import es.cadox8.deud.graphics.textures.Assets;
import es.cadox8.deud.items.Item;

public class HandItem extends WeaponItem {

    public HandItem() {
        super(Assets.hand, 7, "Hand", 0);
    }

    @Override
    public void use(Player p) {
    }

    @Override
    public Item createNew(int x, int y, int count) {
        final Item i = new HandItem();
        i.setPosition(x, y);
        i.setCount(count);
        return i;
    }
}
