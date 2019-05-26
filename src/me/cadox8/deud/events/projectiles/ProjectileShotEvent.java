package me.cadox8.deud.events.projectiles;

import lombok.NonNull;
import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.entities.Entity;
import me.cadox8.deud.entities.creatures.projectiles.Projectile;
import me.cadox8.deud.entities.projectile.Arrow;
import me.cadox8.deud.items.Item;

public class ProjectileShotEvent extends ProjectileEvent {

    private Entity shooter;
    private Item item;

    public ProjectileShotEvent(GameAPI gameAPI, @NonNull Item item, @NonNull Entity shooter) {
        super(gameAPI, null);
        this.item = item;
        this.shooter = shooter;
    }

    @Override
    public void onEvent() {
        switch (item.getId()) {
            case 8:
                projectile = new Arrow(gameAPI, shooter.getX(), shooter.getY());
                projectile.setDamage(shooter.getDamage());
                break;
        }
        projectile.setTexture(item.getTexture());
        projectile.setAttributes(item.getAttributes());
        gameAPI.getEntityManager().addEntity(projectile);
    }

    public Projectile getProjectile() {
        return projectile;
    }
}
