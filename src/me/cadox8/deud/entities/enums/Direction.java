package me.cadox8.deud.entities.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * This class refers to where an Entity is facing/moving.
 *
 * The old way (int) will be deprecated soon
 */

@RequiredArgsConstructor
public enum Direction {

    SOUTH(0),
    NORTH(1),
    EAST(2),
    WEST(3);

    @Deprecated
    @Getter private final int legacy_direction;

    public static Direction parseDirection(int legacy_direction) {
        switch (legacy_direction) {
            case 1:
                return NORTH;
            case 2:
                return EAST;
            case 3:
                return WEST;
            default:
                return SOUTH;
        }
    }
}
