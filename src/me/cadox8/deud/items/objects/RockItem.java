package me.cadox8.deud.items.objects;

import me.cadox8.deud.items.Item;

import java.awt.image.BufferedImage;

public class RockItem extends ObjectItem {

    public RockItem(BufferedImage texture, int id, String name) {
        super(texture, id, name);
    }

    @Override
    public Item createNew(int x, int y, int count) {
        final Item i = new RockItem(texture, id, name);
        i.setPosition(x, y);
        i.setCount(count);
        return i;
    }
}
