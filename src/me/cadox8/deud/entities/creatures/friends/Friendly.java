package me.cadox8.deud.entities.creatures.friends;

import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.entities.creatures.Creature;

import java.awt.*;

public abstract class Friendly extends Creature {

    public Friendly(int id, String name, GameAPI GameAPI, float x, float y, int width, int height) {
        this(id, name, GameAPI, x, y, width, height, 0);
    }

    public Friendly(int id, String name, GameAPI GameAPI, float x, float y, int width, int height, int level) {
        super(id, name, GameAPI, x, y, width, height, level);
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

    @Override
    public void render(Graphics g) {
        g.drawImage(getCurrentAnimationFrame(), (int) (x - gameAPI.getGameCamera().getXOffset()), (int) (y - gameAPI.getGameCamera().getYOffset()), width, height, null);
    }


    //Utils
    private void getInput() {
        xMove = 0;
        yMove = 0;

        ai.getMove();
    }
}
