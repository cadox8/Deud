package me.cadox8.deud.saves;

import lombok.Data;
import lombok.Getter;
import me.cadox8.deud.entities.Location;

import java.util.Map;

@Data
public class PlayerData {

    private String nick;
    private int money = 0;
    private int health = 0;
    private LocationUtils location = new LocationUtils();
    @Getter private Map<Integer, Integer>[] inventory = null;

    public LocationUtils locUtils() {
        return location;
    }
    public Location getLocation() {
        return new Location(location.getX(), location.getY(), location.getDirection());
    }

    @Data
    public class LocationUtils {

        private String world = "";
        private float x = 0;
        private float y = 0;
        private int direction = 0;
    }
}
