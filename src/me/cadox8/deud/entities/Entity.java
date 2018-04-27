package me.cadox8.deud.entities;

import lombok.Getter;
import lombok.Setter;
import me.cadox8.deud.ai.AI;
import me.cadox8.deud.api.API;
import me.cadox8.deud.attributes.Knockback;
import me.cadox8.deud.entities.creatures.Creature;
import me.cadox8.deud.gfx.Animation;
import me.cadox8.deud.items.Item;
import me.cadox8.deud.utils.Location;
import me.cadox8.deud.utils.Log;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import java.util.Random;

public abstract class Entity {

    protected static final int DEFAULT_HEALTH = 10;
    protected static final int DEFAULT_DAMAGE = 5;
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

    public Entity(API API, float x, float y, int width, int height, int level) {
        this.API = API;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.level = level;

        health = DEFAULT_HEALTH;
        damage = DEFAULT_DAMAGE;
        armor = DEFAULT_ARMOR;

        this.location = new Location(API.getWorld(), x, y, direction); //No more nulls :D

        bounds = new Rectangle(0, 0, width, height);
    }


    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract void specialRender(Graphics g);
    public abstract void die();


    public void hurt(Entity attacker) {
        if (!isDamageable()) return;

        int amt = attacker.getDamage() + (int) (DMG_UP_PER_LVL * attacker.getLevel());
        health -= amt;

        if (API.isDebug()) Log.log("Health: " + getHealth());

        if (this instanceof Creature) new Knockback(0.25, attacker, (Creature) this).perform();

        if (health <= 0) {
            active = false;
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
        for (Entity e : API.getWorld().getEntityManager().getEntities()) {
            if (e.equals(this)) continue;
            if (e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset))) return e;
        }
        return null;
    }

    public Rectangle getCollisionBounds(float xOffset, float yOffset) {
        return new Rectangle((int) (x + bounds.x + xOffset), (int) (y + bounds.y + yOffset), bounds.width, bounds.height);
    }


    public void addExp(double xp) {
        if (getLevel() >= MAX_LEVEL) {
            setXP(BASE_XP * MAX_LEVEL);
            setLevel(MAX_LEVEL);
            return;
        }
        setXP(getXp() + xp);

        if (getXPToNextLevel() <= 0) ajustLevel();
    }

    private void ajustLevel() {
        setXP(getXp() - xpToNextLevel());
        setLevel(getLevel() + 1);

        int oldMaxHealth = getMaxHealth();

        setDamage(getDamage() + (int) (DMG_UP_PER_LVL * getLevel()));
        setMaxHealth(getMaxHealth() + (int)(HEALTH_UP_PER_LVL * getLevel()));
        setHealth(getHealth() + (getMaxHealth() - oldMaxHealth));
        setArmor(getArmor() + (int)(ARMOR_UP_PER_LVL * getLevel()));

        if (getXPToNextLevel() <= 0) ajustLevel();
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
        return new Location(API.getWorld(), getX(), getY(), getDirection());
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
}
