package me.cadox8.deud.items.objects;

import me.cadox8.deud.entities.creatures.player.Player;
import me.cadox8.deud.items.Item;

import java.awt.image.BufferedImage;

public class MapItem extends ObjectItem {

    public MapItem() {
        super(null, 3, "Map");
    }

    @Override
    public void use(Player p) {
    }

    @Override
    public Item createNew(int x, int y, int count) {
        final Item i = new MapItem();
        i.setPosition(x, y);
        i.setCount(count);
        return i;
    }
}
