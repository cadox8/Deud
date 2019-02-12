package me.cadox8.deud.entities.statics;

import lombok.Getter;
import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.gfx.textures.Assets;
import me.cadox8.deud.saves.FileUtils;
import me.cadox8.deud.states.GameState;
import me.cadox8.deud.states.State;
import me.cadox8.deud.tiles.Tile;

import java.awt.*;

public class Door extends StaticEntity {

    @Getter private final String map;

    public Door(GameAPI GameAPI, float x, float y, String map) {
        super(9, "Door", GameAPI, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);

        this.map = map;

        setDamageable(false);

        bounds.x = 2;
        bounds.y = (int) (height / 2f);
        bounds.width = width - 6;
        bounds.height = (int) (height - height / 2f);
    }

    public void changeWorld() {
        FileUtils.save(GameAPI.getWorld().getEntityManager().getPlayer());
        GameAPI.getWorld().getEntityManager().getEntities().forEach(GameAPI.getWorld().getEntityManager()::removeEntity);

        final GameState gameState = new GameState(GameAPI, getMap(), true);

        GameAPI.getGame().setGameState(gameState);
        State.setState(gameState);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.bug, (int) (x - GameAPI.getGameCamera().getXOffset()), (int) (y - GameAPI.getGameCamera().getYOffset()), width, height, null);
    }
}
