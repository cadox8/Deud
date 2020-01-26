package me.cadox8.deud.ai.entities;

import lombok.NonNull;
import me.cadox8.deud.ai.AI;
import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.entities.creatures.Creature;

import java.awt.*;

public class MonstersAI extends AI {

    private Creature creature;

    public MonstersAI(@NonNull GameAPI gameAPI, Creature creature, float speed, int delay, int width, int height) {
        super(gameAPI, creature, speed, delay, new Rectangle(0, 0, width, height));
        this.creature = creature;
    }

    @Override
    public void getMove() {
        if (isNoAI()) return;

        if (isTracking()) {
            trackPlayer(creature);
        } else {
            randomMove(creature);
        }
    }
}
