package me.cadox8.deud.ui.helpers;

import lombok.Getter;

import java.awt.*;
import java.util.Arrays;

public class AarinArea {

    @Getter private final Polygon polygon;

    public AarinArea() {
        this.polygon = new Polygon();
    }

    public AarinArea addPoints(Point... points) {
        Arrays.asList(points).forEach(p -> this.polygon.addPoint(p.x, p.y));
        return this;
    }

    public boolean isInside(Point point) {
        return this.polygon.contains(point);
    }

    public Point getFirstPoint() {
        return this.polygon.getBounds().getLocation();
    }

    public void addMargins(double xOffset, double yOffset) {
        this.polygon.xpoints[0] += xOffset;
        this.polygon.ypoints[0] += yOffset;
    }
}
