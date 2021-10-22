package es.cadox8.deud.events.player;

import es.cadox8.deud.api.GameAPI;
import es.cadox8.deud.entities.creatures.player.Player;
import lombok.NonNull;

public class PlayerSpawnEvent extends PlayerEvent {

    public PlayerSpawnEvent(@NonNull GameAPI gameAPI, Player player) {
        super(gameAPI, player);
    }

    @Override
    protected void onEvent() {

    }
}
