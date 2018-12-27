package me.cadox8.deud.entities.statics;

import lombok.Getter;
import me.cadox8.deud.api.API;
import me.cadox8.deud.gfx.textures.Assets;
import me.cadox8.deud.saves.FileUtils;
import me.cadox8.deud.states.GameState;
import me.cadox8.deud.states.State;
import me.cadox8.deud.tiles.Tile;

import java.awt.*;

public class Door extends StaticEntity {

    @Getter private final String map;

    public Door(API API, float x, float y, String map) {
        super(9, "Door", API, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);

        this.map = map;

        setDamageable(false);

        bounds.x = 2;
        bounds.y = (int) (height / 2f);
        bounds.width = width - 6;
        bounds.height = (int) (height - height / 2f);
    }

    public void changeWorld() {
        FileUtils.save(API.getWorld().getEntityManager().getPlayer());
        API.getWorld().getEntityManager().getEntities().forEach(API.getWorld().getEntityManager()::removeEntity);

        final GameState gameState = new GameState(API, getMap(), true);

        API.getGame().setGameState(gameState);
        State.setState(gameState);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.bug, (int) (x - API.getGameCamera().getXOffset()), (int) (y - API.getGameCamera().getYOffset()), width, height, null);
    }
}
