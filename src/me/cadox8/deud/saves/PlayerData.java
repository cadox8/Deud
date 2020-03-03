package me.cadox8.deud.saves;

import lombok.Data;
import me.cadox8.deud.entities.Location;
import me.cadox8.deud.items.Item;

@Data
public class PlayerData {

    private String nick;
    private double money = 0;
    private int health = 0;
    private LocationUtils location = new LocationUtils();
    private ItemUtils[] inventory = new ItemUtils[0];
    private ItemUtils item = new ItemUtils();

    public LocationUtils locUtils() {
        return location;
    }
    public Location getLocation() {
        return new Location(location.getX(), location.getY(), location.getDirection());
    }

    public Item getItem() {
        return Item.items[item.getId()].setCount(item.getCount());
    }

    public Item[] getInventory() {
        if (inventory == null) return new Item[0];
        final Item[] it = new Item[inventory.length];
        for (int x = 0; x < it.length; x++) it[x] = Item.items[inventory[x].getId()].setCount(inventory[x].getCount());
        return it;
    }

    @Data
    public static class ItemUtils {
        private int id = 0;
        private int count = 0;
    }

    @Data
    public static class LocationUtils {
        private String world = "";
        private float x = 0;
        private float y = 0;
        private int direction = 0;
    }
}
