package me.cadox8.deud.events.projectiles;

import lombok.Getter;
import lombok.NonNull;
import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.entities.projectile.Projectile;
import me.cadox8.deud.events.Event;

public abstract class ProjectileEvent extends Event {

    @Getter protected Projectile projectile;

    public ProjectileEvent(@NonNull GameAPI gameAPI, Projectile projectile) {
        super(gameAPI);
        this.projectile = projectile;
    }
}
