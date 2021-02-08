package me.cadox8.deud.entities.creatures.player;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import me.cadox8.deud.animations.Animation;
import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.audio.Sounds;
import me.cadox8.deud.entities.Entity;
import me.cadox8.deud.entities.EntityData;
import me.cadox8.deud.entities.Location;
import me.cadox8.deud.entities.creatures.Creature;
import me.cadox8.deud.graphics.fonts.Text;
import me.cadox8.deud.graphics.textures.Models;
import me.cadox8.deud.inventory.creature.PlayerInventory;
import me.cadox8.deud.items.Item;
import me.cadox8.deud.items.ItemType;
import me.cadox8.deud.items.Items;
import me.cadox8.deud.items.weapons.WeaponItem;
import me.cadox8.deud.managers.EntityManager;
import me.cadox8.deud.quests.Quest;
import me.cadox8.deud.utils.Log;
import me.cadox8.deud.ux.hud.Hud;
import me.cadox8.deud.ux.options.Options;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Arrays;

public class Player extends Creature {

    @Getter @Setter private String nick;

    //Stamina
    @Getter @Setter private int maxHunger;
    @Getter @Setter private double hunger;

    //Money
    @Getter @Setter private double money = 0;

    //Utils
    private float old_speed = -1;

    @Getter @Setter private Quest assignedQuest;

    @Getter @Setter private Options options;

    public Player(@NonNull GameAPI gameAPI, float x, float y) {
        super(1, "Player", EntityData.EntityType.PLAYER, gameAPI, x, y, DEFAULT_CREATURE_WIDTH, DEFAULT_CREATURE_HEIGHT);

        setMoving(false);

        bounds.x = 11;
        bounds.y = 44;
        bounds.width = 43;
        bounds.height = 21;

        // Animations
        animDown = new Animation((int)(speed * 0.2), Models.player_down);
        animUp = new Animation((int)(speed * 0.2), Models.player_up);
        animLeft = new Animation((int)(speed * 0.2), Models.player_left);
        animRight = new Animation((int)(speed * 0.2), Models.player_right);

        inventory = new PlayerInventory(gameAPI, this);

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

        if (this.getGameAPI().getGame().getPlayerData() != null) {
            final PlayerData pd = this.getGameAPI().getGame().getPlayerData();
            final Location loc = pd.getLocation();

            setNick(pd.getNick());
            setHealth(pd.getHealth());
            setMoney(pd.getMoney());

            setLevel(pd.getLevel());
            setXP(pd.getExperience());

            setX(loc.getX());
            setY(loc.getY());
            setDirection(loc.getDirection());

            Log.system("Player nick: " + getNick());

            Arrays.asList(pd.getInventory()).forEach(items -> {
                final Item i = Item.get(items.getId());
                i.setCount(items.getCount());
                inventory.addItem(i);
            });
            getPlayerInventory().setUsableItem(gameAPI.getGame().getPlayerData().getItem());
        } else {
            getPlayerInventory().setUsableItem(Items.getHand());
        }

        options = new Options(gameAPI, this);
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
        gameAPI.getGameCamera().centerOnEntity(this);

        // Attack
        EntityManager.checkAttacks(this);

        // Inventory
        inventory.tick();

        if (options.isEnabled()) options.tick();
    }

    @Override
    public void die() {
        Log.danger("You lose");
        gameAPI.getWorld().getEntityManager().freezeAll();
    }


    @Override
    public void render(Graphics g) {
        g.drawImage(getCurrentAnimationFrame(), (int) (x - gameAPI.getGameCamera().getXOffset()), (int) (y - gameAPI.getGameCamera().getYOffset()), width, height, null);

        if (gameAPI.isDebug()) {
            g.setColor(Color.black);
            g.fillRect(gameAPI.getWidth() - 130, 30, 130, 30);
            g.setColor(Color.WHITE);
            g.drawString("X: " + x + " Y: " + y + " Dir: " + direction, gameAPI.getWidth() - 120, 10);
        }
    }

    public void postRender(Graphics g) {
        //Cooldown
/*        g.drawImage(Assets.xp, 10, (Assets.HEIGHT * 2) + 8, 32, 32, null);
        Text.drawString(g, attackTimer >= 300 ? "Attack" : attackTimer + "ms", 35, (Assets.HEIGHT * 3) - 3, 2);*/

        new Hud(this).render(g);

        inventory.render(g);

        if (getHealth() <= 0) {
            Text.drawString(g, "You lose", 125, 530, Color.BLACK, 3);
            Text.drawString(g, ":(", 367, 515, Color.BLACK, 0);
        }

        if (options.isEnabled()) options.render(g);
    }

    private void getInput() {
        xMove = 0;
        yMove = 0;

        if (gameAPI.getKeyManager().tests) {
            setHunger(getMaxHunger());
            addExp(20);
            setHealth(getMaxHealth());
            inventory.addItem(Items.getSword());
        }

        if (gameAPI.getKeyManager().keyJustPressed(KeyEvent.VK_ESCAPE)) {
            gameAPI.getEntityManager().freezeAll();
            options.setEnabled(!options.isEnabled());
        }

        if (gameAPI.getKeyManager().debug) {
            gameAPI.setDebug(!gameAPI.isDebug());
            Log.log("Debug mode " + (gameAPI.isDebug() ? "Enabled" : "Disabled"));
        }

        if (gameAPI.getKeyManager().keyJustPressed(KeyEvent.VK_SPACE)) {
            final Entity en = EntityManager.getEntity(this, 0, 0);
            new EntityInteract(gameAPI, en, this).interact();
        }

        if (isFreeze()) return;

        setMoving(gameAPI.getKeyManager().up || gameAPI.getKeyManager().down || gameAPI.getKeyManager().left || gameAPI.getKeyManager().right);

        if (gameAPI.getKeyManager().up) {
            yMove = -speed;
            setDirection(1);
            Sounds.ENTITY_WALK_GRASS.play();
        }
        if (gameAPI.getKeyManager().down) {
            yMove = speed;
            setDirection(0);
            Sounds.ENTITY_WALK_GRASS.play();
        }
        if (gameAPI.getKeyManager().left) {
            xMove = -speed;
            setDirection(3);
            Sounds.ENTITY_WALK_GRASS.play();
        }
        if (gameAPI.getKeyManager().right) {
            xMove = speed;
            setDirection(2);
            Sounds.ENTITY_WALK_GRASS.play();
        }

        if (gameAPI.getMouseManager().isRightPressed()) getPlayerInventory().getUsableItem().use(this);

        if (gameAPI.getKeyManager().shift) {
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

    public PlayerInventory getPlayerInventory() {
        return (PlayerInventory) inventory;
    }

    public boolean hasMoney(double amount) {
        return money >= amount;
    }
}
