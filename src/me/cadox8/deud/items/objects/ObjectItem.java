package me.cadox8.deud.items.objects;

import me.cadox8.deud.entities.creatures.player.Player;
import me.cadox8.deud.items.Item;
import me.cadox8.deud.items.ItemType;

import java.awt.image.BufferedImage;

public abstract class ObjectItem extends Item {

    public ObjectItem(BufferedImage texture, int id, String name) {
        super(texture, id, name, ItemType.OBJECT);
    }

    @Override
    public void use(Player p) {}
}
