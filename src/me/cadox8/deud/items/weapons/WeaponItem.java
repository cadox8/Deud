package me.cadox8.deud.items.weapons;

import lombok.Getter;
import lombok.Setter;
import me.cadox8.deud.items.Item;
import me.cadox8.deud.items.ItemType;

import java.awt.image.BufferedImage;

public abstract class WeaponItem extends Item {

    @Getter @Setter private int damage;

    public WeaponItem(BufferedImage texture, int id, String name, int damage) {
        super(texture, id, name);

        this.damage = damage;
        this.type = ItemType.WEAPON;
    }
}
