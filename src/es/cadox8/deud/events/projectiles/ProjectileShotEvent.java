package es.cadox8.deud.events.projectiles;

import es.cadox8.deud.api.GameAPI;
import es.cadox8.deud.entities.Entity;
import es.cadox8.deud.entities.projectile.Arrow;
import es.cadox8.deud.entities.projectile.Projectile;
import es.cadox8.deud.items.Item;
import lombok.NonNull;

public class ProjectileShotEvent extends ProjectileEvent {

    private Entity shooter;
    private Item item;

    public ProjectileShotEvent(@NonNull GameAPI gameAPI, @NonNull Item item, @NonNull Entity shooter) {
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
        gameAPI.getEntityManager().addEntity(projectile);
    }

    public Projectile getProjectile() {
        return projectile;
    }
}
