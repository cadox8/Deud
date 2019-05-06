package me.cadox8.deud.events.player;

import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.entities.creatures.player.Player;

public class PlayerSpawnEvent extends PlayerEvent {

    public PlayerSpawnEvent(GameAPI gameAPI, Player player) {
        super(gameAPI, player);
    }

    @Override
    protected void onEvent() {

    }
}
