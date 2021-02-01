package me.cadox8.deud.ai.entities;

import lombok.NonNull;
import me.cadox8.deud.ai.EntityAI;
import me.cadox8.deud.ai.path.Node;
import me.cadox8.deud.entities.creatures.Creature;

import java.util.List;

public class MonstersEntityAI extends EntityAI {

    public MonstersEntityAI(@NonNull Creature creature, int delay) {
        super(creature, delay, 20);
    }

    @Override
    public void move() {
        if (!this.canTrack()) {
            this.randomMove();
        } else {
            final List<Node> path = this.getPath(this.getPlayerInRadius().getLocation());

            if (path == null) {
                this.randomMove();
            } else {
                path.forEach(node -> {
                    System.out.print("[" + node.x + ", " + node.y + "] ");
                });
            }
        }
    }
}
