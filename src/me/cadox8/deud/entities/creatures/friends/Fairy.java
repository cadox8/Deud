package me.cadox8.deud.entities.creatures.friends;

import me.cadox8.deud.ai.entities.FriendsEntityAI;
import me.cadox8.deud.animations.Animation;
import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.entities.enums.EntityType;
import me.cadox8.deud.graphics.textures.Models;
import me.cadox8.deud.items.Items;

public class Fairy extends Friendly {

    public Fairy(GameAPI gameAPI, float x, float y) {
        super("a26e2385-d2bc-4445-a232-6ee60aa53c66", "Fairy", EntityType.FAIRY, gameAPI, x, y, 42, 42);

        bounds.x = 12;
        bounds.y = 23;
        bounds.width = 20;
        bounds.height = 19;

        //Animatons
        animDown = new Animation(500, Models.fairy_down);
        animUp = new Animation(500, Models.fairy_up);
        animLeft = new Animation(500, Models.fairy_left);
        animRight = new Animation(500, Models.fairy_right);

        setHealth(1);
        setDamage(0);
        setSpeed(10.0f);

        entityAi = new FriendsEntityAI(this, 50);
    }

    @Override
    public void die() {
        dropItem(Items.KEY.item());
    }
}
