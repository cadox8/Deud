package me.cadox8.deud.entities.statics.sign;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.entities.EntityData;
import me.cadox8.deud.entities.statics.StaticEntity;
import me.cadox8.deud.graphics.textures.Assets;
import me.cadox8.deud.tiles.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class Sign extends StaticEntity {

    private final BufferedImage img;
    @Setter @Getter private Sign sign;
    @Getter private List<String> text;

    @Getter @Setter private boolean shown = false;

    public Sign(@NonNull GameAPI gameAPI, float x, float y, List<String> text) {
        super(500, "Sign", EntityData.EntityType.SIGN, gameAPI, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);

        setDamageable(false);

        this.text = text;

        img = Assets.sign;
        bounds.x = 4;
        bounds.y = 23;
        bounds.width = width + 3;
        bounds.height = 33;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(img, (int) (x - gameAPI.getGameCamera().getXOffset()), (int) (y - gameAPI.getGameCamera().getYOffset()), width, height, null);
    }

    public void postRender(Graphics g) {
        if (sign != null) sign.render(g);
    }

    @Override
    public String toString() {
        return "Entity: {ID: " + getINTERNAL_ID() + ", Name: " + getINTERNAL_NAME() + ", X: " + getX() + ", Y: " + getY() + ", Type: " + getENTITY_TYPE() + ", Text: " + getText().toString() + "}";
    }
}
