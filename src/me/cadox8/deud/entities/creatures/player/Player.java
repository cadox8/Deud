package me.cadox8.deud.entities.creatures.player;

import lombok.Getter;
import lombok.Setter;
import me.cadox8.deud.api.API;
import me.cadox8.deud.audio.Sound;
import me.cadox8.deud.entities.Entity;
import me.cadox8.deud.entities.creatures.Creature;
import me.cadox8.deud.gfx.Animation;
import me.cadox8.deud.gfx.fonts.Text;
import me.cadox8.deud.gfx.textures.Assets;
import me.cadox8.deud.gfx.textures.Models;
import me.cadox8.deud.inventory.Inventory;
import me.cadox8.deud.items.Item;
import me.cadox8.deud.items.weapons.WeaponItem;
import me.cadox8.deud.saves.FileUtils;
import me.cadox8.deud.saves.PlayerData;
import me.cadox8.deud.settings.Settings;
import me.cadox8.deud.utils.Location;
import me.cadox8.deud.utils.Log;
import me.cadox8.deud.utils.Utils;
import me.cadox8.deud.worlds.Minimap;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;

public class Player extends Creature {

    // Inventory
    @Getter @Setter private Inventory inventory;

    //Stamina
    @Getter @Setter private int maxHunger;
    @Getter @Setter private double hunger;

    //Money
    @Getter @Setter private int money = 0;

    //Utils
    private float old_speed = -1;
    private Minimap map;

    private Settings.Gamemode gamemode = Settings.Gamemode.SURVIVAL;

    public Player(API API, float x, float y) {
        super(1, "Player", API, x, y, DEFAULT_CREATURE_WIDTH, DEFAULT_CREATURE_HEIGHT);

        bounds.x = 20;
        bounds.y = 44;
        bounds.width = 25;
        bounds.height = 19;

        //Animatons
        animDown = new Animation((int)(speed * 600), Models.player_down);
        animUp = new Animation((int)(speed * 600), Models.player_up);
        animLeft = new Animation((int)(speed * 600), Models.player_left);
        animRight = new Animation((int)(speed * 600), Models.player_right);

        inventory = new Inventory(API, this);
        inventory.setUsableItem(Item.hand);

        setMaxHunger(10);
        setHunger(getMaxHunger());

        setMaxHealth(10);
        setHunger(getMaxHealth());

        setDamage(DEFAULT_DAMAGE);
        setArmor(DEFAULT_ARMOR);

        setAttackCooldown(300);

        animations[0] = animDown;
        animations[1] = animUp;
        animations[2] = animLeft;
        animations[3] = animRight;

        if (getAPI().getGame().getPlayerData() != null) {
            final PlayerData pd = getAPI().getGame().getPlayerData();
            final Location loc = pd.getLocation();

            setHealth(pd.getHealth());
            setMoney(pd.getMoney());
            setLocation(loc);

            setX(loc.getX());
            setY(loc.getY());
            setDirection(loc.getDirection());

            Arrays.asList(pd.getInventory()).forEach(items -> items.forEach((id, count) -> {
                final Item i = Item.items[id];
                i.setCount(count);
                inventory.addItem(i);
            }));
        }
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
        API.getGameCamera().centerOnEntity(this);

        // Attack
        checkAttacks();

        // Inventory
        inventory.tick();

        if (!(inventory.getUsableItem() instanceof WeaponItem)) {
            setDamage(DEFAULT_DAMAGE);
        } else {
            setDamage(((WeaponItem) inventory.getUsableItem()).getDamage() + DEFAULT_DAMAGE);
        }
    }

    @Override
    public void die() {
        Log.log(Log.LogType.DANGER, "You lose");
        API.getWorld().getEntityManager().freezeCreatures();
        API.getWorld().getEntityManager().freezePlayer();
        //System.exit(0);
    }


    @Override
    public void render(Graphics g) {
        g.drawImage(getCurrentAnimationFrame(), (int) (x - API.getGameCamera().getXOffset()), (int) (y - API.getGameCamera().getYOffset()), width, height, null);

        inventory.render(g);

        //if (API.isDebug()) {
            g.setColor(Color.WHITE);
            g.drawString("X: " + x + " Y: " + y + " Dir: " + direction, 1105, 795);
        //}
    }

    public void postRender(Graphics g) {
        renderInfo(g);
        map.paintMap(g);
        inventory.render(g);
    }

    private void renderInfo(Graphics g) {
        if (inventory.isActive()) {
            //Money
            g.drawImage(Assets.coin, 10, 5, 32, 32, null);
            Text.drawString(g, "x" + getMoney(), 35, Assets.HEIGHT - 3, 2);

            //Keys (?)
            g.drawImage(Assets.key, 10, Assets.HEIGHT + 8, 32, 32, null);
            Text.drawString(g, "x" + inventory.keyCount(), 35, (Assets.HEIGHT * 2) - 3, 2);

            //XP
            drawImage(g, Assets.xp, 4);
            drawString(g, "Level: " + getLevel(), 4);
            if (getLevel() != 30) drawString(g, "XP: " + getXp() + "/" + xpToNextLevel(), 5);
        }

        //Cooldown
        g.drawImage(Assets.xp, 10, (Assets.HEIGHT * 2) + 8, 32, 32, null);
        Text.drawString(g, attackTimer >= 300 ? "Attack" : attackTimer + "ms", 35, (Assets.HEIGHT * 3) - 3, 2);

        //Damage
        drawImage(g, Assets.sword, 0);
        drawString(g, getDamage(), 0);

        //Health
        drawImage(g, Assets.hearth, 1);
        drawString(g, getHealth() + "/" + getMaxHealth(), 1);

        //FoodItem
        drawImage(g, Assets.food, 2);
        drawString(g, Utils.round(2, getHunger()) + "/" + getMaxHunger(), 2);

        //Armor
        drawImage(g, Assets.shield, 3);
        drawString(g, getArmor(),  3);

        //Item
        g.drawImage(inventory.getUsableItem().getTexture(), 1152, 670, null);
        Text.drawString(g, inventory.getUsableItem().getName(), 1150, 686 + Assets.HEIGHT, 2);

        if (getHealth() <= 0) {
            Text.drawString(g, "You lose", 125, 530, Color.BLACK, 3);
            Text.drawString(g, ":(", 367, 515, Color.BLACK, 0);
        }
    }

    private void getInput() {
        xMove = 0;
        yMove = 0;

        if (API.getKeyManager().tests) {
            setHunger(getMaxHunger());
            addExp(20);
            setHealth(getMaxHealth());
            API.getWorld().getEntityManager().freezeCreatures();
        }

        if (API.getKeyManager().esc) {
            FileUtils.save(this);
            System.exit(0);
        }

        if (API.getKeyManager().debug) {
            Sound.ENTITY_WALK_GRASS.playSound();
            API.setDebug(!API.isDebug());
        }

        if (isFreeze()) return;


        if (API.getKeyManager().up) {
            yMove = -speed;
            setDirection(1);
        }
        if (API.getKeyManager().down) {
            yMove = speed;
            setDirection(0);
        }
        if (API.getKeyManager().left) {
            xMove = -speed;
            setDirection(3);
        }
        if (API.getKeyManager().right) {
            xMove = speed;
            setDirection(2);
        }

        if (API.getMouseManager().isRightPressed()) inventory.getUsableItem().use(this);

        if (API.getKeyManager().shift) {
            if (hunger <= 0.0) {
                hunger = 0;
                if (old_speed != -1) setSpeed(old_speed);
                return;
            }
            if (old_speed == -1) old_speed = getSpeed();
            setSpeed(5.0f);
            hunger -= 0.02;
        } else {
            if (old_speed != -1) {
                setSpeed(old_speed);
                old_speed = -1;
            }
        }
    }

    private void checkAttacks() {
        attackTimer += System.currentTimeMillis() - lastAttackTimer;
        lastAttackTimer = System.currentTimeMillis();
        if (attackTimer < attackCooldown) {
            if (API.isDebug()) Log.log("Attack in cooldown: " + attackTimer + "/" + attackCooldown);
            return;
        }
        if (inventory.isActive()) return;

        Rectangle cb = getCollisionBounds(0, 0);
        Rectangle ar = new Rectangle();
        int arSize = 20;
        ar.width = arSize;
        ar.height = arSize;

        if (!API.getMouseManager().isLeftPressed()) return;

        switch (getDirection()) {
            case 0:
                ar.x = cb.x + cb.width / 2 - arSize / 2;
                ar.y = cb.y + cb.height;
                break;
            case 1:
                ar.x = cb.x + cb.width / 2 - arSize / 2;
                ar.y = cb.y - arSize;
                break;
            case 2:
                ar.x = cb.x - arSize;
                ar.y = cb.y + cb.height / 2 - arSize / 2;
                break;
            case 3:
                ar.x = cb.x + cb.width;
                ar.y = cb.y + cb.height / 2 - arSize / 2;
                break;
        }

        attackTimer = 0;

        for (Entity e : API.getWorld().getEntityManager().getEntities()) {
            if (e.equals(this)) continue;
            if (e.getCollisionBounds(0, 0).intersects(ar)) {
                e.hurt(this);
                return;
            }
        }
    }

    //
    private void drawImage(Graphics g, BufferedImage image, int pos){
        int infoY = 600;
        int infoX = 10;
        int y = infoY;

        if (pos != 0) y += Assets.HEIGHT * pos;
        if (pos == 4) infoX += 3;

        g.drawImage(image, infoX, y, 32, 32, null);
    }

    private void drawString(Graphics g, double value, int pos){
        drawString(g, value + "", pos);
    }
    private void drawString(Graphics g, String text, int pos){
        int infoY = 621;
        int infoX = 45;
        int y = infoY;

        if (pos != 0) y += (Assets.HEIGHT * pos);
        if (pos == 4) y -= 6;
        if (pos == 5) y = infoY + (Assets.HEIGHT * (pos - 1)) + 9;

        Text.drawString(g, text, infoX, y, 2);
    }

    private boolean isInDoor(int map) {
        switch (map) {
            case 0:
                return getX() >= 490 && getX() <= 531 && getY() >= 452 && getY() <= 520;
            default:
                return false;
        }
    }

    public void loadMiniMap(){
        map = new Minimap(API.getWorld(), this);
    }
}
