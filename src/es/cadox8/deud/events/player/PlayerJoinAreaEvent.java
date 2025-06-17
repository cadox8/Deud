package es.cadox8.deud.events.player;

import es.cadox8.deud.api.GameAPI;
import es.cadox8.deud.entities.creatures.player.Player;
import lombok.NonNull;

public class PlayerJoinAreaEvent extends PlayerEvent {

    public PlayerJoinAreaEvent(@NonNull GameAPI gameAPI, Player player) {
        super(gameAPI, player);
    }

    @Override
    protected void onEvent() {

    }
}
