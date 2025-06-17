package es.cadox8.deud.events.player;

import es.cadox8.deud.api.GameAPI;
import es.cadox8.deud.entities.creatures.player.Player;
import lombok.Getter;
import lombok.NonNull;
import es.cadox8.deud.events.Event;

public abstract class PlayerEvent extends Event {

    @Getter protected Player player;

    public PlayerEvent(@NonNull GameAPI gameAPI, Player player) {
        super(gameAPI);
        this.player = player;
    }
}
