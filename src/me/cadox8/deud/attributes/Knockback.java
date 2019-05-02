package me.cadox8.deud.attributes;

import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.entities.Entity;
import me.cadox8.deud.entities.creatures.Creature;

public class Knockback extends Attribute {

    private final double percent;

    public Knockback(GameAPI GameAPI, double percent){
        super(GameAPI,0, "Knockback");
        this.percent = percent;
    }

    @Override
    public void perform(Entity damager, Entity damaged) {
        if (!(damaged instanceof Creature)) return;
        final float speed = (float)(percent * ((Creature)damaged).getSpeed());

        switch (damager.getDirection()) {
            case 0:
                ((Creature)damaged).setYMove(speed);
                //damaged.setY(damaged.getY() + (float)(percent * damaged.getY()));
                break;
            case 1:
                ((Creature)damaged).setYMove(-speed);
                //damaged.setY(damaged.getY() - (float)(percent * damaged.getY()));
                break;
            case 2:
                ((Creature)damaged).setXMove(speed);
                //damaged.setX(damaged.getX() + (float)(percent * damaged.getX()));
                break;
            case 3:
                ((Creature)damaged).setXMove(-speed);
                //damaged.setX(damaged.getX() - (float)(percent * damaged.getX()));
                break;
        }
        ((Creature)damaged).move();
    }
}
