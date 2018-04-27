package me.cadox8.deud.items.food;

import lombok.Getter;
import me.cadox8.deud.items.Item;

import java.awt.image.BufferedImage;

public abstract class FoodItem extends Item {

    @Getter protected double foodRegen;

    public FoodItem(BufferedImage texture, int id, String name, double foodRegen) {
        super(texture, id, name);

        this.foodRegen = foodRegen;
    }

    @Override
    public void use() {
        consume();
    }

    protected void consume() {

    }
}
