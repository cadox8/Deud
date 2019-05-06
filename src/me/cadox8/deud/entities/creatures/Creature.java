package me.cadox8.deud.entities.creatures;

import lombok.Getter;
import lombok.Setter;
import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.entities.Entity;
import me.cadox8.deud.entities.creatures.player.Player;
import me.cadox8.deud.entities.statics.Chest;
import me.cadox8.deud.entities.statics.Door;
import me.cadox8.deud.entities.statics.sign.Sign;
import me.cadox8.deud.entities.statics.sign.SignEntity;
import me.cadox8.deud.tiles.Tile;

import java.awt.*;
import java.util.Arrays;
import java.util.Random;

public abstract class Creature extends Entity {

    public static final float DEFAULT_SPEED = 3.0f;

    public static final int DEFAULT_CREATURE_WIDTH = 64, DEFAULT_CREATURE_HEIGHT = 64;

    @Getter @Setter protected float speed;
    @Getter @Setter protected float xMove, yMove;

    @Getter @Setter protected boolean freeze = false;

    public Creature(int id, String name, GameAPI gameAPI, float x, float y, int width, int height) {
        this(id, name, gameAPI, x, y, width, height, 0);
    }

    public Creature(int id, String name, GameAPI gameAPI, float x, float y, int width, int height, int level) {
        super(id, name, gameAPI, x, y, width, height, level);
        speed = DEFAULT_SPEED;
        xMove = 0;
        yMove = 0;
    }


    public void fixAnimations() {
        if (animations[0] != null) Arrays.asList(animations).forEach(a -> a.setSpeed((int)(speed * 166.66)));
    }


    private SignEntity se;
    public void move() {
        if (!checkEntityCollisions(xMove, 0f)) moveX();
        if (!checkEntityCollisions(0f, yMove)) moveY();

        if (!(this instanceof Player)) return;

        // Sign
        if (getEntityCollision(xMove, 0f) instanceof SignEntity) {
            se = (SignEntity) getEntityCollision(xMove, 0f);
            se.setSign(new Sign(se.getText()));
            return;
        }
        if (getEntityCollision(0f, yMove) instanceof SignEntity) {
            se = (SignEntity) getEntityCollision(0f, yMove);
            se.setSign(new Sign(se.getText()));
            return;
        }
        if (se != null && se.getSign() != null) se.setSign(null);

        // Chest
        if (getEntityCollision(xMove, 0f) instanceof Chest) {
            final Chest chest = (Chest) getEntityCollision(xMove, 0f);
            chest.open((Player)this);
            return;
        }
        if (getEntityCollision(0f, yMove) instanceof Chest) {
            final Chest chest = (Chest) getEntityCollision(0f, yMove);
            chest.open((Player)this);
            return;
        }

        // Door
        if (getEntityCollision(xMove, 0f) instanceof Door) {
            final Door door = (Door) getEntityCollision(xMove, 0f);
            door.changeWorld();
            return;
        }
        if (getEntityCollision(0f, yMove) instanceof Door) {
            final Door door = (Door) getEntityCollision(0f, yMove);
            door.changeWorld();
            return;
        }
    }

    public void moveX() {
        if (xMove > 0) { //Moving right
            int tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILEWIDTH;

            if (!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT) && !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)) {
                x += xMove;
            } else {
                x = tx * Tile.TILEWIDTH - bounds.x - bounds.width - 1;
            }
        } else {
            if (xMove < 0) { //Moving left
                int tx = (int) (x + xMove + bounds.x) / Tile.TILEWIDTH;

                if (!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT) && !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)) {
                    x += xMove;
                } else {
                    x = tx * Tile.TILEWIDTH + Tile.TILEWIDTH - bounds.x;
                }
            }
        }
    }

    public void moveY() {
        if (yMove < 0) { //Up
            int ty = (int) (y + yMove + bounds.y) / Tile.TILEHEIGHT;

            if (!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty) && !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)) {
                y += yMove;
            } else {
                y = ty * Tile.TILEHEIGHT + Tile.TILEHEIGHT - bounds.y;
            }
        } else {
            if (yMove > 0) { //Down
                int ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILEHEIGHT;

                if (!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty) && !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)) {
                    y += yMove;
                } else {
                    y = ty * Tile.TILEHEIGHT - bounds.y - bounds.height - 1;
                }
            }
        }
    }

    private boolean collisionWithTile(int x, int y) {
        return gameAPI.getWorld().getTile(x, y).isSolid();
    }

    protected void ajustXP(float droppedXP){
        if (getLevel() != 0) {
            killer.addExp(getLevel() * droppedXP);
        } else {
            killer.addExp(new Random().nextFloat() * 10);
        }
    }


    @Override
    public void specialRender(Graphics g) {}

    @Override
    public void getHurt() {}
}
