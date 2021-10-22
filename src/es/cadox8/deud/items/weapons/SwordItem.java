package es.cadox8.deud.items.weapons;

import es.cadox8.deud.entities.creatures.player.Player;
import es.cadox8.deud.graphics.textures.Assets;
import es.cadox8.deud.items.Item;

public class SwordItem extends WeaponItem {

    public SwordItem() {
        super(Assets.sword, 6, "Sword", 4.5);
    }

    @Override
    public void use(Player p) {
    }

    @Override
    public Item createNew(int x, int y, int count) {
        final Item i = new SwordItem();
        i.setPosition(x, y);
        i.setCount(count);
        return i;
    }
}
