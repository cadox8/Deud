package me.cadox8.deud.ai.entities;

import me.cadox8.deud.ai.AI;
import me.cadox8.deud.api.API;
import me.cadox8.deud.entities.creatures.Creature;

public class FriendsAI extends AI {

    private Creature creature;

    public FriendsAI(API API, Creature creature, float speed, int delay) {
        super(API, creature, speed, delay);
        this.creature = creature;
    }

    @Override
    public void getMove() {
        if (isNoAI()) return;
        randomMove(creature);
    }

    @Override
    public boolean isTracking() {
        return false;
    }
}
