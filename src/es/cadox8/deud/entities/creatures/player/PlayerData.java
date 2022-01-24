package es.cadox8.deud.entities.creatures.player;

import es.cadox8.deud.entities.components.inventory.creature.CreatureInventory;
import es.cadox8.deud.entities.enums.Direction;
import es.cadox8.deud.items.Item;
import lombok.Data;
import es.cadox8.deud.entities.Location;
import es.cadox8.deud.entities.components.inventory.Inventory;

import java.util.HashMap;
import java.util.Map;

@Data
public class PlayerData {

    private double money = 0;

    private int maxHealth = 0;
    private int health = 0;

    private int maxStamina = 0;
    private double stamina = 0.0;

    private int level = 0;
    private double experience = 0;

    private LocationUtils location = new LocationUtils();
    private ItemUtils[] inventory = new ItemUtils[0];
    private ItemUtils2[] equip = new ItemUtils2[0];

    public LocationUtils locUtils() {
        return location;
    }
    public Location getLocation() {
        return new Location(location.getX(), location.getY(), Direction.valueOf(location.direction));
    }

    public Map<CreatureInventory.Equipment, Item> getEquipment() {
        if (equip == null) return new HashMap<>();
        final HashMap<CreatureInventory.Equipment, Item> equipment = new HashMap<>();
        for (ItemUtils2 itemUtils : equip) equipment.put(CreatureInventory.Equipment.valueOf(itemUtils.getSlot()), Item.get(itemUtils.getId()).setCount(itemUtils.getCount()));
        return equipment;
    }

    public Item[] getInventory() {
        if (inventory == null) return new Item[0];
        final Item[] it = new Item[inventory.length];
        for (int x = 0; x < it.length; x++) it[x] = Item.get(inventory[x].getId()).setCount(inventory[x].getCount());
        return it;
    }

    @Data
    public static class ItemUtils {
        private int id = 0;
        private int count = 0;
    }
    @Data
    public static class ItemUtils2 {
        private String slot = "";
        private int id = 0;
        private int count = 0;
    }

    @Data
    public static class LocationUtils {
        private String world = "";
        private float x = 0;
        private float y = 0;
        private String direction = Direction.SOUTH.name();
    }
}
