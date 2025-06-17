package es.cadox8.deud.items;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import es.cadox8.deud.api.GameAPI;
import es.cadox8.deud.entities.creatures.player.Player;
import es.cadox8.deud.graphics.textures.Assets;
import es.cadox8.deud.entities.components.inventory.Inventory;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public abstract class Item {

    @Getter @Setter protected GameAPI gameAPI;

    @Getter protected final int id;
    @Getter protected final BufferedImage texture;
    @Getter @Setter protected String name;
    @Getter protected final ItemType type;

    @Getter protected final Rectangle bounds;

    @Getter protected int x, y, count;
    @Getter @Setter protected boolean pickedUp = false;

    @Getter @Setter protected boolean infinity;
    @Getter @Setter protected float durability;

    @Getter @Setter protected double sellAmount = 0;
    @Getter @Setter protected double buyAmount = 0;

    @Getter @Setter protected double damage = 0;

    public Item(BufferedImage texture, int id, String name, ItemType type) {
        this.texture = texture == null ? Assets.bug : texture;
        this.id = id;
        this.name = name;
        this.count = 1;
        this.type = type;

        this.infinity = false;
        this.durability = 1.0f;

        bounds = new Rectangle(x, y, 64, 64);
    }

    public abstract void use(@NonNull Player p);

    @Deprecated
    public abstract Item createNew(int x, int y, int count);

    public void tick() {
        if (gameAPI.getWorld().getPlayer().getCollisionBounds(0f, 0f).intersects(bounds)) {
            pickedUp = true;
            gameAPI.getWorld().getPlayer().getPlayerInventory().add(this);
        }
    }

    public void removeItem(@NonNull Player p) {
        if (getCount() == 1) {
            p.getPlayerInventory().remove(this);
            p.getPlayerInventory().setItemInHand(Items.HAND.item());
            return;
        }
        count--;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
        bounds.x = x;
        bounds.y = y;
    }

    public Item addCount(int count) {
        return setCount(getCount() + count);
    }

    public Item setCount(int count) {
        this.count = count;
        return this;
    }

    public Item randomAmount(int min, int max) {
        this.setCount(new Random().nextInt(max + 1 - min) + min);
        return this;
    }

    public static Item get(int id) {
        return Items.getItem(id);
    }

    public void render(Graphics g) {
        if (gameAPI == null) return;
        render(g, (int) (x - gameAPI.getGameCamera().getXOffset()), (int) (y - gameAPI.getGameCamera().getYOffset()));
    }

    public void render(Graphics g, int x, int y) {
        g.drawImage(texture, x, y, 32, 32, null);
    }

    @Override
    public String toString() {
        return "Item:{ID: " + id + ", Name: " + name + ", Amount: " + count + "}";
    }

    public enum ItemType {
        NONE, OBJECT, FOOD, POTION, WEAPON
    }
}
