package me.cadox8.deud.entities.creatures.monsters;

import me.cadox8.deud.ai.entities.MonstersAI;
import me.cadox8.deud.api.API;
import me.cadox8.deud.gfx.Animation;
import me.cadox8.deud.gfx.textures.Models;
import me.cadox8.deud.items.Item;

public class Zombie extends Monster {

    public Zombie(API API, float x, float y) {
        super(API, x, y, DEFAULT_CREATURE_WIDTH, DEFAULT_CREATURE_HEIGHT);

        bounds.x = 20;
        bounds.y = 44;
        bounds.width = 25;
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

        ai = new MonstersAI(API, this, getSpeed(), 10, 200, 200);
    }


    @Override
    public void die() {
        ajustXP(0.3f);
        dropItem(Item.chickenItem);
    }
}
