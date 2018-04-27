package me.cadox8.deud.gfx;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.cadox8.deud.api.API;
import me.cadox8.deud.entities.Entity;
import me.cadox8.deud.tiles.Tile;

@AllArgsConstructor
public class GameCamera {

    private API API;
    @Getter private float xOffset, yOffset;

    private void checkBlankSpace() {
        if (xOffset < 0) {
            xOffset = 0;
        } else if (xOffset > API.getWorld().getWidth() * Tile.TILEWIDTH - API.getWidth()) {
            xOffset = API.getWorld().getWidth() * Tile.TILEWIDTH - API.getWidth();
        }

        if (yOffset < 0) {
            yOffset = 0;
        } else if (yOffset > API.getWorld().getHeight() * Tile.TILEHEIGHT - API.getHeight()) {
            yOffset = API.getWorld().getHeight() * Tile.TILEHEIGHT - API.getHeight();
        }
    }

    public void centerOnEntity(Entity e) {
        xOffset = e.getX() - API.getWidth() / 2 + e.getWidth() / 2;
        yOffset = e.getY() - API.getHeight() / 2 + e.getHeight() / 2;
        checkBlankSpace();
    }

    public void move(float xAmt, float yAmt) {
        xOffset += xAmt;
        yOffset += yAmt;
        checkBlankSpace();
    }
}
