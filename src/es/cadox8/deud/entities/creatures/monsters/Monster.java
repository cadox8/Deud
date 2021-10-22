package es.cadox8.deud.entities.creatures.monsters;

import es.cadox8.deud.api.GameAPI;
import es.cadox8.deud.entities.enums.EntityType;
import es.cadox8.deud.inventory.creature.CreatureInventory;
import es.cadox8.deud.entities.creatures.Creature;

public abstract class Monster extends Creature {

    public Monster(String uuid, String name, EntityType type, GameAPI gameAPI, float x, float y, int width, int height) {
        super(uuid, name, type, gameAPI, x, y, width, height);
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
