package me.cadox8.deud.entities.creatures.monsters;

import me.cadox8.deud.ai.entities.MonstersAI;
import me.cadox8.deud.animations.Animation;
import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.entities.EntityData;
import me.cadox8.deud.gfx.textures.Models;

public class Ghost extends Monster {

    public Ghost(GameAPI gameAPI, float x, float y) {
        super(3, "Ghost", EntityData.EntityType.GHOST, gameAPI, x, y, DEFAULT_CREATURE_WIDTH, DEFAULT_CREATURE_HEIGHT);

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

        ai = new MonstersAI(gameAPI, this, getSpeed(), 5, 250, 250);
    }

    @Override
    public void die() {
        adjustXP(0.13f);
    }
}
