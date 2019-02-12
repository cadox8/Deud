package me.cadox8.deud.attributes;

import lombok.Getter;
import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.entities.Entity;
import me.cadox8.deud.entities.creatures.Creature;

public abstract class Attribute {

    protected final GameAPI GameAPI;
    @Getter private final int id;
    @Getter private final String name;

    public Attribute(GameAPI GameAPI, int id, String name) {
        this.GameAPI = GameAPI;
        this.id = id;
        this.name = name;
    }

    public abstract void perform(Entity damager, Creature damaged);
}
