package me.cadox8.deud.entities.statics;

import lombok.Getter;
import lombok.NonNull;
import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.entities.EntityData;
import me.cadox8.deud.gfx.textures.Assets;
import me.cadox8.deud.tiles.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;

public class House extends StaticEntity {

    @Getter private final int houseType;

    public House(@NonNull GameAPI gameAPI, float x, float y, int houseType) {
        super(252, "House", EntityData.EntityType.HOUSE, gameAPI, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);

        this.houseType = houseType;

        setDamageable(false);

        bounds.x = -70;
        bounds.y = height - (height * 4) - 10;
        bounds.width = width * 3 + 15;
        bounds.height = height * 5 - 45;
    }

    @Override
    public void tick() {}

    @Override
    public void die() {}

    @Override
    public void render(Graphics g) {
        // Down to up
        g.drawImage(getImage(houseType, 7), (int) (x - gameAPI.getGameCamera().getXOffset() - width), (int) (y - gameAPI.getGameCamera().getYOffset()), width, height, null);
        g.drawImage(getImage(houseType, 4), (int) (x - gameAPI.getGameCamera().getXOffset()), (int) ((y - gameAPI.getGameCamera().getYOffset())), width, height, null);
        g.drawImage(getImage(houseType, 7), (int) (x - gameAPI.getGameCamera().getXOffset() + width), (int) ((y - gameAPI.getGameCamera().getYOffset())), width, height, null);

        g.drawImage(getImage(houseType, 7), (int) (x - gameAPI.getGameCamera().getXOffset() - width), (int) (y - gameAPI.getGameCamera().getYOffset() - height), width, height, null);
        g.drawImage(getImage(houseType, 7), (int) (x - gameAPI.getGameCamera().getXOffset()), (int) ((y - gameAPI.getGameCamera().getYOffset()) - height), width, height, null);
        g.drawImage(getImage(houseType, 7), (int) (x - gameAPI.getGameCamera().getXOffset() + width), (int) ((y - gameAPI.getGameCamera().getYOffset()) - height), width, height, null);

        g.drawImage(getImage(houseType, 6), (int) (x - gameAPI.getGameCamera().getXOffset() - width), (int) (y - gameAPI.getGameCamera().getYOffset() - (height * 2)), width, height, null);
        g.drawImage(getImage(houseType, 7), (int) (x - gameAPI.getGameCamera().getXOffset()), (int) ((y - gameAPI.getGameCamera().getYOffset()) - (height * 2)), width, height, null);
        g.drawImage(getImage(houseType, 8), (int) (x - gameAPI.getGameCamera().getXOffset() + width), (int) ((y - gameAPI.getGameCamera().getYOffset()) - (height * 2)), width, height, null);

        g.drawImage(getImage(houseType, 3), (int) (x - gameAPI.getGameCamera().getXOffset() - width), (int) (y - gameAPI.getGameCamera().getYOffset() - (height * 3)), width, height, null);
        g.drawImage(getImage(houseType, 1), (int) (x - gameAPI.getGameCamera().getXOffset()), (int) ((y - gameAPI.getGameCamera().getYOffset()) - (height * 3)), width, height, null);
        g.drawImage(getImage(houseType, 5), (int) (x - gameAPI.getGameCamera().getXOffset() + width), (int) ((y - gameAPI.getGameCamera().getYOffset()) - (height * 3)), width, height, null);

        g.drawImage(getImage(houseType, 0), (int) (x - gameAPI.getGameCamera().getXOffset() - width), (int) (y - gameAPI.getGameCamera().getYOffset() - (height * 4)), width, height, null);
        g.drawImage(getImage(houseType, 1), (int) (x - gameAPI.getGameCamera().getXOffset()), (int) ((y - gameAPI.getGameCamera().getYOffset()) - (height * 4)), width, height, null);
        g.drawImage(getImage(houseType, 2), (int) (x - gameAPI.getGameCamera().getXOffset() + width), (int) ((y - gameAPI.getGameCamera().getYOffset()) - (height * 4)), width, height, null);
    }

    private BufferedImage getImage(int type, int index) {
        switch (type) {
            case 2:
                return Assets.house2[index];
            case 3:
                return Assets.house3[index];
            default:
                return Assets.house1[index];
        }
    }
}
