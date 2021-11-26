package es.cadox8.deud.events.projectiles;

import es.cadox8.deud.api.GameAPI;
import es.cadox8.deud.entities.Entity;
import es.cadox8.deud.entities.projectile.Projectile;
import lombok.NonNull;

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
