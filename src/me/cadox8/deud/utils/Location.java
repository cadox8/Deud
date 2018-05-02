package me.cadox8.deud.utils;

import lombok.Getter;
import lombok.Setter;
import me.cadox8.deud.api.API;
import me.cadox8.deud.worlds.World;

import java.util.HashMap;
import java.util.Map;

@Deprecated
public class Location {

    private API API;

    @Getter @Setter private World world;
    @Getter @Setter private float x;
    @Getter @Setter private float y;
    @Getter @Setter private int direction;

    public Location(World world, float x, float y) {
        this(world, x, y, 0);
    }

    public Location(World world, float x, float y, int direction) {
        this.world = world;
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public void add(float x, float y) {
        this.x += x;
        this.y += y;
    }

    public void teleport(World world, float x, float y, int direction) {
        setWorld(world);
        setX(x);
        setY(y);
        setDirection(direction);
    }

    public void teleport(float x, float y, int direction) {
        teleport(API.getWorld(), x, y, direction);
    }

    public int getXDistance(Location location) {
        return (int) (getX() - location.getX());
    }

    public int getYDistance(Location location) {
        return (int) (getY() - location.getY());
    }

    public Map<String, Object> serializeLocation() {
        Map<String, Object> location = new HashMap<>();

        location.put("world", getWorld().worldName());
        location.put("x", getX());
        location.put("y", getY());
        location.put("direction", getDirection());

        return location;
    }

    public String toString() {
        return "Location:{World:" + world.worldName() + ",X:" + getX() + ",Y:" + getY() + ",Direction:" + getDirection() + "}";
    }
}
