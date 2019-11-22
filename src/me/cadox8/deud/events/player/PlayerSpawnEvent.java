package me.cadox8.deud.events.player;

import lombok.NonNull;
import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.entities.creatures.player.Player;

public class PlayerSpawnEvent extends PlayerEvent {

    public PlayerSpawnEvent(@NonNull GameAPI gameAPI, Player player) {
        super(gameAPI, player);
    }

    @Override
    protected void onEvent() {

    }
}
