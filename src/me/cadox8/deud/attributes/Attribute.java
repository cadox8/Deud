package me.cadox8.deud.attributes;

import lombok.Getter;
import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.entities.Entity;
import me.cadox8.deud.runnable.DeudTask;

public abstract class Attribute extends DeudTask {

    protected final GameAPI GameAPI;
    @Getter private final int id;
    @Getter private final String name;

    public Attribute(GameAPI GameAPI, int id, String name) {
        this.GameAPI = GameAPI;
        this.id = id;
        this.name = name;
    }

    public abstract void perform(Entity damager, Entity damaged);

    @Override
    public void run() {}
}
