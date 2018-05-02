package me.cadox8.deud.attributes;

import me.cadox8.deud.api.API;
import me.cadox8.deud.entities.Entity;
import me.cadox8.deud.entities.creatures.Creature;

public class Knockback extends Attribute {

    private final double percent;
    private final Entity damager;
    private final Creature damaged;

    public Knockback(API API, double percent, Entity damager, Creature damaged){
        super(API,0, "Knockback");
        this.percent = percent;
        this.damager = damager;
        this.damaged = damaged;
    }

    @Override
    public void perform() {
        int damagerDir = damager.getDirection();
        float speed = damaged.getSpeed();

        switch (damagerDir) {
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
    }
}
