package me.cadox8.deud.items.objects;

import me.cadox8.deud.entities.creatures.player.Player;
import me.cadox8.deud.items.Item;

import java.awt.image.BufferedImage;

public class MapItem extends ObjectItem {

    public MapItem(BufferedImage texture, int id, String name) {
        super(texture, id, name);
    }

    @Override
    public void use(Player p) {
    }

    @Override
    public Item createNew(int x, int y, int count) {
        final Item i = new BugItem(texture, id, name);
        i.setPosition(x, y);
        i.setCount(count);
        return i;
    }
}
