package me.cadox8.deud.entities.statics.trees;

import lombok.NonNull;
import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.entities.EntityData;
import me.cadox8.deud.graphics.textures.Assets;
import me.cadox8.deud.items.Items;

import java.awt.*;

public class NormalTree extends Tree {

    public NormalTree(@NonNull GameAPI gameAPI, float x, float y) {
        super(100, "NormalTree", EntityData.EntityType.NORMALTREE, gameAPI, x, y);

        bounds.x = 10;
        bounds.y = (int) (height / 1.5f);
        bounds.width = width - 20;
        bounds.height = (int) (height - height / 1.5f);
    }

    @Override
    public void die() {
        dropItem(Items.getWoodItem().randomAmount(2, 6));
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.tree2, (int) (x - gameAPI.getGameCamera().getXOffset()), (int) (y - gameAPI.getGameCamera().getYOffset()), width, height, null);
        g.drawImage(Assets.tree, (int) (x - gameAPI.getGameCamera().getXOffset()), (int) ((y - gameAPI.getGameCamera().getYOffset()) - height), width, height, null);
    }
}
