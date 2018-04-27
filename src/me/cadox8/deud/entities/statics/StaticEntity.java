package me.cadox8.deud.entities.statics;

import lombok.Getter;
import lombok.Setter;
import me.cadox8.deud.api.API;
import me.cadox8.deud.entities.Entity;

public abstract class StaticEntity extends Entity {

    @Getter @Setter protected boolean explosive = false;

    public StaticEntity(API API, float x, float y, int width, int height) {
        super(API, x, y, width, height, 0);
    }
}
