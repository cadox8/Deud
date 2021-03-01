package me.cadox8.deud.ai.entities;

import lombok.NonNull;
import me.cadox8.deud.ai.EntityAI;
import me.cadox8.deud.ai.path.Node;
import me.cadox8.deud.entities.Location;
import me.cadox8.deud.entities.creatures.Creature;
import me.cadox8.deud.utils.Utils;

import java.util.List;

public class MonstersEntityAI extends EntityAI {

    public MonstersEntityAI(@NonNull Creature creature, int delay) {
        super(creature, delay, 200);
    }

    @Override
    public void move() {
        if (this.canTrack()) {
            /*final List<Node> path = this.getPath(this.getPlayerInRadius().getLocation());

            if (path == null) {
                this.randomMove();
            } else {
                final Creature creature = this.getCreature();
                final Location location = creature.getLocation();
                path.forEach(node -> {
                    final float xEnd = Utils.tileToLocation(node.x);
                    final float yEnd = Utils.tileToLocation(node.y);

                    // ToDo: Mejorar!
                    //
                    if (xEnd < location.getX()) {
                        for (float i = xEnd; i < location.getX(); i += creature.getSpeed()) creature.setXMove(i);
                    } else if (xEnd > location.getX()) {
                        for (float i = location.getX(); i < xEnd; i += creature.getSpeed()) creature.setXMove(-i);
                    }

                    if (yEnd < location.getY()) {
                        for (float i = yEnd; i < location.getY(); i += creature.getSpeed()) creature.setYMove(i);
                    } else if (yEnd > location.getY()) {
                        for (float i = location.getY(); i < yEnd; i += creature.getSpeed()) creature.setYMove(-i);
                    }
                    //
                });
                path.forEach(node -> {
                    System.out.println("[" + node.x + ", " + node.y + "] ");
                });
            }*/
        } else {
            this.randomMove();
        }
    }
}
