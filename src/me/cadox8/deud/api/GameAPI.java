package me.cadox8.deud.api;

import lombok.Getter;
import lombok.Setter;
import me.cadox8.deud.entities.EntityManager;
import me.cadox8.deud.game.Game;
import me.cadox8.deud.gfx.GameCamera;
import me.cadox8.deud.input.KeyManager;
import me.cadox8.deud.input.MouseManager;
import me.cadox8.deud.worlds.World;

public class GameAPI {

    @Getter private final Game game;
    @Getter @Setter private World world;

    @Getter @Setter private boolean debug = false;

    public GameAPI(Game game) {
        this.game = game;
    }

    public GameCamera getGameCamera() {
        return game.getGameCamera();
    }

    public KeyManager getKeyManager() {
        return game.getKeyManager();
    }
    public MouseManager getMouseManager() {
        return game.getMouseManager();
    }

    public EntityManager getEntityManager() {
        return world.getEntityManager();
    }

    public int getWidth() {
        return game.getWidth();
    }
    public int getHeight() {
        return game.getHeight();
    }
}
