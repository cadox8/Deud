package me.cadox8.deud.events.projectiles;

import lombok.NonNull;
import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.entities.Entity;
import me.cadox8.deud.entities.projectile.Projectile;

public class ProjectileHitEvent extends ProjectileEvent {

    private Entity damaged;

    public ProjectileHitEvent(GameAPI gameAPI, @NonNull Projectile projectile, @NonNull Entity damaged) {
        super(gameAPI, projectile);
        this.damaged = damaged;
    }

    @Override
    public void onEvent() {
        if (damaged == null) return; //No hit
        if (!projectile.getAttributes().isEmpty()) projectile.getAttributes().forEach(a -> a.perform(projectile, damaged));
        projectile.setActive(false);
    }
}
