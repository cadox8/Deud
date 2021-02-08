package me.cadox8.deud.attributes;

import lombok.NonNull;
import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.entities.Entity;
import me.cadox8.deud.utils.Utils;

public class Explosion extends Attribute {

    private final double radius;
    private final double power;

    private Entity damager;

    public Explosion(@NonNull GameAPI gameAPI, double radius, double power) {
        super(gameAPI, 1, "Explosion");

        this.radius = radius;
        this.power = power;
    }

    @Override
    public void perform(Entity damager, Entity damaged) {
        this.damager = damager;
        damager.setDamage(damager.getDamage() + (int)(damager.getDamage() * power));
        scheduleDelayed(2).run();
    }

    @Override
    public void run() {
        Utils.getNearbyEntities(this.damager.getLocation(), radius).forEach(e -> e.hurt(this.damager));
        stop();
    }
}
