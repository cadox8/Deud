package me.cadox8.deud.entities.creatures;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.entities.Entity;
import me.cadox8.deud.entities.enums.EntityType;
import me.cadox8.deud.entities.statics.Light;
import me.cadox8.deud.tiles.Tile;

import java.awt.*;
import java.util.Arrays;

public abstract class Creature extends Entity {

    public static final float DEFAULT_SPEED = 3.0f;

    public static final int DEFAULT_CREATURE_WIDTH = 64, DEFAULT_CREATURE_HEIGHT = 64;

    @Getter @Setter protected float speed;
    @Getter @Setter protected float xMove, yMove;

    @Getter @Setter protected boolean freeze = false;

    @Getter @Setter protected Light light;

    public Creature(String uuid, String name, EntityType type, @NonNull GameAPI gameAPI, float x, float y, int width, int height) {
        super(uuid, name, type, gameAPI, x, y, width, height);
        speed = DEFAULT_SPEED;
        xMove = 0;
        yMove = 0;

        light = new Light(gameAPI, (int)getX(), (int)getY(), 100, 1.3F).setStatic(false);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(getCurrentAnimationFrame(), (int) (x - gameAPI.getGameCamera().getXOffset()), (int) (y - gameAPI.getGameCamera().getYOffset()), width, height, null);
    }

    public void fixAnimations() {
        light.setX((int)entityCenter().getX());
        light.setY((int)entityCenter().getY());

        if (animations[0] != null) Arrays.asList(animations).forEach(a -> a.setSpeed((int)(speed * 166.66)));
    }

    public void move() {
        if (!checkEntityCollisions(xMove, 0f)) moveX();
        if (!checkEntityCollisions(0f, yMove)) moveY();
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

    @Override
    public void preRender(Graphics g) {}

    @Override
    public void postRender(Graphics g) {}

    @Override
    public void getHurt() {}
}
