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
        if (p.getStamina() == p.getMaxStamina()) return;

        if (!this.isInfinity()) removeItem(p);

        if (p.getStamina() + foodRegen >= p.getMaxStamina()) {
            p.setStamina(p.getMaxStamina());
            return;
        }
        p.setStamina(p.getStamina() + foodRegen);
    }
}
