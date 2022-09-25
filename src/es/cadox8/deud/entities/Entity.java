package es.cadox8.deud.entities;

import es.cadox8.deud.ai.EntityAI;
import es.cadox8.deud.animations.Animation;
import es.cadox8.deud.api.GameAPI;
import es.cadox8.deud.entities.creatures.monsters.Monster;
import es.cadox8.deud.entities.creatures.player.Player;
import es.cadox8.deud.entities.enums.Direction;
import es.cadox8.deud.entities.enums.EntityType;
import es.cadox8.deud.entities.projectile.Projectile;
import es.cadox8.deud.entities.components.inventory.Inventory;
import es.cadox8.deud.items.Item;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import es.cadox8.deud.events.projectiles.ProjectileHitEvent;
import es.cadox8.deud.utils.Log;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public abstract class Entity {

    // Internal Data
    @Getter private final String UUID;
    @Getter private final String INTERNAL_NAME;
    @Getter private final EntityType ENTITY_TYPE;
    //

    // Defaults

    protected final int DEFAULT_HEALTH = 10;
    protected final int DEFAULT_DAMAGE = 3;
    protected final float DEFAULT_ARMOR = 0;

    //

    @Getter @Setter protected GameAPI gameAPI;

    @Getter @Setter protected EntityAI entityAi;

    @Getter @Setter protected float x, y;
    @Getter @Setter protected int width, height;

    @Getter @Setter private int maxHealth;
    @Getter @Setter private int health;

    @Getter @Setter private int damage;

    @Getter @Setter private double armor;

    @Getter @Setter private boolean damageable = true;

    // Attack timer
    @Getter @Setter protected long lastAttackTimer, attackCooldown = 400, attackTimer = attackCooldown;

    @Getter @Setter protected Direction direction;

    @Getter @Setter private boolean active = true;

    @Getter @Setter protected Rectangle bounds;

    @Getter @Setter private Entity collisionEntity;

    @Getter @Setter protected Entity killer;

    @Setter protected Inventory inventory;

    @Getter @Setter protected boolean moving = true;

    @Getter @Setter protected Animation animDown, animUp, animLeft, animRight;
    @Getter @Setter protected Animation[] animations = new Animation[4];

    public Entity(String uuid, String name, EntityType ENTITY_TYPE, @NonNull GameAPI gameAPI, float x, float y, int width, int height) {
        this.UUID = uuid;
        this.INTERNAL_NAME = name;
        this.ENTITY_TYPE = ENTITY_TYPE;

        this.gameAPI = gameAPI;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        health = DEFAULT_HEALTH;
        damage = DEFAULT_DAMAGE;
        armor = DEFAULT_ARMOR;

        this.direction = Direction.SOUTH;

        bounds = new Rectangle(0, 0, width, height);
    }

    public abstract void tick();
    public abstract void fixAnimations();
    public abstract void postRender(Graphics g);
    public abstract void render(Graphics g);
    public abstract void preRender(Graphics g);
    public abstract void getHurt();
    public abstract void die();

    /**
     * Hurt main method
     *
     * getHurt(); method is thrown BEFORE checking if the entity can have any damage. This is to animate the entity and more things.
     *
     * @param attacker The attacker
     *
     * @see #getHurt()
     */
    public void hurt(Entity attacker) {
        Log.log(this.getINTERNAL_NAME() + " - Health: " + getHealth());
        this.getHurt();

        if (!this.isDamageable()) return;
        int damage = attacker.getDamage(); // Initial damage

        // If the attacker is a Monster
        if (attacker instanceof Monster) {
            final Monster monster = (Monster) attacker;
            final Item handItem = monster.getCreatureInventory().getItemInHand();
            damage += handItem.getDamage();
        }

        if (attacker instanceof Projectile) {
            final Projectile projectile = (Projectile) attacker;
            new ProjectileHitEvent(this.gameAPI, projectile, this).onEvent();
            damage += projectile.getDamage();
        }

        this.setHealth(this.getHealth() - damage);

        if (health <= 0) {
            if (!(this instanceof Player)) active = false;
            this.killer = attacker;
            this.die();
        }
    }

    public boolean checkEntityCollisions(float xOffset, float yOffset) {
        for (Entity e : gameAPI.getWorld().getEntityManager().getEntities()) {
            if (e.equals(this)) continue;

            if (e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset))) {
                collisionEntity = e;
                return true;
            }
        }
        return false;
    }

    public Entity getEntityCollision(float xOffset, float yOffset) {
        return gameAPI.getWorld().getEntityManager().getEntities().stream().filter(e -> !e.equals(this)).filter(e -> e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset))).findFirst().orElse(null);
    }

    public Rectangle getCollisionBounds(float xOffset, float yOffset) {
        return new Rectangle((int) (x + bounds.x + xOffset), (int) (y + bounds.y + yOffset), bounds.width, bounds.height);
    }

    public void kill() {
        setHealth(0);
    }

    public Inventory getInventory() {
        return inventory;
    }

    protected Point entityCenter() {
        return new Point((int) (getWidth()/2 + x - gameAPI.getGameCamera().getXOffset()), (int) (getHeight()/2 + y - gameAPI.getGameCamera().getYOffset()));
    }

    protected BufferedImage getCurrentAnimationFrame() {
        switch (direction) {
            case NORTH:
                return isMoving() ? animUp.getCurrentFrame() : animUp.getFirstFrame();
            case EAST:
                return isMoving() ? animRight.getCurrentFrame() : animRight.getFirstFrame();
            case WEST:
                return isMoving() ? animLeft.getCurrentFrame() : animLeft.getFirstFrame();
            default:
                return isMoving() ? animDown.getCurrentFrame() : animDown.getFirstFrame();
        }
    }

    public void dropItem(Item item){
        dropItem(item, 1);
    }
    public void dropItem(Item item, float percent){
        dropItem(item, percent, (int) x, (int) y);
    }
    public void dropItem(Item item, float percent, int xPos, int yPos){
        if (item == null) return;
        if (percent >= new Random().nextFloat()) {
            final Random r = new Random();
            final int amount = r.nextInt(15) + 45;

            gameAPI.getWorld().getItemManager().addItem(item.createNew(xPos + (r.nextBoolean() ? amount : -amount), yPos + (r.nextBoolean() ? amount : -amount), item.getCount()));
        }
    }

    public Location getLocation() {
        return new Location(this);
    }


    @Override
    public String toString() {
        return "Entity: {ID: " + getUUID() + ", Name: " + getINTERNAL_NAME() + ", X: " + getX() + ", Y: " + getY() + "}";
    }
}
