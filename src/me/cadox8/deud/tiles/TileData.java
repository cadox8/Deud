package me.cadox8.deud.tiles;

import jdk.jfr.Experimental;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.awt.*;
import java.awt.image.BufferedImage;

@RequiredArgsConstructor
@Experimental
public class TileData {

    @Getter private final Tile tile;
    @Getter private final Color color;

    @Deprecated
    @Getter @Setter private BufferedImage bi;
    @Deprecated
    @Getter @Setter private int x;
    @Deprecated
    @Getter @Setter private int y;
}
