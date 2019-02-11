package me.cadox8.deud.saves;

import lombok.Data;
import lombok.Getter;
import me.cadox8.deud.entities.Location;

import java.util.Map;

@Data
public class PlayerData {

    private String nick;
    private int Money = 0;
    private int Health = 0;
    private LocationUtils Location = new LocationUtils();
    @Getter private Map<Integer, Integer>[] Inventory = null;

    public LocationUtils locUtils() {
        return Location;
    }
    public Location getLocation() {
        return new Location(Location.getX(), Location.getY(),Location.getDirection());
    }

    @Data
    public class LocationUtils {

        private String world = "";
        private float x = 0;
        private float y = 0;
        private int direction = 0;
    }
}
