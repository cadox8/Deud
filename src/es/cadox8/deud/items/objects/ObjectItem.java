package es.cadox8.deud.items.objects;

import es.cadox8.deud.entities.creatures.player.Player;
import es.cadox8.deud.items.Item;

import java.awt.image.BufferedImage;

public abstract class ObjectItem extends Item {

    public ObjectItem(BufferedImage texture, int id, String name) {
        super(texture, id, name, ItemType.OBJECT);
    }

    @Override
    public void use(Player p) {}
}
