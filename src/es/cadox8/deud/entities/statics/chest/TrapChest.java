package es.cadox8.deud.entities.statics.chest;

import es.cadox8.deud.api.GameAPI;
import es.cadox8.deud.items.Items;
import es.cadox8.deud.runnable.ChestExplosionDelayedTask;
import lombok.NonNull;
import es.cadox8.deud.entities.creatures.player.Player;

import java.util.Random;

public class TrapChest extends RewardChest {

    public TrapChest(@NonNull GameAPI gameAPI, float x, float y) {
        this(gameAPI, x, y, false);
    }
    public TrapChest(@NonNull GameAPI gameAPI, float x, float y, boolean needKey) {
        super(gameAPI, x, y, needKey, ChestType.TRAP);

        setDamage(DEFAULT_DAMAGE);
    }

    @Override
    public void open(Player player) {
        if (!this.canOpen(player)) return;

        player.getPlayerInventory().removeItem(Items.getItem(Items.KEY.getId()), 1);

        new ChestExplosionDelayedTask(this).scheduleDelayed(2 + new Random().nextInt(5));

        //Sounds.EXPLOSION_1.play();

        player.getInventory().addItem(this.pool.get(new Random().nextInt(this.pool.size())));
        setOpen(true);
    }
}
