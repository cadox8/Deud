package me.cadox8.deud.attributes;

import me.cadox8.deud.api.API;
import me.cadox8.deud.entities.Entity;
import me.cadox8.deud.entities.creatures.Creature;

public class Knockback extends Attribute {

    private final double percent;

    public Knockback(API API, double percent){
        super(API,0, "Knockback");
        this.percent = percent;
    }

    @Override
    public void perform(Entity damager, Creature damaged) {
        final float speed = (float)(percent * damaged.getSpeed());

        switch (damager.getDirection()) {
            case 0:
                damaged.setYMove(speed);
                //damaged.setY(damaged.getY() + (float)(percent * damaged.getY()));
                break;
            case 1:
                damaged.setYMove(-speed);
                //damaged.setY(damaged.getY() - (float)(percent * damaged.getY()));
                break;
            case 2:
                damaged.setXMove(speed);
                //damaged.setX(damaged.getX() + (float)(percent * damaged.getX()));
                break;
            case 3:
                damaged.setXMove(-speed);
                //damaged.setX(damaged.getX() - (float)(percent * damaged.getX()));
                break;
        }
        damaged.move();
    }
}
