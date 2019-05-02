package me.cadox8.deud.attributes;

import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.entities.Entity;
import me.cadox8.deud.entities.creatures.Creature;

public class Stun extends Attribute {

    private Creature damaged;

    public Stun(GameAPI GameAPI) {
        super(GameAPI, 2, "Stun");
    }

    @Override
    public void perform(Entity damager, Entity dam) {
        if (!(dam instanceof Creature)) return;
        this.damaged = (Creature)dam;
        damaged.setFreeze(true);
    }

    @Override
    public void run() {
        damaged.setFreeze(false);
    }
}
