package es.cadox8.deud.items.objects;

import es.cadox8.deud.entities.creatures.player.Player;
import es.cadox8.deud.graphics.textures.Assets;
import es.cadox8.deud.items.Item;

public class KeyItem extends ObjectItem {

    public KeyItem() {
        super(Assets.key, 2, "Key");
    }

    @Override
    public void use(Player p) {

    }

    @Override
    public Item createNew(int x, int y, int count) {
        final Item i = new KeyItem();
        i.setPosition(x, y);
        i.setCount(count);
        return i;
    }
}
