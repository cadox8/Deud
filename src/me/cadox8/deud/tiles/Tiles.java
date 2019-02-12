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
    DOOR(3, Assets.door),
    DOOR2(3, 2, Assets.door, false),
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
    VOID(8, 0, Assets.voidImg, true);


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

            default:
                return 0;
        }
    }
}
