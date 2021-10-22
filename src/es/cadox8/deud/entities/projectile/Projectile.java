package es.cadox8.deud.entities.projectile;

import es.cadox8.deud.api.GameAPI;
import es.cadox8.deud.entities.enums.Direction;
import es.cadox8.deud.entities.enums.EntityType;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import es.cadox8.deud.entities.Location;
import es.cadox8.deud.entities.creatures.Creature;
import es.cadox8.deud.utils.Utils;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Projectile extends Creature {

    @Setter protected BufferedImage texture;

    @Getter @Setter private float distance;

    public Projectile(String uuid, String name, EntityType type, @NonNull GameAPI gameAPI, BufferedImage texture, float x, float y, int width, int height) {
        super(uuid, name, type, gameAPI, x, y, width, height);
        this.texture = texture;

        this.distance = 20.0F;

        this.setDamageable(false);
    }

    public void shot(final Direction direction, Location location) {
        texture = Utils.rotateImage(Utils.directionToDegrees(direction), texture);

        switch (direction) {
            case NORTH -> location.add(0, distance);
            case EAST -> location.add(distance, 0);
            case WEST -> location.add(-distance, 0);
            default -> location.add(0, -distance); // South
        }

        // ToDo: Projectile movement

    }

    @Override
    public void tick() {}

    @Override
    public void render(Graphics g) {
        g.drawImage(texture, (int) (x - gameAPI.getGameCamera().getXOffset()), (int) (y - gameAPI.getGameCamera().getYOffset()), width, height, null);
    }

    @Override
    public void die() {}
}
