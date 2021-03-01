package me.cadox8.deud.entities.statics.chest;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.entities.creatures.player.Player;
import me.cadox8.deud.items.Item;
import me.cadox8.deud.items.Items;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class RewardChest extends Chest {

    @Getter @Setter protected boolean open = false;

    @Getter protected final boolean needKey;

    @Getter @Setter protected List<Item> pool;

    public RewardChest(@NonNull GameAPI gameAPI, float x, float y) {
        this(gameAPI, x, y, false, ChestType.REWARD);
    }
    public RewardChest(@NonNull GameAPI gameAPI, float x, float y, boolean needKey, ChestType type) {
        super(gameAPI, x, y, type);

        this.needKey = needKey;
        this.pool = new ArrayList<>();
    }

    @Override
    public void open(Player player) {
        if (this.canOpen(player)) return;

        // ToDo: Remove 1 key

        player.getInventory().addItem(this.pool.get(new Random().nextInt(this.pool.size())));
        setOpen(true);
    }

    protected boolean canOpen(Player player) {
        if (this.isOpen()) return true;
        return this.needKey && !player.getInventory().hasItem(Objects.requireNonNull(Items.KEY.item()));
    }

    public void addToPool(int id) {
        this.pool.add(Items.getItem(id));
    }
}
