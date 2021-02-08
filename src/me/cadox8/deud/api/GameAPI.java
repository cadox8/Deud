package me.cadox8.deud.api;

import lombok.Getter;
import lombok.Setter;
import me.cadox8.deud.audio.Sound;
import me.cadox8.deud.config.Config;
import me.cadox8.deud.entities.creatures.player.Player;
import me.cadox8.deud.game.Game;
import me.cadox8.deud.graphics.GameCamera;
import me.cadox8.deud.input.KeyManager;
import me.cadox8.deud.input.MouseManager;
import me.cadox8.deud.managers.DamageManager;
import me.cadox8.deud.managers.EntityManager;
import me.cadox8.deud.utils.Log;
import me.cadox8.deud.worlds.World;

import java.awt.*;

public class GameAPI {

    @Getter private final Game game;
    @Getter @Setter private World world;

    @Getter @Setter private boolean debug = true; // Development Only!

    @Getter @Setter private static Font gameFont = new Font("'Arial'", Font.PLAIN, 12);

    public GameAPI(Game game) {
        this.game = game;

        setDebug(getConfig().isDebug());

        Log.setGameAPI(this);
        Sound.setGameAPI(this);

        Log.log("Debug Enabled");
    }

    public Config getConfig() {
        return game.getConfig();
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

    public DamageManager getDamageManager() {
        return game.getDamageManager();
    }

    public EntityManager getEntityManager() {
        return world.getEntityManager();
    }
    public Player getPlayer() {
        return getEntityManager().getPlayer();
    }

    public int getWidth() {
        return game.getWidth();
    }
    public int getHeight() {
        return game.getHeight();
    }
}
