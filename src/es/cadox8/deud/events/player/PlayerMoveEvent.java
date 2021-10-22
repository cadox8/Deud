package es.cadox8.deud.events.player;

import es.cadox8.deud.api.GameAPI;
import es.cadox8.deud.entities.Location;
import es.cadox8.deud.entities.creatures.player.Player;
import lombok.Getter;

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
