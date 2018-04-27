package me.cadox8.deud.attributes;

public class Explosion extends Attribute {

    private int radius;
    private int power;

    public Explosion(int radius, int power) {
        super(1, "Explosion");

        this.radius = radius;
        this.power = power;
    }

    @Override
    public void perform() {

    }
}
