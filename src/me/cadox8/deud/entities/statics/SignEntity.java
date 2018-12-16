package me.cadox8.deud.entities.statics;

import lombok.Getter;
import lombok.Setter;
import me.cadox8.deud.api.API;
import me.cadox8.deud.gfx.textures.Assets;
import me.cadox8.deud.sign.Sign;
import me.cadox8.deud.tiles.Tile;
import me.cadox8.deud.utils.Utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class SignEntity extends StaticEntity {

    private BufferedImage img;
    @Setter @Getter private Sign sign;
    @Getter private List<String> whatToSay;

    public SignEntity(API API, float x, float y, int type, List<String> whatToSay) {
        super(7, "Sign", API, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);

        setDamageable(false);

        this.whatToSay = whatToSay;

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
        if (sign != null) sign.render(g);
    }

    @Override
    public void specialRender(Graphics g) {
        g.drawImage(img, (int) (x - API.getGameCamera().getXOffset()), (int) (y - API.getGameCamera().getYOffset()), width, height, null);
    }
}
