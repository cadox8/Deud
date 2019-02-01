package me.cadox8.deud.attributes;

import me.cadox8.deud.api.API;
import me.cadox8.deud.entities.Entity;
import me.cadox8.deud.entities.creatures.Creature;

public class Stun extends Attribute {

    private double time;
    private Thread stop;

    public Stun(API API, int time) {
        super(API, 2, "Stun");
        this.time = time;
    }

    @Override
    public void perform(Entity damager, Creature damaged) {
         stop = new Thread(() -> {
            damaged.setFreeze(true);

            time -= 0.01;

            if (time == 0) {
                damaged.setFreeze(false);
                try {
                    stop.join();
                } catch (InterruptedException e) {}
            }
        });
        stop.start();
    }
}
