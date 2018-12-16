package me.cadox8.deud.entities;

import lombok.Getter;
import lombok.Setter;
import me.cadox8.deud.ai.AI;
import me.cadox8.deud.api.API;
import me.cadox8.deud.entities.creatures.Creature;
import me.cadox8.deud.entities.creatures.monsters.Monster;
import me.cadox8.deud.entities.creatures.player.Player;
import me.cadox8.deud.gfx.Animation;
import me.cadox8.deud.items.Item;
import me.cadox8.deud.utils.Location;
import me.cadox8.deud.utils.Log;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import java.util.Random;

public abstract class Entity {

    // Internal Data
    @Getter private final int INTERNAL_ID;
    @Getter private final String INTERNAL_NAME;
    //

    protected static final int DEFAULT_HEALTH = 10;
    protected static final int DEFAULT_DAMAGE = 3;
    protected static final float DEFAULT_ARMOR = 0;

    private static final double DMG_UP_PER_LVL = 0.1;
    private static final double HEALTH_UP_PER_LVL = 0.13;
    private static final double ARMOR_UP_PER_LVL = 0.086;

    @Getter @Setter protected API API;

    @Getter @Setter protected AI ai;

    @Getter @Setter protected float x, y;
    @Getter @Setter protected int width, height;
    @Getter @Setter private int health;
    @Getter @Setter private int damage;
    @Getter @Setter private double armor;
    @Getter @Setter private int maxHealth;

    @Getter private double xp;
    @Getter private int level;
    @Getter private static final int BASE_XP = 40;
    @Getter private static final int MAX_LEVEL = 30;

    @Getter @Setter private boolean damageable = true;

    // Attack timer
    @Getter @Setter protected long lastAttackTimer, attackCooldown = 400, attackTimer = attackCooldown;

    @Getter @Setter protected int direction = 0; //0 = South, 1 = North, 2 = East, 3 = West

    @Getter @Setter private boolean active = true;

    @Setter private Location location;

    @Getter @Setter protected Rectangle bounds;

    @Getter @Setter private Entity collisionEntity;

    @Getter @Setter protected Entity killer;

    @Getter @Setter protected Animation animDown, animUp, animLeft, animRight;
    @Getter @Setter protected Animation[] animations = new Animation[4];

    public Entity(int id, String name, API API, float x, float y, int width, int height, int level) {
        INTERNAL_ID = id;
        INTERNAL_NAME = name;
        this.API = API;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.level = level;

        health = DEFAULT_HEALTH;
        damage = DEFAULT_DAMAGE;
        armor = DEFAULT_ARMOR;

        this.location = new Location(this);

        bounds = new Rectangle(0, 0, width, height);
    }


    public abstract void tick();
    public abstract void fixAnimations();
    public abstract void render(Graphics g);
    public abstract void specialRender(Graphics g);
    public abstract void die();

    public void hurt(Entity attacker) {
        if (!isDamageable()) return;

        int amt = attacker.getDamage() + (int) (DMG_UP_PER_LVL * attacker.getLevel());
        health -= amt;

        if (API.isDebug()) Log.log("Health: " + getHealth());

        if (this instanceof Creature) {
            if (attacker instanceof Monster) ((Monster)attacker).getItemInHand().getAttributes().forEach(a -> a.perform(attacker, (Creature) this));
            if (attacker instanceof Player) ((Player) attacker).getInventory().getUsableItem().getAttributes().forEach(a -> a.perform(attacker, (Creature) this));
        }

        if (health <= 0) {
            if (!(this instanceof Player)) active = false;
            this.killer = attacker;
            die();
        }
    }

    public boolean checkEntityCollisions(float xOffset, float yOffset) {
        for (Entity e : API.getWorld().getEntityManager().getEntities()) {
            if (e.equals(this)) continue;

            if (e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset))) {
                collisionEntity = e;
                return true;
            }
        }
        return false;
    }

    public Entity getEntityCollision(float xOffset, float yOffset) {
        return API.getWorld().getEntityManager().getEntities().stream().filter(e -> !e.equals(this)).filter(e -> e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset))).findFirst().orElse(null);
/*        for (Entity e : API.getWorld().getEntityManager().getEntities()) {
            if (e.equals(this)) continue;
            if (e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset))) return e;
        }
        return null;*/
    }

    public Rectangle getCollisionBounds(float xOffset, float yOffset) {
        return new Rectangle((int) (x + bounds.x + xOffset), (int) (y + bounds.y + yOffset), bounds.width, bounds.height);
    }

    public void kill() {
        setHealth(0);
    }



    public void addExp(double xp) {
        if (getLevel() >= MAX_LEVEL) {
            setXP(BASE_XP * MAX_LEVEL);
            setLevel(MAX_LEVEL);
            return;
        }
        setXP(getXp() + xp);

        if (getXPToNextLevel() <= 0) adjustLevel();
    }

    private void adjustLevel() {
        setXP(getXp() - xpToNextLevel());
        setLevel(getLevel() + 1);

        int oldMaxHealth = getMaxHealth();

        setDamage(getDamage() + (int) (DMG_UP_PER_LVL * getLevel()));
        setMaxHealth(getMaxHealth() + (int)(HEALTH_UP_PER_LVL * getLevel()));
        setHealth(getHealth() + (getMaxHealth() - oldMaxHealth));
        setArmor(getArmor() + (int)(ARMOR_UP_PER_LVL * getLevel()));

        if (getXPToNextLevel() <= 0) adjustLevel();
    }

    private double getXPToNextLevel() {
        return (BASE_XP * (getLevel() + 1)) - getXp();
    }

    protected int xpToNextLevel(){
        if (getLevel() == MAX_LEVEL) return BASE_XP * MAX_LEVEL;
        return BASE_XP * (getLevel() + 1);
    }


    protected BufferedImage getCurrentAnimationFrame() {
        switch (direction) {
            case 0:
                return animDown.getCurrentFrame();
            case 1:
                return animUp.getCurrentFrame();
            case 2:
                return animRight.getCurrentFrame();
            case 3:
                return animLeft.getCurrentFrame();
            default:
                return animDown.getCurrentFrame();
        }
    }

    public void dropItem(Item item){
        dropItem(item, 1);
    }
    public void dropItem(Item item, float percent){
        if (percent >= new Random().nextFloat()) API.getWorld().getItemManager().addItem(item.createNew((int) x, (int) y, item.getCount()));
    }


    public Location getLocation() {
        return new Location(this);
    }

    //Utils
    public void setLevel(int level) {
        this.level = level;
    }

    public void setXP(double xp) {
        if (xp <= 0) {
            this.xp = 0;
            return;
        }
        DecimalFormat df = new DecimalFormat("#.##");
        this.xp = Double.valueOf(df.format(xp).replaceAll(",", "."));
    }


    @Override
    public String toString() {
        return "Entity: {ID: " + getINTERNAL_ID() + ", Name: " + getINTERNAL_NAME() + ", X: " + getX() + ", Y: " + getY() + "}";
    }
}
