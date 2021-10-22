package es.cadox8.deud.items.food;

import lombok.Getter;
import es.cadox8.deud.entities.creatures.player.Player;
import es.cadox8.deud.items.Item;

import java.awt.image.BufferedImage;

public abstract class FoodItem extends Item {

    @Getter protected double foodRegen;

    public FoodItem(BufferedImage texture, int id, String name, double foodRegen) {
        super(texture, id, name, ItemType.FOOD);
        this.foodRegen = foodRegen;
    }

    @Override
    public void use(Player p) {
        consume(p);
    }

    private void consume(Player p) {
        if (p.getHunger() == p.getMaxHunger()) return;

        if (!this.isInfinity()) removeItem(p);

        if (p.getHunger() + foodRegen >= p.getMaxHunger()) {
            p.setHunger(p.getMaxHunger());
            return;
        }
        p.setHunger(p.getHunger() + foodRegen);
    }
}
