package me.cadox8.deud.entities.statics;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.entities.Entity;
import me.cadox8.deud.entities.EntityData;
import me.cadox8.deud.inventory.statics.StaticInventory;

import java.awt.*;

public abstract class StaticEntity extends Entity {

    public StaticEntity(String uuid, String name, EntityData.EntityType type, @NonNull GameAPI gameAPI, float x, float y, int width, int height) {
        super(uuid, name, type, gameAPI, x, y, width, height, 0);
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

    public StaticInventory getInventory() {
        return (StaticInventory) inventory;
    }
}
