package me.cadox8.deud.items;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.attributes.Attribute;
import me.cadox8.deud.entities.creatures.player.Player;
import me.cadox8.deud.graphics.textures.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public abstract class Item {

    private static final Item[] items = new Item[9]; //All items


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

    public static Item get(int id) {
        return items[id];
    }


    public void removeItem(@NonNull Player p) {
        if (getCount() == 1) {
            p.getPlayerInventory().removeItem(this);
            p.getPlayerInventory().setUsableItem(Items.getHand());
            return;
        }
        count--;
    }

    public Item addAttributes(@NonNull Attribute... attributes) {
        this.attributes.addAll(Arrays.asList(attributes));
        return this;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
        bounds.x = x;
        bounds.y = y;
    }

    public static Item getRandom() {
        return getRandom(Items.getBugItem().getId());
    }
    public static Item getRandom(Item... banedIDs) {
        final Integer[] ids = new Integer[banedIDs.length];
        ids[0] = Items.getBugItem().getId();
        int x = 1;

        for (Item i : banedIDs) {
            ids[x] = i.getId();
            x++;
        }
        return getRandom(ids);
    }
    public static Item getRandom(Integer... banedIDs) {
        final List<Integer> baned = Arrays.asList(banedIDs);
        final Item i = get(new Random().nextInt(items.length));

        i.setCount(1);

        if (banedIDs.length >= items.length) return Items.getBugItem();
        if (baned.contains(i.getId()) || i.getId() == Items.getBugItem().getId()) return getRandom(banedIDs);
        return i;
    }

    public Item randomAmount(int min, int max) {
        final Random r = new Random();
        setCount(r.nextInt((max - min) + 1) + min);
        return this;
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
