package me.cadox8.deud.items.potions;

import me.cadox8.deud.items.Item;
import me.cadox8.deud.items.ItemType;

import java.awt.image.BufferedImage;

public abstract class PotionItem extends Item {

    protected final int level;
    private final PotionType potionType;

    public PotionItem(BufferedImage texture, int id, String name, int level, PotionType potionType) {
        super(texture, id, name);

        this.level = Math.min(level, 3);
        this.potionType = potionType;
        this.type = ItemType.POTION;
    }

    public enum PotionType {
        HEALTH, REGENERATION, POWER
    }
}
