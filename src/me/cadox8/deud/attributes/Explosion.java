package me.cadox8.deud.attributes;

import me.cadox8.deud.api.API;
import me.cadox8.deud.entities.Entity;
import me.cadox8.deud.utils.Utils;

public class Explosion extends Attribute {

    private final int radius;
    private final int power;
    private final Entity entity;

    public Explosion(API API, int radius, int power, Entity entity) {
        super(API, 1, "Explosion");

        this.radius = radius;
        this.power = power;
        this.entity = entity;
    }

    @Override
    public void perform() {
        Utils.getNearbyEntities(entity.getLocation(), 5, 30).forEach(e -> e.hurt(entity));
    }
}
