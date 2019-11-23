package me.cadox8.deud.entities.statics.sign;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.entities.EntityData;
import me.cadox8.deud.entities.statics.StaticEntity;
import me.cadox8.deud.gfx.textures.Assets;
import me.cadox8.deud.tiles.Tile;
import me.cadox8.deud.utils.Utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class SignEntity extends StaticEntity {

    private BufferedImage img;
    @Setter @Getter private Sign sign;
    @Getter private List<String> text;

    @Getter private final int type;

    public SignEntity(@NonNull GameAPI gameAPI, float x, float y, int type, List<String> text) {
        super(500, "Sign", EntityData.EntityType.SIGN, gameAPI, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);

        setDamageable(false);

        this.text = text;
        this.type = type;

        switch (type) {
            case 0:
                img = Assets.sign2;
                bounds.x = 22;
                bounds.y = (int) (height / 2f) - 29;
                bounds.width = (width / 4) - 1;
                bounds.height = height - 7;
                break;
            case 1:
                img = Utils.rotateImage(90, Assets.sign2);
                bounds.x = 4;
                bounds.y = 23;
                bounds.width = width - 8;
                bounds.height = 13;
                break;
        }
    }

    @Override
    public void render(Graphics g) {
    }

    @Override
    public void preRender(Graphics g) {
        g.drawImage(img, (int) (x - gameAPI.getGameCamera().getXOffset()), (int) (y - gameAPI.getGameCamera().getYOffset()), width, height, null);
    }

    public void signRender(Graphics g) {
        if (sign != null) sign.render(g);
    }

    @Override
    public String toString() {
        return "Entity: {ID: " + getINTERNAL_ID() + ", Name: " + getINTERNAL_NAME() + ", X: " + getX() + ", Y: " + getY() + ", Type: " + getENTITY_TYPE() + ", Text: " + getText().toString() + "}";
    }
}
