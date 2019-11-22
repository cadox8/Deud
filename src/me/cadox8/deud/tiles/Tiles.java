package me.cadox8.deud.tiles;

import me.cadox8.deud.gfx.textures.Assets;

import java.awt.image.BufferedImage;
import java.util.Arrays;

public enum Tiles {

    // Void Tile to represent background and fill non-existing Tiles
    VOID(-1, Assets.voidImg, true),
    GRASS(0, Assets.grass),
    DIRT(1, Assets.dirt),
    BRICK(2, Assets.brick, true),
    DOOR(3, Assets.door, true),
    DOOR2(9, Assets.door2),
    GRASS_DIRT1(4, 0, Assets.grass_dirt1),
    GRASS_DIRT1_2(4, 1, Assets.grass_dirt1),
    GRASS_DIRT1_3(4, 2, Assets.grass_dirt1),
    GRASS_DIRT1_4(4, 3, Assets.grass_dirt1),
    GRASS_DIRT_SQUARE(5, 0, Assets.grass_dirt2),
    GRASS_DIRT_SQUARE_2(5, 1, Assets.grass_dirt2),
    GRASS_DIRT_SQUARE_3(5, 2, Assets.grass_dirt2),
    GRASS_DIRT_SQUARE_4(5, 3, Assets.grass_dirt2),
    SAND(6, Assets.sand),
    BUG(7, Assets.bug, true),

    // House 2
    HOUSE2_R1(19, Assets.house2[0], true),
    HOUSE2_R2(20, Assets.house2[3], true),
    HOUSE2_R3(21, Assets.house2[6], true),
    HOUSE2_R4(22, Assets.house2[1], true),
    HOUSE2_W1(23, Assets.house2[7], true),
    HOUSE2_R5(24, Assets.house2[2], true),
    HOUSE2_R6(25, Assets.house2[5], true),
    HOUSE2_R7(26, Assets.house2[8], true),
    HOUSE2_JOIN(27, Assets.house2[4], true);

    private final int id;
    private int subID;
    private final BufferedImage texture;
    private boolean solid;

    Tiles(int id, BufferedImage texture) {
        this(id, texture, false);
    }
    Tiles(int id, BufferedImage texture, boolean solid) {
        this(id, 0, texture, solid);
    }
    Tiles(int id, int subID, BufferedImage texture) {
        this(id, subID, texture, false);
    }
    Tiles(int id, int subID, BufferedImage texture, boolean solid) {
        this.id = id;
        this.subID = subID;
        this.texture = texture;
        this.solid = solid;
    }

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
            default:
                return 0;
        }
    }
}
