package me.cadox8.deud.items.food;

import me.cadox8.deud.items.Item;

import java.awt.image.BufferedImage;

public class ChickenItem extends FoodItem {

    public ChickenItem(BufferedImage texture, int id, String name, double foodRengen) {
        super(texture, id, name, foodRengen);
    }

    @Override
    public Item createNew(int x, int y, int count) {
        Item i = new ChickenItem(texture, id, name, getFoodRegen());
        i.setPosition(x, y);
        i.setCount(count);
        return i;
    }
}
