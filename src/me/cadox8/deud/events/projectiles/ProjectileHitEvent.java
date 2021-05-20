package me.cadox8.deud.events.projectiles;

import lombok.NonNull;
import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.entities.Entity;
import me.cadox8.deud.entities.projectile.Projectile;

public class ProjectileHitEvent extends ProjectileEvent {

    private Entity damaged;

    public ProjectileHitEvent(@NonNull GameAPI gameAPI, @NonNull Projectile projectile, @NonNull Entity damaged) {
        super(gameAPI, projectile);
        this.damaged = damaged;
    }

    // ToDo: Projectile effects!

    @Override
    public void onEvent() {
        projectile.setActive(false);
    }
}
