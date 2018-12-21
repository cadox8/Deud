package me.cadox8.deud.ai;

import lombok.Getter;
import me.cadox8.deud.api.API;
import me.cadox8.deud.entities.Entity;
import me.cadox8.deud.entities.EntityManager;
import me.cadox8.deud.entities.Location;
import me.cadox8.deud.entities.creatures.Creature;
import me.cadox8.deud.entities.creatures.player.Player;

import java.awt.*;
import java.util.Random;

public abstract class AI {

    protected API API;
    private Entity entity;
    protected Player player;

    private float speed;
    private int delay;
    protected int direction = 0;
    @Getter protected boolean noAI = false;
    @Getter protected Rectangle bounds;
    private int tempDelay = 0;

    public AI(API API, Entity entity, float speed, int delay) {
        this(API, entity, speed, delay, new Rectangle(0, 0, 0, 0));
    }
    public AI(API API, Entity entity, float speed, int delay, Rectangle bounds) {
        this.API = API;
        this.entity = entity;
        this.speed = speed;
        this.delay = delay;

        tempDelay = delay;

        this.bounds = bounds;
    }

    public abstract void getMove();
    protected abstract boolean isTracking();

    protected void randomMove(Creature creature) {
        while (isOnDelay()) {
            tempDelay--;
            return;
        }
        if (creature.isFreeze()) return;

        tempDelay = delay;
        direction = new Random().nextInt(6);

        switch (direction) {
            case 0:
                creature.setYMove(creature.getSpeed());
                break;
            case 1:
                creature.setYMove(-creature.getSpeed());
                break;
            case 2:
                creature.setXMove(creature.getSpeed());
                break;
            case 3:
                creature.setXMove(-creature.getSpeed());
                break;
            default:
                return;
        }
        entity.setDirection(direction);
    }

    //TODO: Better
    protected void trackPlayer(Creature creature) {
        while (isOnDelay()) {
            tempDelay--;
            return;
        }
        if (creature.isFreeze()) return;

        tempDelay = delay;
        final Location playerLocation = new Location(player);
        final Location creatureLocation = new Location(creature);

        if (playerLocation.getY() > creatureLocation.getY()) {
            if (!entity.checkEntityCollisions(creature.getSpeed(), 0f)) {
                creature.setYMove(creature.getSpeed());
            } else {
                attack(creature,0, creature.getSpeed());
            }
            creature.setDirection(0);
        }
        if (playerLocation.getY() < creatureLocation.getY()) {
            if (!entity.checkEntityCollisions(-creature.getSpeed(), 0f)) {
                creature.setYMove(-creature.getSpeed());
            } else {
                attack(creature,0, -creature.getSpeed());
            }
            creature.setDirection(1);
        }
        if (playerLocation.getX() > creatureLocation.getX()) {
            if (!entity.checkEntityCollisions(-creature.getSpeed(), 0f)) {
                creature.setXMove(creature.getSpeed());
            } else {
                attack(creature, creature.getSpeed(), 0);
            }
            creature.setDirection(2);
        }
        if (playerLocation.getX() < creatureLocation.getX()) {
            if (!entity.checkEntityCollisions(-creature.getSpeed(), 0f)) {
                creature.setXMove(-creature.getSpeed());
            } else {
                attack(creature, -creature.getSpeed(), 0);
            }
            creature.setDirection(3);
        }
    }

    private boolean isOnDelay() {
        return tempDelay != 0;
    }

    private void attack(Entity attacker, float xMove, float yMove) {
        EntityManager.checkAttacks(attacker, xMove, yMove);
/*
        entity.setAttackTimer(entity.getAttackTimer() + System.currentTimeMillis() - entity.getLastAttackTimer());
        entity.setLastAttackTimer(System.currentTimeMillis());
        if (entity.getAttackTimer() < entity.getAttackCooldown()) return;

        for (Entity e : API.getWorld().getEntityManager().getEntities()) {
            if (e instanceof Player) {
                if (e.getCollisionBounds(xMove, yMove).intersects(player.getCollisionBounds(0, 0))) {
                    isAttacking = true;
                    if (!isAttacking) return;
                    entity.setAttackTimer(0);
                    player.hurt(entity);
                }
            }
        }*/
    }

    //TODO: Change
    protected Rectangle getTrackingArea() {
        return new Rectangle((int) (entity.getX() - (entity.getX() / 4)) + 8, (int) (entity.getY() - (entity.getY() / 4)), bounds.width, bounds.height);
    }
}
