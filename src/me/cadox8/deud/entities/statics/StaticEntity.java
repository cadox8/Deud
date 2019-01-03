package me.cadox8.deud.entities.statics;

import lombok.Getter;
import lombok.Setter;
import me.cadox8.deud.api.API;
import me.cadox8.deud.entities.Entity;

import java.awt.*;

public abstract class StaticEntity extends Entity {

    @Getter @Setter protected boolean explosive = false;

    public StaticEntity(int id, String name, API API, float x, float y, int width, int height) {
        super(id, name, API, x, y, width, height, 0);
    }

    @Override
    public void tick() {}

    @Override
    public void die() {}

    @Override
    public void getHurt() {}

    @Override
    public void specialRender(Graphics g) {}

    public void fixAnimations() {}
}
