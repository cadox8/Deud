package me.cadox8.deud.runnable;

import me.cadox8.deud.entities.statics.chest.TrapChest;
import me.cadox8.deud.utils.Utils;

public class ChestExplosionDelayedTask extends DeudTask {

    private final TrapChest chest;

    public ChestExplosionDelayedTask(TrapChest chest) {
        this.chest = chest;
    }

    @Override
    public void run() {
        Utils.getNearbyEntities(chest.getLocation(), 30.5).forEach(e -> e.hurt(this.chest));
    }
}
