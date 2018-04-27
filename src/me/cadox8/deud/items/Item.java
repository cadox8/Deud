package me.cadox8.deud.items;

import lombok.Getter;
import lombok.Setter;
import me.cadox8.deud.api.API;
import me.cadox8.deud.entities.creatures.player.Player;
import me.cadox8.deud.gfx.textures.Assets;
import me.cadox8.deud.items.food.ChickenItem;
import me.cadox8.deud.items.objects.KeyItem;
import me.cadox8.deud.items.objects.ObjectItem;
import me.cadox8.deud.items.objects.RockItem;
import me.cadox8.deud.items.objects.WoodItem;
import me.cadox8.deud.items.weapons.HandItem;
import me.cadox8.deud.items.weapons.WeaponItem;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.Random;

public abstract class Item {

    public static Item[] items = new Item[5]; //All items


    //Objects
    public static final ObjectItem woodItem = new WoodItem(Assets.wood, 0, "Wood");
    public static final ObjectItem rockItem = new RockItem(Assets.stone, 1, "Rock");
    public static final ObjectItem keyItem = new KeyItem(Assets.key, 2, "Key");

    //Food
    public static final ChickenItem chickenItem = new ChickenItem(Assets.food, 3, "Chicken", 3);

    //Weapons
    public static final WeaponItem hand = new HandItem(Assets.bug, 4, "Hand", 0);


    // Class
    public static final int ITEMWIDTH = 32, ITEMHEIGHT = 32;

    @Getter @Setter protected API API;
    @Getter @Setter protected final BufferedImage texture;
    @Getter protected final int id;
    @Getter @Setter protected String name;

    @Getter @Setter protected Rectangle bounds;

    @Getter @Setter protected int x, y, count;
    @Getter @Setter protected boolean pickedUp = false;


    public Item(BufferedImage texture, int id, String name){
        this.texture = texture;
        this.id = id;
        this.name = name;
        this.count = 1;

        bounds = new Rectangle(x, y, ITEMWIDTH, ITEMHEIGHT);

        items[id] = this;
    }


    public abstract void use(Player p);
    public abstract Item createNew(int x, int y, int count);

    public void removeItem(Player p) {
        if (getCount() == 1) {
            p.getInventory().removeItem(this);
            p.getInventory().setUsableItem(Item.hand);
            return;
        }
        count--;
    }


    public void tick(){
        if(API.getWorld().getEntityManager().getPlayer().getCollisionBounds(0f, 0f).intersects(bounds)){
            pickedUp = true;
            API.getWorld().getEntityManager().getPlayer().getInventory().addItem(this);
        }
    }

    public void render(Graphics g){
        if(API == null) return;
        render(g, (int) (x - API.getGameCamera().getXOffset()), (int) (y - API.getGameCamera().getYOffset()));
    }

    public void render(Graphics g, int x, int y){
        g.drawImage(texture, x, y, ITEMWIDTH, ITEMHEIGHT, null);
    }



    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
        bounds.x = x;
        bounds.y = y;
    }

    public static Item getRandom(Item... banedIDs) {
        Integer[] ids = new Integer[banedIDs.length];
        int x = 0;

        for (Item i : banedIDs) {
            ids[x] = i.getId();
            x++;
        }
        return getRandom(ids);
    }
    public static Item getRandom(Integer... banedIDs) {
        Item i = items[new Random().nextInt(items.length)];
        i.setCount(1);
        if (Arrays.asList(banedIDs).contains(i.getId())) return getRandom(banedIDs);
        return i;
    }


    @Override
    public String toString() {
        return "Item:{ID: " + id + ", Name: " + name + ", Amount: " + count + "}";
    }
}
