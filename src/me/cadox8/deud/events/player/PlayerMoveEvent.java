package me.cadox8.deud.events.player;

import lombok.Getter;
import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.entities.Location;
import me.cadox8.deud.entities.creatures.player.Player;

public class PlayerMoveEvent extends PlayerEvent {

    @Getter private Location location;

    public PlayerMoveEvent(GameAPI gameAPI, Player player) {
        super(gameAPI, player);
    }

    @Override
    protected void onEvent() {
        location = player.getLocation();
    }
}
