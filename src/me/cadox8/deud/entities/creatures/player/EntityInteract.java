package me.cadox8.deud.entities.creatures.player;

import lombok.RequiredArgsConstructor;
import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.entities.Entity;
import me.cadox8.deud.entities.creatures.npcs.Npc;
import me.cadox8.deud.entities.statics.chest.Chest;
import me.cadox8.deud.entities.statics.Door;
import me.cadox8.deud.entities.statics.Shop;
import me.cadox8.deud.entities.statics.sign.Sign;
import me.cadox8.deud.states.GameState;
import me.cadox8.deud.ux.dialog.Dialog;

@RequiredArgsConstructor
public class EntityInteract {

    private final GameAPI gameAPI;
    private final Entity entity;
    private final Player player;

    public void interact() {
        if (entity == null) return;

        if (entity instanceof Chest) {
            final Chest chest = (Chest) entity;
            chest.open(player);
        }

        if (entity instanceof Shop) {
            final Shop shop = (Shop) entity;
            shop.open(player);
        }

        if (entity instanceof Door) {
            final Door door = (Door) entity;
            door.changeWorld(player);
        }

        if (entity instanceof Sign) {
            final Sign sign = (Sign) entity;
            final Dialog dialog = new Dialog(gameAPI, player).addText(sign.getText());
            ((GameState) gameAPI.getGame().getGameState()).setDialog(dialog);
        }

        if (entity instanceof Npc) {
            final Npc npc = (Npc) entity;
            if (npc.getText().isEmpty()) return;
            final Dialog dialog = new Dialog(gameAPI, player, npc);
            ((GameState) gameAPI.getGame().getGameState()).setDialog(dialog);
        }
    }
}
