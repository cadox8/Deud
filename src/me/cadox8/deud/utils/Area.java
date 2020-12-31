package me.cadox8.deud.utils;

import lombok.Getter;
import lombok.NonNull;
import me.cadox8.deud.entities.Location;
import me.cadox8.deud.entities.creatures.player.Player;

import java.awt.*;

public class Area {

    @Getter private final float xStart, yStart, xEnd, yEnd;

    @Getter private final Rectangle area;

    public Area(float xStart, float yStart, float xEnd, float yEnd) {
        this.xStart = xStart;
        this.yStart = yStart;
        this.xEnd = xEnd;
        this.yEnd = yEnd;

        this.area = new Rectangle((int)xStart, (int)yStart, (int)(xEnd - xStart), (int)(yEnd - yStart));
    }

    public boolean isInArea(@NonNull final Player player) {
        final Location location = player.getLocation();
        return this.area.contains(location.getX(), location.getY());
    }
}
