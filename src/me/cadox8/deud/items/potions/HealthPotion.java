package me.cadox8.deud.items.potions;

import me.cadox8.deud.entities.creatures.player.Player;
import me.cadox8.deud.graphics.textures.Assets;
import me.cadox8.deud.items.Item;

public class HealthPotion extends PotionItem {

    private final int BASE_HEALTH = 3;

    public HealthPotion(int level) {
        super(Assets.xp, 5, "Health Potion", level, PotionType.HEALTH);
    }

    @Override
    public void use(Player p) {
        if (isInfinity()) return;
        if (p.getHealth() == p.getMaxHealth()) return;
        final int health = BASE_HEALTH + (BASE_HEALTH * (level - 1));

        removeItem(p);
        if (p.getHealth() + health >= p.getMaxHealth()) {
            p.setHealth(p.getMaxHealth());
            return;
        }
        p.setHealth(p.getHealth() + health);
    }

    @Override
    public Item createNew(int x, int y, int count) {
        final Item i = new HealthPotion(level);
        i.setPosition(x, y);
        i.setCount(count);
        return i;
    }
}
