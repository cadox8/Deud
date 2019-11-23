package me.cadox8.deud.entities.statics;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.entities.Entity;
import me.cadox8.deud.inventory.CreatureInventory;
import me.cadox8.deud.inventory.StaticInventory;

import java.awt.*;

public abstract class StaticEntity extends Entity {

    @Getter @Setter protected boolean explosive = false;

    public StaticEntity(int id, String name, @NonNull GameAPI gameAPI, float x, float y, int width, int height) {
        super(id, name, gameAPI, x, y, width, height, 0);
    }

    @Override
    public void tick() {}

    @Override
    public void die() {}

    @Override
    public void getHurt() {}

    @Override
    public void preRender(Graphics g) {}

    public void fixAnimations() {}

    public StaticInventory getInventory() {
        return (StaticInventory) inventory;
    }
}
