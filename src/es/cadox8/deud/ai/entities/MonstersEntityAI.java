package es.cadox8.deud.ai.entities;

import lombok.NonNull;
import es.cadox8.deud.ai.EntityAI;
import es.cadox8.deud.entities.creatures.Creature;

public class MonstersEntityAI extends EntityAI {

    public MonstersEntityAI(@NonNull Creature creature, int delay) {
        super(creature, delay, 200);
    }

    @Override
    public void move() {
        if (this.canTrack()) {
          // ToDo: Make AI
        } else {
            this.randomMove();
        }
    }
}
