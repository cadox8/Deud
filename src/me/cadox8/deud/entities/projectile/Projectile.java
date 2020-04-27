package me.cadox8.deud.entities.projectile;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.attributes.Attribute;
import me.cadox8.deud.entities.EntityData;
import me.cadox8.deud.entities.Location;
import me.cadox8.deud.entities.creatures.Creature;
import me.cadox8.deud.utils.Utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Projectile extends Creature {

    @Setter protected BufferedImage texture;
    @Getter @Setter private List<Attribute> attributes;

    @Getter @Setter private float distance;

    public Projectile(int id, String name, EntityData.EntityType type, @NonNull GameAPI gameAPI, BufferedImage texture, float x, float y, int width, int height) {
        super(id, name, type, gameAPI, x, y, width, height, 0);
        this.texture = texture;

        attributes = new ArrayList<>();

        distance = 20.0F;
    }

    public void shot(final int direction, final Location location) {
        texture = Utils.rotateImage(Utils.directionToDegrees(direction), texture);
        final Location finalLocation = location;

        switch (direction) {
            case 1:
                finalLocation.add(0, distance);
                break;
            case 2:
                finalLocation.add(distance, 0);
                break;
            case 3:
                finalLocation.add(-distance, 0);
                break;
            default:
                finalLocation.add(0, -distance);
                break;
        }

        while (!finalLocation.equals(location)) {
            switch (direction) {
                case 1:
                    location.add(0, 1);
                    break;
                case 2:
                    location.add(1, 0);
                    break;
                case 3:
                    location.add(-1, 0);
                    break;
                default:
                    location.add(0, -1);
                    break;
            }
        }
    }

    public void addAttributes(Attribute... attributes) {
        this.attributes.addAll(Arrays.asList(attributes));
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
