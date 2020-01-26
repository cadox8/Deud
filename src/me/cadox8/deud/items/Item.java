package me.cadox8.deud.items;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.attributes.Attribute;
import me.cadox8.deud.entities.creatures.player.Player;
import me.cadox8.deud.gfx.textures.Assets;
import me.cadox8.deud.items.food.ChickenItem;
import me.cadox8.deud.items.objects.*;
import me.cadox8.deud.items.potions.HealthPotion;
import me.cadox8.deud.items.potions.PotionItem;
import me.cadox8.deud.items.weapons.HandItem;
import me.cadox8.deud.items.weapons.SwordItem;
import me.cadox8.deud.items.weapons.WeaponItem;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public abstract class Item {

    public static Item[] items = new Item[9]; //All items

    //Bug
    public static final ObjectItem bugItem = new BugItem(Assets.bug, 5, "3RR0R");

    //Objects
    public static final ObjectItem woodItem = new WoodItem(Assets.wood, 0, "Wood");
    public static final ObjectItem rockItem = new RockItem(Assets.stone, 1, "Rock");
    public static final ObjectItem keyItem = new KeyItem(Assets.key, 2, "Key");
    public static final ObjectItem mapItem = new MapItem(null, 7, "Map");

    //Food
    public static final ChickenItem chickenItem = new ChickenItem(Assets.food, 3, "Chicken", 3);

    //Weapons
    public static final WeaponItem hand = new HandItem(Assets.hand, 4, "Hand", 1);
    public static final WeaponItem sword = new SwordItem(Assets.sword, 8, "Sword", 5);

    // Potions
    public static final PotionItem healthPotion = new HealthPotion(6, 1);


    // Class
    public static final int ITEMWIDTH = 64, ITEMHEIGHT = 64;

    @Getter @Setter protected GameAPI gameAPI;
    @Getter protected final BufferedImage texture;
    @Getter protected final int id;
    @Getter @Setter protected String name;

    @Getter @Setter private List<Attribute> attributes;

    @Getter @Setter protected Rectangle bounds;

    @Getter protected int x, y, count;
    @Getter @Setter protected boolean pickedUp = false;

    @Getter @Setter protected boolean infinity = false;

    @Getter @Setter protected double sellAmount = 0;
    @Getter @Setter protected double buyAmount = 0;

    public Item(BufferedImage texture, int id, String name) {
        this.texture = texture == null ? Assets.bug : texture;
        this.id = id;
        this.name = name;
        this.count = 1;
        this.attributes = new ArrayList<>();

        bounds = new Rectangle(x, y, ITEMWIDTH, ITEMHEIGHT);

        items[id] = this;
    }

    public abstract void use(@NonNull Player p);
    public abstract Item createNew(int x, int y, int count);

    public void removeItem(@NonNull Player p) {
        if (getCount() == 1) {
            p.getPlayerInventory().removeItem(this);
            p.getPlayerInventory().setUsableItem(Item.hand);
            return;
        }
        count--;
    }

    public Item addAttributes(@NonNull Attribute... attributes) {
        this.attributes.addAll(Arrays.asList(attributes));
        return this;
    }


    public void tick(){
        if(gameAPI.getWorld().getPlayer().getCollisionBounds(0f, 0f).intersects(bounds)){
            pickedUp = true;
            gameAPI.getWorld().getPlayer().getPlayerInventory().addItem(this);
        }
    }

    public void render(Graphics g){
        if(gameAPI == null) return;
        render(g, (int) (x - gameAPI.getGameCamera().getXOffset()), (int) (y - gameAPI.getGameCamera().getYOffset()));
    }

    public void render(Graphics g, int x, int y){
        g.drawImage(texture, x, y, 32, 32, null);
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
        bounds.x = x;
        bounds.y = y;
    }

    public static Item getRandom() {
        return getRandom(5);
    }
    public static Item getRandom(Item... banedIDs) {
        final Integer[] ids = new Integer[banedIDs.length];
        int x = 0;

        for (Item i : banedIDs) {
            ids[x] = i.getId();
            x++;
        }
        return getRandom(ids);
    }
    public static Item getRandom(Integer... banedIDs) {
        final List<Integer> baned = Arrays.asList(banedIDs);
        final Item i = items[new Random().nextInt(items.length)];

        i.setCount(1);

        if (banedIDs.length >= items.length) return bugItem;
        if (baned.contains(i.getId()) || i.getId() == bugItem.getId()) return getRandom(banedIDs);
        return i;
    }

    public Item addCount(int count) {
        return setCount(getCount() + count);
    }
    public Item setCount(int count) {
        this.count = count;
        return this;
    }

    @Override
    public String toString() {
        return "Item:{ID: " + id + ", Name: " + name + ", Amount: " + count + "}";
    }
}
