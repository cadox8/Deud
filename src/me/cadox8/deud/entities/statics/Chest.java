package me.cadox8.deud.entities.statics;

import lombok.Getter;
import lombok.NonNull;
import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.entities.creatures.player.Player;
import me.cadox8.deud.gfx.textures.Assets;
import me.cadox8.deud.items.Item;
import me.cadox8.deud.tiles.Tile;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Chest extends StaticEntity {

    @Getter private ArrayList<Item> inventoryItems;

    // Only for instances
    protected Chest(int id, String name, @NonNull GameAPI gameAPI, float x, float y) {
        super(id, name, gameAPI, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);
    }

    public Chest(@NonNull GameAPI gameAPI, float x, float y) {
        this(gameAPI,x, y, false);
    }
    public Chest(@NonNull GameAPI gameAPI, float x, float y, boolean explosive) {
        super(501, "Chest", gameAPI, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);

        setDamageable(false);
        setExplosive(explosive);

        setDamage(3);
        setLevel(0);

        inventoryItems = new ArrayList<>();

        bounds.x = 2;
        bounds.y = (int) (height / 2f) - 5;
        bounds.width = width - 6;
        bounds.height = (int) (height - height / 2f);
    }

    public void open(@NonNull Player p) {

    }

    public void addItems(@NonNull Item... items) {
        Arrays.asList(items).forEach(this::addItem);
    }
    public void addItem(@NonNull Item item) {
        inventoryItems.stream().filter(i -> i.getId() == item.getId()).findFirst().ifPresentOrElse(i -> i.addCount(item.getCount()), () -> inventoryItems.add(item));
    }

    public void removeItem(@NonNull Item item) {
        if (inventoryItems.size() == 0) return;
        inventoryItems.stream().filter(it -> it.getId() == item.getId()).findFirst().ifPresent(i -> {
            if (i.getCount() - item.getCount() <= 0) {
                inventoryItems.remove(item);
                return;
            }
            i.setCount(i.getCount() - item.getCount());
        });
    }

    public boolean hasItem(int item) {
        return inventoryItems.stream().anyMatch(i -> i.getId() == item);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.chest, (int) (x - gameAPI.getGameCamera().getXOffset()), (int) (y - gameAPI.getGameCamera().getYOffset()), width, height, null);
    }
}
