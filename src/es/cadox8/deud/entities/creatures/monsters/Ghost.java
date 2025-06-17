package es.cadox8.deud.entities.creatures.monsters;

import es.cadox8.deud.ai.entities.MonstersEntityAI;
import es.cadox8.deud.animations.Animation;
import es.cadox8.deud.api.GameAPI;
import es.cadox8.deud.entities.enums.EntityType;
import es.cadox8.deud.graphics.textures.Models;
import es.cadox8.deud.entities.components.inventory.creature.CreatureInventory;

public class Ghost extends Monster {

    public Ghost(GameAPI gameAPI, float x, float y) {
        super("36913f63-3654-4940-aed7-d06b81b200c4", "Ghost", EntityType.GHOST, gameAPI, x, y, DEFAULT_CREATURE_WIDTH, DEFAULT_CREATURE_HEIGHT);

        bounds.x = 20;
        bounds.y = 44;
        bounds.width = 28;
        bounds.height = 19;

        //Animations
        animDown = new Animation(500, Models.ghost_down);
        animUp = new Animation(500, Models.ghost_up);
        animLeft = new Animation(500, Models.ghost_left);
        animRight = new Animation(500, Models.ghost_right);

        setHealth(7);
        setDamage(2);
        setSpeed(8.0f);

        setAttackCooldown(800);

        entityAi = new MonstersEntityAI(this, 5);
    }

    @Override
    public void die() {
    }
}
