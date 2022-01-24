package es.cadox8.deud.entities.statics;

import es.cadox8.deud.api.GameAPI;
import es.cadox8.deud.entities.Entity;
import es.cadox8.deud.entities.enums.EntityType;
import lombok.NonNull;

import java.awt.*;

public abstract class StaticEntity extends Entity {

    public StaticEntity(String uuid, String name, EntityType type, @NonNull GameAPI gameAPI, float x, float y, int width, int height) {
        super(uuid, name, type, gameAPI, x, y, width, height);
    }

    @Override
    public void tick() {}

    @Override
    public void die() {}

    @Override
    public void getHurt() {}

    @Override
    public void preRender(Graphics g) {}

    @Override
    public void postRender(Graphics g) {}

    public void fixAnimations() {}
}
