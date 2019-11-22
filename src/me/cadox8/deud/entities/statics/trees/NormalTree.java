package me.cadox8.deud.entities.statics.trees;

import lombok.NonNull;
import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.gfx.textures.Assets;

import java.awt.*;

public class NormalTree extends Tree {

    public NormalTree(@NonNull GameAPI gameAPI, float x, float y) {
        super(100, "Tree", gameAPI, x, y);

        bounds.x = 10;
        bounds.y = (int) (height / 1.5f);
        bounds.width = width - 20;
        bounds.height = (int) (height - height / 1.5f);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.tree2, (int) (x - gameAPI.getGameCamera().getXOffset()), (int) (y - gameAPI.getGameCamera().getYOffset()), width, height, null);
        g.drawImage(Assets.tree, (int) (x - gameAPI.getGameCamera().getXOffset()), (int) ((y - gameAPI.getGameCamera().getYOffset()) - height), width, height, null);
    }
}
