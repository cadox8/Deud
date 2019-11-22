package me.cadox8.deud.entities.projectile;


import lombok.NonNull;
import me.cadox8.deud.api.GameAPI;

public class Arrow extends Projectile {

    public Arrow(@NonNull GameAPI gameAPI, float x, float y) {
        super(900, "Arrow", gameAPI, null, x, y, 42, 42);

        bounds.x = 12;
        bounds.y = 23;
        bounds.width = 17;
        bounds.height = 19;

        setDamageable(false);
        setSpeed(10.0f);

        setTexture(null);
    }
}
