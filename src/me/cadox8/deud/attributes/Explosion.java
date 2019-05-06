package me.cadox8.deud.attributes;

import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.entities.Entity;
import me.cadox8.deud.utils.Utils;

public class Explosion extends Attribute {

    private final int radius;
    private final double power;

    private Entity damager;

    public Explosion(GameAPI gameAPI, int radius, double power) {
        super(gameAPI, 1, "Explosion");

        this.radius = radius;
        this.power = power;
    }

    @Override
    public void perform(Entity damager, Entity damaged) {
        this.damager = damaged;
        damager.setDamage(damager.getDamage() + (int)(damager.getDamage() * power));
        scheduleDelayed(2);
    }

    @Override
    public void run() {
        Utils.getNearbyEntities(damager.getLocation(), radius, 30).forEach(e -> e.hurt(damager));
    }
}
