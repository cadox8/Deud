package me.cadox8.deud.items.objects;

import me.cadox8.deud.graphics.textures.Assets;
import me.cadox8.deud.items.Item;

public class WoodItem extends ObjectItem {

    public WoodItem() {
        super(Assets.wood, 0, "Wood");
    }

    @Override
    public Item createNew(int x, int y, int count) {
        final Item i = new WoodItem();
        i.setPosition(x, y);
        i.setCount(count);
        return i;
    }
}
