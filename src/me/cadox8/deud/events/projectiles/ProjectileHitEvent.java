package me.cadox8.deud.events.projectiles;

import lombok.NonNull;
import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.entities.Entity;
import me.cadox8.deud.entities.creatures.projectiles.Projectile;
import me.cadox8.deud.events.Event;

public class ProjectileHitEvent extends Event {

    private Projectile projectile;
    private Entity damaged;

    public ProjectileHitEvent(GameAPI gameAPI, @NonNull Projectile projectile, @NonNull Entity damaged) {
        super(gameAPI);
        this.projectile = projectile;
        this.damaged = damaged;
    }

    @Override
    public void onEvent() {
        if (damaged == null) return; //No hit
        if (!projectile.getAttributes().isEmpty()) projectile.getAttributes().forEach(a -> a.perform(projectile, damaged));
        projectile.setActive(false);
    }
}
