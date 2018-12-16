package me.cadox8.deud.items.weapons;

import lombok.Getter;
import lombok.Setter;
import me.cadox8.deud.attributes.Attribute;
import me.cadox8.deud.attributes.Knockback;
import me.cadox8.deud.entities.creatures.Creature;
import me.cadox8.deud.items.Item;

import java.awt.image.BufferedImage;
import java.util.*;

public abstract class WeaponItem extends Item {

    @Getter @Setter private int damage;

    public WeaponItem(BufferedImage texture, int id, String name, int damage) {
        super(texture, id, name);

        this.damage = damage;
    }
}
