package es.cadox8.deud.entities.statics.sign;

import es.cadox8.deud.api.GameAPI;
import es.cadox8.deud.entities.enums.EntityType;
import es.cadox8.deud.graphics.textures.Assets;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import es.cadox8.deud.entities.statics.StaticEntity;
import es.cadox8.deud.tiles.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class Sign extends StaticEntity {

    private final BufferedImage img;
    @Setter @Getter private Sign sign;
    @Getter private List<String> text;

    @Getter @Setter private boolean shown = false;

    public Sign(@NonNull GameAPI gameAPI, float x, float y, List<String> text) {
        super("b06ff805-c536-41fd-b49f-3bd195c1eeff", "Sign", EntityType.SIGN, gameAPI, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);

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
        return "Entity: {ID: " + getUUID() + ", Name: " + getINTERNAL_NAME() + ", X: " + getX() + ", Y: " + getY() + ", Type: " + getENTITY_TYPE() + ", Text: " + getText().toString() + "}";
    }
}
