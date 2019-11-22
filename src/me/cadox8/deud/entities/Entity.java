package me.cadox8.deud.entities;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import me.cadox8.deud.ai.AI;
import me.cadox8.deud.animations.Animation;
import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.entities.creatures.Creature;
import me.cadox8.deud.entities.creatures.monsters.Monster;
import me.cadox8.deud.entities.creatures.npcs.Npc;
import me.cadox8.deud.entities.creatures.player.Player;
import me.cadox8.deud.entities.projectile.Projectile;
import me.cadox8.deud.events.projectiles.ProjectileHitEvent;
import me.cadox8.deud.items.Item;
import me.cadox8.deud.items.weapons.WeaponItem;
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

    private static final double DMG_UP_PER_LVL = 0.13;
    private static final double HEALTH_UP_PER_LVL = 0.17;
    private static final double ARMOR_UP_PER_LVL = 0.09;

    @Getter @Setter protected GameAPI gameAPI;

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

    @Getter @Setter protected Rectangle bounds;

    @Getter @Setter private Entity collisionEntity;

    @Getter @Setter protected Entity killer;

    @Getter @Setter protected Animation animDown, animUp, animLeft, animRight;
    @Getter @Setter protected Animation[] animations = new Animation[4];

    public Entity(int id, String name, @NonNull GameAPI gameAPI, float x, float y, int width, int height, int level) {
        INTERNAL_ID = id;
        INTERNAL_NAME = name;
        this.gameAPI = gameAPI;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.level = level;

        health = DEFAULT_HEALTH;
        damage = DEFAULT_DAMAGE;
        armor = DEFAULT_ARMOR;

        bounds = new Rectangle(0, 0, width, height);
    }


    public abstract void tick();
    public abstract void fixAnimations();
    public abstract void render(Graphics g);
    public abstract void preRender(Graphics g);
    public abstract void getHurt();
    public abstract void die();

    public void hurt(Entity attacker) {
        Log.log(getINTERNAL_NAME() + " Health: " + getHealth());
        getHurt();
        if (!isDamageable()) return;

        int amt = attacker.getDamage() + (int) (DMG_UP_PER_LVL * attacker.getLevel());
        if (attacker instanceof Monster && ((Monster)attacker).getItemInHand() instanceof WeaponItem) amt += ((WeaponItem) ((Monster) attacker).getItemInHand()).getDamage();
        health -= amt;

        if (this instanceof Creature) {
            if (attacker instanceof Monster) ((Monster)attacker).getItemInHand().getAttributes().forEach(a -> a.perform(attacker, this));
            if (attacker instanceof Npc) ((Npc)attacker).getItems().get(0).getAttributes().forEach(a -> a.perform(attacker, this));
            if (attacker instanceof Player) ((Player) attacker).getInventory().getUsableItem().getAttributes().forEach(a -> a.perform(attacker, this));
        }

        if (attacker instanceof Projectile) {
            new ProjectileHitEvent(getGameAPI(), ((Projectile)attacker), this).onEvent();
        }

        if (health <= 0) {
            if (!(this instanceof Player)) active = false;
            this.killer = attacker;
            die();
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
        if (item == null) return;
        if (percent >= new Random().nextFloat()) gameAPI.getWorld().getItemManager().addItem(item.createNew((int) x, (int) y, item.getCount()));
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
        this.xp = Double.parseDouble(new DecimalFormat("#.##").format(xp).replaceAll(",", "."));
    }


    @Override
    public String toString() {
        return "Entity: {ID: " + getINTERNAL_ID() + ", Name: " + getINTERNAL_NAME() + ", X: " + getX() + ", Y: " + getY() + "}";
    }
}
