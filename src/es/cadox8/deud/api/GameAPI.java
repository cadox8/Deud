package es.cadox8.deud.api;

import es.cadox8.deud.audio.Sound;
import es.cadox8.deud.game.Game;
import es.cadox8.deud.input.KeyManager;
import es.cadox8.deud.input.MouseManager;
import lombok.Getter;
import lombok.Setter;
import es.cadox8.deud.config.Config;
import es.cadox8.deud.entities.creatures.player.Player;
import es.cadox8.deud.graphics.GameCamera;
import es.cadox8.deud.graphics.fonts.Fonts;
import es.cadox8.deud.managers.DamageManager;
import es.cadox8.deud.managers.EntityManager;
import es.cadox8.deud.utils.Log;
import es.cadox8.deud.worlds.World;

public class GameAPI {

    @Getter private static GameAPI instance;

    @Getter private final Game game;
    @Getter @Setter private World world;

    @Getter @Setter private boolean debug = true; // Development Only!

    @Getter @Setter private static Fonts gameFont = Fonts.DEUD;

    public GameAPI(Game game) {
        instance = this;
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
