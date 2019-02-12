package me.cadox8.deud.ai.entities;

import lombok.Getter;
import lombok.Setter;
import me.cadox8.deud.ai.AI;
import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.entities.creatures.Creature;

public class FriendsAI extends AI {

    private Creature creature;

    @Setter @Getter private boolean angry;

    public FriendsAI(GameAPI GameAPI, Creature creature, float speed, int delay) {
        super(GameAPI, creature, speed, delay);
        this.creature = creature;
    }

    @Override
    public void getMove() {
        if (isNoAI()) return;
        randomMove(creature);

        if (isAngry()) {
            if (isTracking()) {
                trackPlayer(creature);
            } else {
                randomMove(creature);
            }
        } else {
            randomMove(creature);
        }
    }

    @Override
    public boolean isTracking() {
        return false;
    }
}
