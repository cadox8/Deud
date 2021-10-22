package es.cadox8.deud.utils;

import lombok.Getter;

public class Circle2D {

    @Getter private double x, y, radius;

    public Circle2D(double x, double y, double radius) {
        this.x = x;
        this.y = y;
        this.radius = radius * 10;
    }

    public double getArea() {
        return Math.PI * radius * radius;
    }

    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }


    public boolean contains(double x2, double y2) {
        return Math.sqrt((x - x2) * (x - x2) + (y - y2) * (y - y2)) <= radius;
    }

    public boolean contains(Circle2D c) {
        double x2 = c.getX(), y2 = c.getY();
        double distance = Math.sqrt((x - x2) * (x - x2) + (y - y2) * (y - y2));
        return distance <= radius - c.getRadius();
    }

    public boolean overlaps(Circle2D c) {
        double x2 = c.getX(), y2 = c.getY();
        double distance = Math.sqrt((x - x2) * (x - x2) + (y - y2) * (y - y2));
        return distance <= radius + c.getRadius();
    }
}