package me.cadox8.deud.entities.creatures.monsters;

import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.entities.EntityData;
import me.cadox8.deud.entities.creatures.Creature;
import me.cadox8.deud.inventory.creature.CreatureInventory;

public abstract class Monster extends Creature {

    public Monster(int id, String name, EntityData.EntityType type, GameAPI gameAPI, float x, float y, int width, int height) {
        this(id, name, type, gameAPI, x, y, width, height, 0);
    }

    public Monster(int id, String name, EntityData.EntityType type, GameAPI gameAPI, float x, float y, int width, int height, int level) {
        super(id, name, type, gameAPI, x, y, width, height, level);
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

    public CreatureInventory getCreatureInventory() {
        return (CreatureInventory) inventory;
    }
}
