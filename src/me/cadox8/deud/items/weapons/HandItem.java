package me.cadox8.deud.items.weapons;

import me.cadox8.deud.attributes.Knockback;
import me.cadox8.deud.entities.creatures.player.Player;
import me.cadox8.deud.items.Item;

import java.awt.image.BufferedImage;

public class HandItem extends WeaponItem {

    public HandItem(BufferedImage texture, int id, String name, int damage) {
        super(texture, id, name, damage);

        addAttributes(new Knockback(getAPI(), 5));
    }

    @Override
    public void use(Player p) {}

    @Override
    public Item createNew(int x, int y, int count) {
        Item i = new HandItem(texture, id, name, getDamage());
        i.setPosition(x, y);
        i.setCount(count);
        return i;
    }
}
