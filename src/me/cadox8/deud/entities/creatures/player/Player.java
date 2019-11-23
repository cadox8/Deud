package me.cadox8.deud.entities.creatures.player;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import me.cadox8.deud.animations.Animation;
import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.audio.Sounds;
import me.cadox8.deud.entities.Entity;
import me.cadox8.deud.entities.Location;
import me.cadox8.deud.entities.creatures.Creature;
import me.cadox8.deud.entities.creatures.npcs.Npc;
import me.cadox8.deud.gfx.fonts.Text;
import me.cadox8.deud.gfx.textures.Assets;
import me.cadox8.deud.gfx.textures.Models;
import me.cadox8.deud.inventory.PlayerInventory;
import me.cadox8.deud.items.Item;
import me.cadox8.deud.items.weapons.WeaponItem;
import me.cadox8.deud.managers.EntityManager;
import me.cadox8.deud.quests.Quest;
import me.cadox8.deud.saves.FileUtils;
import me.cadox8.deud.saves.PlayerData;
import me.cadox8.deud.states.GameState;
import me.cadox8.deud.utils.Log;
import me.cadox8.deud.utils.Utils;
import me.cadox8.deud.ux.dialog.Dialog;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Arrays;

public class Player extends Creature {

    @Getter @Setter private String nick;

    //Stamina
    @Getter @Setter private int maxHunger;
    @Getter @Setter private double hunger;

    //Money
    @Getter @Setter private int money = 0;

    //Utils
    private float old_speed = -1;

    @Getter @Setter private Quest assignedQuest;

    public Player(@NonNull GameAPI gameAPI, float x, float y) {
        super(1, "Player", gameAPI, x, y, DEFAULT_CREATURE_WIDTH, DEFAULT_CREATURE_HEIGHT);

        bounds.x = 20;
        bounds.y = 44;
        bounds.width = 30;
        bounds.height = 24;

        // Animations
        animDown = new Animation((int)(speed * 600), Models.player_down);
        animUp = new Animation((int)(speed * 600), Models.player_up);
        animLeft = new Animation((int)(speed * 600), Models.player_left);
        animRight = new Animation((int)(speed * 600), Models.player_right);

        inventory = new PlayerInventory(gameAPI, this);
        getPlayerInventory().setUsableItem(Item.hand);

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
        gameAPI.getGameCamera().centerOnEntity(this);

        // Attack
        EntityManager.checkAttacks(this);

        // Inventory
        inventory.tick();

        if (!(((PlayerInventory)inventory).getUsableItem() instanceof WeaponItem)) {
            setDamage(DEFAULT_DAMAGE);
        } else {
            setDamage(((WeaponItem) ((PlayerInventory)inventory).getUsableItem()).getDamage() + DEFAULT_DAMAGE);
        }
    }

    @Override
    public void die() {
        Log.log(Log.LogType.DANGER, "You lose");
        gameAPI.getWorld().getEntityManager().freezeAll();
        //System.exit(0);
    }


    @Override
    public void render(Graphics g) {
        g.drawImage(getCurrentAnimationFrame(), (int) (x - gameAPI.getGameCamera().getXOffset()), (int) (y - gameAPI.getGameCamera().getYOffset()), width, height, null);

        inventory.render(g);

        //if (GameAPI.isDebug()) {
            g.setColor(Color.WHITE);
            g.drawString("X: " + x + " Y: " + y + " Dir: " + direction, 1105, 795);
        //}
    }

    public void postRender(Graphics g) {
        renderInfo(g);
        //map.paintMap(g);
        inventory.render(g);
    }

    private void renderInfo(Graphics g) {
        if (((PlayerInventory)inventory).isActive()) {
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
/*        g.drawImage(Assets.xp, 10, (Assets.HEIGHT * 2) + 8, 32, 32, null);
        Text.drawString(g, attackTimer >= 300 ? "Attack" : attackTimer + "ms", 35, (Assets.HEIGHT * 3) - 3, 2);*/

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
        g.drawImage(((PlayerInventory)inventory).getUsableItem().getTexture(), 1160, 670, null);
        Text.drawString(g, ((PlayerInventory)inventory).getUsableItem().getName(), 1150, 686 + Assets.HEIGHT, false, Color.BLACK, 2);

        if (getHealth() <= 0) {
            Text.drawString(g, "You lose", 125, 530, Color.BLACK, 3);
            Text.drawString(g, ":(", 367, 515, Color.BLACK, 0);
        }
    }

    private void getInput() {
        xMove = 0;
        yMove = 0;

        if (gameAPI.getKeyManager().tests) {
            //Log.log("\n\n" + Utils.getNearbyEntities(getLocation(), 0.5).toString());
            //new Door(GameAPI, 0, 0, "main").changeWorld();
            setHunger(getMaxHunger());
            addExp(20);
            setHealth(getMaxHealth());
            gameAPI.getWorld().getEntityManager().freezeCreatures();
        }

        if (gameAPI.getKeyManager().esc) {
            FileUtils.save(this);
            System.exit(0);
        }

        if (gameAPI.getKeyManager().debug) {
            Sounds.ENTITY_WALK_GRASS.playSound();
            gameAPI.setDebug(!gameAPI.isDebug());
        }

        if (isFreeze()) return;

        if (gameAPI.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER)) {
            final Entity en = EntityManager.getEntity(this, 0, 0);
            if (en != null && en instanceof Npc) {
                final Npc npc = (Npc) en;
                if (npc.getText().isEmpty()) return;
                final Dialog dialog = new Dialog(gameAPI, this, npc);
                ((GameState) gameAPI.getGame().getGameState()).setDialog(dialog);
            }
        }

        if (gameAPI.getKeyManager().up) {
            yMove = -speed;
            setDirection(1);
        }
        if (gameAPI.getKeyManager().down) {
            yMove = speed;
            setDirection(0);
        }
        if (gameAPI.getKeyManager().left) {
            xMove = -speed;
            setDirection(3);
        }
        if (gameAPI.getKeyManager().right) {
            xMove = speed;
            setDirection(2);
        }

        if (gameAPI.getMouseManager().isRightPressed()) ((PlayerInventory)inventory).getUsableItem().use(this);

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


    //
    private void drawImage(Graphics g, BufferedImage image, int pos){
        int infoY = 644;
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
        int infoY = 665;
        int infoX = 45;
        int y = infoY;

        if (pos != 0) y += (Assets.HEIGHT * pos);
        if (pos == 4) y -= 6;
        if (pos == 5) y = infoY + (Assets.HEIGHT * (pos - 1)) + 9;

        Text.drawString(g, text, infoX, y, false, Color.BLACK, 2);
    }
}
