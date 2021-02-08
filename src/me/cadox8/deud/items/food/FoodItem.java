package me.cadox8.deud.items.food;

import lombok.Getter;
import me.cadox8.deud.entities.creatures.player.Player;
import me.cadox8.deud.items.Item;
import me.cadox8.deud.items.ItemType;

import java.awt.image.BufferedImage;

public abstract class FoodItem extends Item {

    @Getter protected double foodRegen;

    public FoodItem(BufferedImage texture, int id, String name, double foodRegen) {
        super(texture, id, name);

        this.foodRegen = foodRegen;

        this.type = ItemType.FOOD;
    }

    @Override
    public void use(Player p) {
        if (isInfinity()) return;
        consume(p);
    }

    private void consume(Player p) {
        if (p.getHunger() == p.getMaxHunger()) return;

        removeItem(p);

        if (p.getHunger() + foodRegen >= p.getMaxHunger()) {
            p.setHunger(p.getMaxHunger());
            return;
        }
        p.setHunger(p.getHunger() + foodRegen);
    }
}
