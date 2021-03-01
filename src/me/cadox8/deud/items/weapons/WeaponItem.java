package me.cadox8.deud.items.weapons;

import me.cadox8.deud.items.Item;
import me.cadox8.deud.items.ItemType;

import java.awt.image.BufferedImage;

public abstract class WeaponItem extends Item {

    private WeaponAttributes[] attributes;

    public WeaponItem(BufferedImage texture, int id, String name, double damage) {
        super(texture, id, name, ItemType.WEAPON);

        this.damage = damage;

        this.attributes = new WeaponAttributes[0];
    }

    public enum WeaponAttributes {
        NONE, FLAMMABLE, POISONOUS
    }
}
