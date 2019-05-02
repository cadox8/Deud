package me.cadox8.deud.attributes;

import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.entities.Entity;
import me.cadox8.deud.utils.Utils;

public class Explosion extends Attribute {

    private final int radius;
    private final int power;

    public Explosion(GameAPI GameAPI, int radius, int power) {
        super(GameAPI, 1, "Explosion");

        this.radius = radius;
        this.power = power;
    }

    @Override
    public void perform(Entity damager, Entity damaged) {
        Utils.getNearbyEntities(damager.getLocation(), 5, 30).forEach(e -> e.hurt(damager));
    }
}
