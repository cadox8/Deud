package me.cadox8.deud.items.weapons;

import me.cadox8.deud.attributes.Knockback;
import me.cadox8.deud.entities.creatures.player.Player;
import me.cadox8.deud.items.Item;
import me.cadox8.deud.utils.Metadata;

import java.awt.image.BufferedImage;

public class SwordItem extends WeaponItem {

    public SwordItem(BufferedImage texture, int id, String name, int damage) {
        super(texture, id, name, damage);

        addAttributes(new Knockback(this.getGameAPI(), 12));

        addMetadatas(new Metadata("areaRadius", 0.0D));
    }

    @Override
    public void use(Player p) {}

    @Override
    public Item createNew(int x, int y, int count) {
        final Item i = new SwordItem(texture, id, name, getDamage());
        i.setPosition(x, y);
        i.setCount(count);
        return i;
    }
}
