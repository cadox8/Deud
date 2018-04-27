package me.cadox8.deud.items.objects;

import me.cadox8.deud.items.Item;

import java.awt.image.BufferedImage;

public abstract class ObjectItem extends Item {

    public ObjectItem(BufferedImage texture, int id, String name) {
        super(texture, id, name);
    }

    @Override
    public void use() {}
}
