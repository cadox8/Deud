package me.cadox8.deud.saves;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import me.cadox8.deud.entities.Location;
import me.cadox8.deud.items.Item;

import java.util.Map;

@Data
public class PlayerData {

    private String nick;
    private int money = 0;
    private int health = 0;
    private LocationUtils location = new LocationUtils();
    @Getter private Map<Integer, Integer>[] inventory = null;
    private ItemUtils item = new ItemUtils(Item.hand.getId(), 1);

    public LocationUtils locUtils() {
        return location;
    }
    public Location getLocation() {
        return new Location(location.getX(), location.getY(), location.getDirection());
    }

    public Item getItem() {
        return Item.items[item.getId()].setCount(item.getCount());
    }

    @Data
    @AllArgsConstructor
    public static class ItemUtils {
        private int id;
        private int count;
    }

    @Data
    public static class LocationUtils {
        private String world = "";
        private float x = 0;
        private float y = 0;
        private int direction = 0;
    }
}
