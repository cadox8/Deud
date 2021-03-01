package me.cadox8.deud.entities.statics;

import lombok.NonNull;
import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.entities.EntityData;
import me.cadox8.deud.graphics.textures.Assets;
import me.cadox8.deud.items.Items;
import me.cadox8.deud.tiles.Tile;

import java.awt.*;

public class Rock extends StaticEntity {

    public Rock(@NonNull GameAPI gameAPI, float x, float y) {
        super("91d334fe-5322-42d7-8a0d-3cebb6a173d4", "Rock", EntityData.EntityType.ROCK, gameAPI, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);

        bounds.x = 2;
        bounds.y = (int) (height / 2f);
        bounds.width = width - 6;
        bounds.height = (int) (height - height / 2f);
    }

    @Override
    public void die() {
        dropItem(Items.STONE.item());
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.stone, (int) (x - gameAPI.getGameCamera().getXOffset()), (int) (y - gameAPI.getGameCamera().getYOffset()), width, height, null);
    }
}
