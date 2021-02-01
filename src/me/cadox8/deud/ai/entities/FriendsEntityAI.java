package me.cadox8.deud.ai.entities;

import lombok.NonNull;
import me.cadox8.deud.ai.EntityAI;
import me.cadox8.deud.entities.creatures.Creature;

public class FriendsEntityAI extends EntityAI {

    public FriendsEntityAI(@NonNull Creature creature, int delay) {
        super(creature, delay);
    }

    @Override
    public void move() {
        this.randomMove();
    }
}
