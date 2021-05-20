package me.cadox8.deud.managers;

import lombok.NonNull;
import me.cadox8.deud.entities.enums.EntityType;
import me.cadox8.deud.items.Item;
import me.cadox8.deud.items.weapons.WeaponItem;

@Deprecated
public class DamageManager {

    public int effectiveDamage(double initialDamage, @NonNull EntityType damaged, Item weapon) {
        return (int) effectiveDamageDouble(initialDamage, damaged, weapon);
    }
    private double effectiveDamageDouble(double initialDamage, @NonNull EntityType damaged, Item weapon) {
        if (weapon == null) return initialDamage;
        if (!(weapon instanceof WeaponItem)) return initialDamage;
        final int id = weapon.getId();

        switch (damaged) {
            case DEADTREE:
            case NORMALTREE:
                if (id == 5) return initialDamage + (initialDamage * 0.84);
                return initialDamage - (initialDamage * 0.57);
            case ROCK:
                if (id == 5)  return initialDamage + (initialDamage * 0.97);
                return initialDamage - (initialDamage * 0.86);

            default:
                return initialDamage;
        }
    }
}
