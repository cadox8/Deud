package me.cadox8.deud.items.potions;

import me.cadox8.deud.items.Item;

import java.awt.image.BufferedImage;

public abstract class PotionItem extends Item {

    protected final int level;
    private final PotionType type;

    public PotionItem(BufferedImage texture, int id, String name, int level, PotionType type) {
        super(texture, id, name);

        this.level = level > 3 ? 3 : level;
        this.type = type;
    }

    public enum PotionType {
        HEALTH, REGENERATION, POWER
    }
}
