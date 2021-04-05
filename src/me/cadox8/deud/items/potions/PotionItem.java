package me.cadox8.deud.items.potions;

import me.cadox8.deud.items.Item;

import java.awt.image.BufferedImage;

public abstract class PotionItem extends Item {

    protected final int level;
    private final PotionType potionType;

    public PotionItem(BufferedImage texture, int id, String name, int level, PotionType potionType) {
        super(texture, id, name, ItemType.POTION);

        this.level = Math.min(level, 3);
        this.potionType = potionType;
    }

    public enum PotionType {
        HEALTH, REGENERATION, POWER
    }
}
