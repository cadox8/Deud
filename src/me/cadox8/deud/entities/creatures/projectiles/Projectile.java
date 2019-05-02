package me.cadox8.deud.entities.creatures.projectiles;

import lombok.Getter;
import lombok.Setter;
import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.attributes.Attribute;
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

    public Projectile(int id, String name, GameAPI gameAPI, BufferedImage texture, float x, float y, int width, int height) {
        super(id, name, gameAPI, x, y, width, height);
        this.texture = texture;

        attributes = new ArrayList<>();
    }

    public void shot(int direction) {
        texture = Utils.rotateImage(Utils.directionToDregrees(direction) ,texture);
    }

    public void addAttributes(Attribute... attributes) {
        this.attributes.addAll(Arrays.asList(attributes));
    }

    @Override
    public void tick() {}

    @Override
    public void render(Graphics g) {
        g.drawImage(texture, (int) (x - GameAPI.getGameCamera().getXOffset()), (int) (y - GameAPI.getGameCamera().getYOffset()), width, height, null);
    }

    @Override
    public void die() {}
}
