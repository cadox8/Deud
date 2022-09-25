package es.cadox8.deud.entities.creatures.player;

import es.cadox8.deud.animations.Animation;
import es.cadox8.deud.api.GameAPI;
import es.cadox8.deud.audio.SoundType;
import es.cadox8.deud.entities.enums.Direction;
import es.cadox8.deud.entities.enums.EntityType;
import es.cadox8.deud.graphics.fonts.Fonts;
import es.cadox8.deud.graphics.fonts.Text;
import es.cadox8.deud.graphics.textures.Models;
import es.cadox8.deud.entities.components.inventory.Inventory;
import es.cadox8.deud.entities.components.inventory.creature.PlayerInventory;
import es.cadox8.deud.items.Item;
import es.cadox8.deud.items.Items;
import es.cadox8.deud.managers.EntityManager;
import es.cadox8.deud.ux.hud.Hud;
import es.cadox8.deud.ux.options.Options;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import es.cadox8.deud.entities.Entity;
import es.cadox8.deud.entities.Location;
import es.cadox8.deud.entities.creatures.Creature;
import es.cadox8.deud.utils.Log;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Arrays;

public class Player extends Creature {

    @Getter private String nick;

    //Stamina
    @Getter @Setter private int maxStamina;
    @Getter @Setter private double stamina;

    //Money
    @Getter @Setter private double money = 0;

    //Utils
    private float old_speed = -1;

    private Hud hud;
    @Getter @Setter private Options options;

    public Player(@NonNull GameAPI gameAPI, float x, float y) {
        super("9ad2cbee-f134-480a-9681-edf174dde4bb", "Player", EntityType.PLAYER, gameAPI, x, y, DEFAULT_CREATURE_WIDTH, DEFAULT_CREATURE_HEIGHT);

        this.nick = "Arya";

        this.setMoving(false);

        this.bounds.x = 11;
        this.bounds.y = 44;
        this.bounds.width = 43;
        this.bounds.height = 21;

        // Animations
        this.animDown = new Animation((int)(speed * 0.2), Models.player_down);
        this.animUp = new Animation((int)(speed * 0.2), Models.player_up);
        this.animLeft = new Animation((int)(speed * 0.2), Models.player_left);
        this.animRight = new Animation((int)(speed * 0.2), Models.player_right);

        this.inventory = new PlayerInventory(this);

        this.setMaxStamina(10);
        this.setStamina(this.getMaxStamina());

        this.setMaxHealth(10);
        this.setHealth(this.getMaxHealth());

        this.setDamage(DEFAULT_DAMAGE);
        this.setArmor(DEFAULT_ARMOR);

        this.setAttackCooldown(300);

        this.animations[0] = animDown;
        this.animations[1] = animUp;
        this.animations[2] = animLeft;
        this.animations[3] = animRight;

        if (this.getGameAPI().getGame().getPlayerData() != null) {
            final PlayerData pd = this.getGameAPI().getGame().getPlayerData();
            final Location loc = pd.getLocation();

            this.setMaxHealth(pd.getMaxHealth());
            this.setHealth(pd.getHealth());

            this.setMaxStamina(pd.getMaxStamina());
            this.setStamina(pd.getStamina());

            this.setMoney(pd.getMoney());

            this.setX(loc.getX());
            this.setY(loc.getY());
            this.setDirection(loc.getDirection());

            Arrays.asList(pd.getInventory()).forEach(items -> {
                final Item i = Item.get(items.getId());
                i.setCount(items.getCount());
                this.inventory.add(i);
            });
            pd.getEquipment().keySet().forEach(k -> this.getPlayerInventory().setEquipment(k, pd.getEquipment().get(k)));
        } else {
            this.getPlayerInventory().setItemInHand(Items.HAND.item());
        }

        this.options = new Options(gameAPI, this);
        this.hud = new Hud(this);
    }

    @Override
    public void tick() {
        //Animations
        this.animDown.tick();
        this.animUp.tick();
        this.animRight.tick();
        this.animLeft.tick();

        //Movement
        this.getInput();
        this.move();
        this.gameAPI.getGameCamera().centerOnEntity(this);

        // Attack
        EntityManager.checkAttacks(this);

        // Inventory
        this.inventory.tick();
        this.hud.tick();

        if (this.options.isEnabled()) this.options.tick();
    }

    @Override
    public void die() {
        Log.danger("You lose");
        this.gameAPI.getWorld().getEntityManager().freezeAll();
    }


    @Override
    public void render(Graphics g) {
        g.drawImage(getCurrentAnimationFrame(), (int) (x - this.gameAPI.getGameCamera().getXOffset()), (int) (y - this.gameAPI.getGameCamera().getYOffset()), width, height, null);

        if (this.gameAPI.isDebug()) {
            g.setColor(Color.black);
            g.fillRect(this.gameAPI.getWidth() - 130, 30, 130, 30);
            g.setColor(Color.WHITE);
            g.drawString("X: " + x + " Y: " + y + " Dir: " + direction, gameAPI.getWidth() - 120, 10);
        }
    }

    public void postRender(Graphics g) {
        //Cooldown
/*        g.drawImage(Assets.xp, 10, (Assets.HEIGHT * 2) + 8, 32, 32, null);
        Text.drawString(g, attackTimer >= 300 ? "Attack" : attackTimer + "ms", 35, (Assets.HEIGHT * 3) - 3, 2);*/

        this.hud.render(g);

        this.inventory.render(g);

        if (this.getHealth() <= 0) {
            Text.drawString(g, "You lose", 125, 530, Color.BLACK, Fonts.DEUD_DEATH_SCREEN);
            Text.drawString(g, ":(", 367, 515, Color.BLACK, Fonts.DEUD_TALL);
        }

        if (this.options.isEnabled()) this.options.render(g);
    }

    private void getInput() {
        this.xMove = 0;
        this.yMove = 0;

        if (this.gameAPI.getKeyManager().tests) {
            this.setStamina(this.getMaxStamina());
            this.setHealth(getMaxHealth());
            this.inventory.add(Items.SWORD.item());
        }

        if (this.gameAPI.getKeyManager().keyJustPressed(KeyEvent.VK_ESCAPE)) {
            this.gameAPI.getEntityManager().freezeAll();
            this.options.setEnabled(!this.options.isEnabled());
        }

        if (this.gameAPI.getKeyManager().debug) {
            this.gameAPI.setDebug(!this.gameAPI.isDebug());
            Log.log("Debug mode " + (this.gameAPI.isDebug() ? "Enabled" : "Disabled"));
        }

        if (this.gameAPI.getKeyManager().keyJustPressed(KeyEvent.VK_SPACE)) {
            final Entity en = EntityManager.getEntity(this, 0, 0);
            new EntityInteract(this.gameAPI, en, this).interact();
        }

        if (this.isFreeze()) return;

        this.setMoving(this.gameAPI.getKeyManager().up || this.gameAPI.getKeyManager().down || this.gameAPI.getKeyManager().left || this.gameAPI.getKeyManager().right);

        if (this.gameAPI.getKeyManager().up) {
            this.yMove = -this.speed;
            this.setDirection(Direction.NORTH);
            Sounds.ENTITY_WALK_GRASS.play();
        }
        if (this.gameAPI.getKeyManager().down) {
            this.yMove = this.speed;
            this.setDirection(Direction.SOUTH);
            Sounds.ENTITY_WALK_GRASS.play();
        }
        if (this.gameAPI.getKeyManager().left) {
            this.xMove = -this.speed;
            this.setDirection(Direction.WEST);
            Sounds.ENTITY_WALK_GRASS.play();
        }
        if (this.gameAPI.getKeyManager().right) {
            this.xMove = this.speed;
            this.setDirection(Direction.EAST);
            Sounds.ENTITY_WALK_GRASS.play();
        }

        if (this.gameAPI.getMouseManager().isRightPressed()) this.getPlayerInventory().getItemInHand().use(this);

        if (this.gameAPI.getKeyManager().shift) {
            if (this.stamina <= 0.0) {
                this.stamina = 0;
                if (this.old_speed != -1) setSpeed(this.old_speed);
                return;
            }
            if (this.old_speed == -1) this.old_speed = getSpeed();
            this.setSpeed(5.0f);
            this.stamina -= 0.02;
        } else {
            if (this.old_speed != -1) {
                this.setSpeed(this.old_speed);
                this.old_speed = -1;
            }
        }
    }

    public PlayerInventory getPlayerInventory() {
        return (PlayerInventory) this.inventory;
    }

    public boolean hasMoney(double amount) {
        return this.money >= amount;
    }
}
