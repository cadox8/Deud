package me.cadox8.deud.items.objects;

import me.cadox8.deud.entities.creatures.player.Player;
import me.cadox8.deud.items.Item;
import me.cadox8.deud.worlds.Minimap;

import java.awt.image.BufferedImage;

public class MapItem extends ObjectItem {

    private Minimap map;

    public MapItem(BufferedImage texture, int id, String name) {
        super(texture, id, name);

        //map = new Minimap(gameAPI.getWorld());
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
