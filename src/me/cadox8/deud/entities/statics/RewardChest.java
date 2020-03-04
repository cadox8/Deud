package me.cadox8.deud.entities.statics;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.attributes.Explosion;
import me.cadox8.deud.entities.EntityData;
import me.cadox8.deud.entities.creatures.player.Player;
import me.cadox8.deud.items.Item;

public class RewardChest extends Chest {

    @Getter @Setter private boolean open = false;

    public RewardChest(@NonNull GameAPI gameAPI, float x, float y) {
        super(502, "RewardChest", EntityData.EntityType.REWARDCHEST, gameAPI, x, y);
    }

    public void open(Player p) {
        if (p.getPlayerInventory().itemCount(Item.keyItem) == 0 || open) return;
        open = true;
        p.getPlayerInventory().removeItem(Item.keyItem.setCount(1));

        p.getPlayerInventory().addItem(Item.getRandom(Item.hand, Item.keyItem));

        if (isExplosive()) new Explosion(this.getGameAPI(),5, 0.3).perform(this, null);
    }
}
