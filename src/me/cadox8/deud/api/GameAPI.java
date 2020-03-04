package me.cadox8.deud.api;

import lombok.Getter;
import lombok.Setter;
import me.cadox8.deud.config.Config;
import me.cadox8.deud.entities.creatures.player.Player;
import me.cadox8.deud.game.Game;
import me.cadox8.deud.gfx.GameCamera;
import me.cadox8.deud.input.KeyManager;
import me.cadox8.deud.input.MouseManager;
import me.cadox8.deud.managers.DamageManager;
import me.cadox8.deud.managers.EntityManager;
import me.cadox8.deud.saves.FileUtils;
import me.cadox8.deud.worlds.World;

import java.awt.*;

public class GameAPI {

    @Getter private final Game game;
    @Getter @Setter private World world;

    @Getter private final Config config;

    @Getter @Setter private boolean debug = false;

    @Getter @Setter private static Font gameFont = new Font("'Arial'", Font.PLAIN, 12);

    @Getter private static GameAPI instance; // This is f*king sh*t, but for now it will work...

    public GameAPI(Game game) {
        this.game = game;
        this.config = FileUtils.loadConfig();

        instance = this;
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
