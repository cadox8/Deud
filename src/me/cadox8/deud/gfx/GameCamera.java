package me.cadox8.deud.gfx;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.entities.Entity;
import me.cadox8.deud.tiles.Tile;

@AllArgsConstructor
public class GameCamera {

    private GameAPI gameAPI;
    @Getter private float xOffset, yOffset;

    private void checkBlankSpace() {
        if (xOffset < 0) {
            xOffset = 0;
        } else if (xOffset > gameAPI.getWorld().getWidth() * Tile.TILEWIDTH - gameAPI.getWidth()) {
            xOffset = gameAPI.getWorld().getWidth() * Tile.TILEWIDTH - gameAPI.getWidth();
        }

        if (yOffset < 0) {
            yOffset = 0;
        } else if (yOffset > gameAPI.getWorld().getHeight() * Tile.TILEHEIGHT - gameAPI.getHeight()) {
            yOffset = gameAPI.getWorld().getHeight() * Tile.TILEHEIGHT - gameAPI.getHeight();
        }
    }

    public void centerOnEntity(Entity e) {
        xOffset = e.getX() - gameAPI.getWidth() / 2 + e.getWidth() / 2;
        yOffset = e.getY() - gameAPI.getHeight() / 2 + e.getHeight() / 2;
        checkBlankSpace();
    }

    public void move(float xAmt, float yAmt) {
        xOffset += xAmt;
        yOffset += yAmt;
        checkBlankSpace();
    }
}
