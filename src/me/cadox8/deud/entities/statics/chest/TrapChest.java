package me.cadox8.deud.entities.statics.chest;

import lombok.NonNull;
import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.entities.creatures.player.Player;
import me.cadox8.deud.runnable.ChestExplosionDelayedTask;

import java.util.Random;

public class TrapChest extends RewardChest {

    public TrapChest(@NonNull GameAPI gameAPI, float x, float y) {
        this(gameAPI, x, y, false);
    }
    public TrapChest(@NonNull GameAPI gameAPI, float x, float y, boolean needKey) {
        super(gameAPI, x, y, needKey, ChestType.TRAP);

        setDamage(DEFAULT_DAMAGE);
        setLevel(1);
    }

    @Override
    public void open(Player player) {
        if (this.canOpen(player)) return;

        // ToDo: Remove 1 key

        new ChestExplosionDelayedTask(this).scheduleDelayed(2 + new Random().nextInt(5));
        // ToDo: Play Sound

        player.getInventory().addItem(this.pool.get(new Random().nextInt(this.pool.size())));
        setOpen(true);
    }
}
