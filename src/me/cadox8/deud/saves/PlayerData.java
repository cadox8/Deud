package me.cadox8.deud.saves;

import lombok.Data;
import lombok.Getter;
import me.cadox8.deud.utils.Location;

import java.util.Map;

@Data
public class PlayerData {

    private int Money;
    private int Health;
    private LocationUtils Location;
    @Getter private Map<Integer, Integer>[] Inventory;

    public LocationUtils locUtils() {
        return Location;
    }
    public Location getLocation() {
        return new Location(Location.getX(), Location.getY(),Location.getDirection());
    }

    @Data
    public class LocationUtils {

        private String world;
        private float x;
        private float y;
        private int direction;
    }
}
