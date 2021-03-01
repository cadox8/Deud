package me.cadox8.deud.entities.creatures.friends;

import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.entities.EntityData;
import me.cadox8.deud.entities.creatures.Creature;

public abstract class Friendly extends Creature {

    public Friendly(String uuid, String name, EntityData.EntityType type, GameAPI gameAPI, float x, float y, int width, int height) {
        this(uuid, name, type, gameAPI, x, y, width, height, 0);
    }

    public Friendly(String uuid, String name, EntityData.EntityType type, GameAPI gameAPI, float x, float y, int width, int height, int level) {
        super(uuid, name, type, gameAPI, x, y, width, height, level);
    }

    @Override
    public void tick() {
        //Animations
        animDown.tick();
        animUp.tick();
        animRight.tick();
        animLeft.tick();

        //Movement
        getInput();
        move();
    }

    //Utils
    private void getInput() {
        xMove = 0;
        yMove = 0;

        entityAi.move();
    }
}
