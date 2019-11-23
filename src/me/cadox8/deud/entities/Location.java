package me.cadox8.deud.entities;

import lombok.Getter;
import lombok.Setter;
import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.worlds.World;

import java.util.HashMap;
import java.util.Map;

public class Location {

    @Setter @Getter private static GameAPI gameAPI;

    @Getter @Setter private World world;
    @Getter @Setter private float x;
    @Getter @Setter private float y;
    @Getter @Setter private int direction;

    public Location(Entity en) {
        this.world = en.getGameAPI().getWorld();
        this.x = en.getX();
        this.y = en.getY();
        this.direction = en.getDirection();
    }

    public Location(float x, float y, int direction) {
        this(gameAPI.getWorld(), x, y, direction);
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

    public void teleport(float x, float y, int direction) {
        teleport(gameAPI.getWorld(), x, y, direction);
    }
    public void teleport(World world, float x, float y, int direction) {
        setWorld(world);
        setX(x);
        setY(y);
        setDirection(direction);
    }

    public int getXDistance(Location location) {
        return (int) (getX() - location.getX());
    }
    public int getYDistance(Location location) {
        return (int) (getY() - location.getY());
    }

    public boolean equals(double x, double y) {
        return getX() == x && getY() == y;
    }

    public boolean equals(Location checkLoc) {
        return checkLoc.getWorld().worldName().equalsIgnoreCase(getWorld().worldName()) && checkLoc.getX() == getX() && checkLoc.getY() == getY();
    }

    @Override
    public String toString() {
        return "Location{World: " + world.worldName() + ", X: " + getX() + ", Y: " + getY() + ", Direction: " + getDirection() + "}";
    }

    // Save Utils
    public Map<String, Object> serializeLocation() {
        Map<String, Object> location = new HashMap<>();

        location.put("world", getWorld().worldName());
        location.put("x", getX());
        location.put("y", getY());
        location.put("direction", getDirection());

        return location;
    }

    public Location deSerializeLocation(Map<String, Object> location) {
        final World world = new World(gameAPI, (String)location.get("world"));
        final float x = (float)location.get("x");
        final float y = (float)location.get("y");
        final int direction = (int)location.get("direction");

        return new Location(world, x, y, direction);
    }
}
