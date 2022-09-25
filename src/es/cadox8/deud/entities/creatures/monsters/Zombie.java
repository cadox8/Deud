package es.cadox8.deud.entities.creatures.monsters;

import es.cadox8.deud.ai.entities.MonstersEntityAI;
import es.cadox8.deud.animations.Animation;
import es.cadox8.deud.api.GameAPI;
import es.cadox8.deud.entities.enums.EntityType;
import es.cadox8.deud.graphics.textures.Models;
import es.cadox8.deud.entities.components.inventory.creature.CreatureInventory;
import es.cadox8.deud.items.Items;

public class Zombie extends Monster {

    public Zombie(GameAPI gameAPI, float x, float y) {
        super("f71c5cef-46a6-42e2-b6cf-60c69de977cd", "Zombie", EntityType.ZOMBIE, gameAPI, x, y, DEFAULT_CREATURE_WIDTH, DEFAULT_CREATURE_HEIGHT);

        bounds.x = 20;
        bounds.y = 44;
        bounds.width = 28;
        bounds.height = 19;

        //Animations
        animDown = new Animation(500, Models.zombie_down);
        animUp = new Animation(500, Models.zombie_up);
        animLeft = new Animation(500, Models.zombie_left);
        animRight = new Animation(500, Models.zombie_right);

        setHealth(15);
        setDamage(2);
        setSpeed(7.0f);

        setAttackCooldown(1000);

        entityAi = new MonstersEntityAI(this, 10);
    }


    @Override
    public void die() {
        dropItem(Items.CHICKEN.item());
    }
}
