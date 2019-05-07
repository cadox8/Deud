package me.cadox8.deud.entities.creatures.friends;

import me.cadox8.deud.ai.entities.FriendsAI;
import me.cadox8.deud.animations.Animation;
import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.gfx.textures.Models;
import me.cadox8.deud.items.Item;

public class Fairy extends Friendly {

    public Fairy(GameAPI GameAPI, float x, float y) {
        super(4, "Fairy", GameAPI, x, y, 42, 42);

        bounds.x = 12;
        bounds.y = 23;
        bounds.width = 17;
        bounds.height = 19;

        //Animatons
        animDown = new Animation(500, Models.fairy_down);
        animUp = new Animation(500, Models.fairy_up);
        animLeft = new Animation(500, Models.fairy_left);
        animRight = new Animation(500, Models.fairy_right);

        setHealth(1);
        setDamage(0);
        setSpeed(10.0f);

        ai = new FriendsAI(GameAPI, this, getSpeed(), 50);
    }

    @Override
    public void die() {
        ajustXP(0.13f);
        dropItem(Item.keyItem);
    }
}
