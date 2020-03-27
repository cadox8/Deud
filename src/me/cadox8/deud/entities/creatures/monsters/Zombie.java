package me.cadox8.deud.entities.creatures.monsters;

import me.cadox8.deud.ai.entities.MonstersAI;
import me.cadox8.deud.animations.Animation;
import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.entities.EntityData;
import me.cadox8.deud.graphics.textures.Models;
import me.cadox8.deud.inventory.creature.CreatureInventory;
import me.cadox8.deud.items.Items;

public class Zombie extends Monster {

    public Zombie(GameAPI gameAPI, float x, float y) {
        super(2, "Zombie", EntityData.EntityType.ZOMBIE, gameAPI, x, y, DEFAULT_CREATURE_WIDTH, DEFAULT_CREATURE_HEIGHT);

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

        ai = new MonstersAI(gameAPI, this, getSpeed(), 10, 200, 200);

        inventory = new CreatureInventory(gameAPI);
    }


    @Override
    public void die() {
        adjustXP(0.3f);
        dropItem(Items.getChickenItem());
    }
}
