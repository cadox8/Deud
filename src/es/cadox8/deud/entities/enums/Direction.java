package es.cadox8.deud.entities.enums;

import java.util.Random;

public enum Direction {

    STATIC, SOUTH, NORTH, EAST, WEST;

    public static Direction randomDirection() {
        return Direction.values()[new Random().nextInt(Direction.values().length)];
    }
}
