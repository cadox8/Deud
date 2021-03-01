package me.cadox8.deud.items.objects;

import me.cadox8.deud.graphics.textures.Assets;
import me.cadox8.deud.items.Item;

public class StoneItem extends ObjectItem {

    public StoneItem() {
        super(Assets.stone, 1, "Stone");
    }

    @Override
    public Item createNew(int x, int y, int count) {
        final Item i = new StoneItem();
        i.setPosition(x, y);
        i.setCount(count);
        return i;
    }
}
