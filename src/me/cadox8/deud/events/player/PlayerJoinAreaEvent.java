package me.cadox8.deud.events.player;

import lombok.NonNull;
import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.entities.creatures.player.Player;

public class PlayerJoinAreaEvent extends PlayerEvent {

    public PlayerJoinAreaEvent(@NonNull GameAPI gameAPI, Player player) {
        super(gameAPI, player);
    }

    @Override
    protected void onEvent() {

    }
}
