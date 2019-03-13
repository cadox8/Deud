package me.cadox8.deud.tiles;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import me.cadox8.deud.gfx.textures.Assets;

import java.awt.image.BufferedImage;
import java.util.Arrays;

@RequiredArgsConstructor
@AllArgsConstructor
public enum Tiles {
    GRASS(0, Assets.grass),
    DIRT(1, Assets.dirt),
    BRICK(2, 0, Assets.brick, true),
    DOOR(3, 0, Assets.door, true),
    DOOR2(9, Assets.door2),
    GRASS_DIRT1(4, 0, Assets.grass_dirt1, false),
    GRASS_DIRT1_2(4, 1, Assets.grass_dirt1, false),
    GRASS_DIRT1_3(4, 2, Assets.grass_dirt1, false),
    GRASS_DIRT1_4(4, 3, Assets.grass_dirt1, false),
    GRASS_DIRT_SQUARE(5, 0, Assets.grass_dirt2, false),
    GRASS_DIRT_SQUARE_2(5, 1, Assets.grass_dirt2, false),
    GRASS_DIRT_SQUARE_3(5, 2, Assets.grass_dirt2, false),
    GRASS_DIRT_SQUARE_4(5, 3, Assets.grass_dirt2, false),
    SAND(6, Assets.sand),
    BUG(7, 0, Assets.bug, true),
    VOID(8, 0, Assets.voidImg, true),

    // House 1
    HOUSE1_R1(10, 0, Assets.house1[0], true),
    HOUSE1_R2(11, 0, Assets.house1[3], true),
    HOUSE1_R3(12, 0, Assets.house1[6], true),
    HOUSE1_R4(13, 0, Assets.house1[1], true),
    HOUSE1_W1(14, 0, Assets.house1[7], true),
    HOUSE1_R5(15, 0, Assets.house1[2], true),
    HOUSE1_R6(16, 0, Assets.house1[5], true),
    HOUSE1_R7(17, 0, Assets.house1[8], true),
    HOUSE1_JOIN(18, 0, Assets.house1[4], true),

    // House 2
    HOUSE2_R1(19, 0, Assets.house2[0], true),
    HOUSE2_R2(20, 0, Assets.house2[3], true),
    HOUSE2_R3(21, 0, Assets.house2[6], true),
    HOUSE2_R4(22, 0, Assets.house2[1], true),
    HOUSE2_W1(23, 0, Assets.house2[7], true),
    HOUSE2_R5(24, 0, Assets.house2[2], true),
    HOUSE2_R6(25, 0, Assets.house2[5], true),
    HOUSE2_R7(26, 0, Assets.house2[8], true),
    HOUSE2_JOIN(27, 0, Assets.house2[4], true);

    private final int id;
    private int subID = 0;
    private final BufferedImage texture;
    private boolean solid = false;

    public static Tile getTile(int id, int subID) {
        return Arrays.stream(values()).filter(t -> t.id == id && t.subID == subID).findFirst().orElse(VOID).build();
    }

    public Tile build() {
        final Tile tile = new Tile(id, texture, subID, solid);
        if (subID != 0) return tile.createNewRotated(parseDegrees());
        return tile;
    }

    private int parseDegrees() {
        switch (subID) {
            case 1:
                return 90;
            case 2:
                return 180;
            case 3:
                return 270;

            case 4:
                return 0;
            default:
                return 0;
        }
    }
}
