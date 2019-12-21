package me.cadox8.deud.achievement;

import lombok.Getter;
import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.gfx.textures.Assets;
import me.cadox8.deud.nysvaui.components.base.UIBlock;
import me.cadox8.deud.nysvaui.components.images.UIImage;
import me.cadox8.deud.nysvaui.helpers.UIDimension;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Achievement {

    @Getter private final int id;
    @Getter private final String name;
    @Getter private final BufferedImage icon;

    public Achievement(int id, String name) {
        this(id, name, null);
    }
    public Achievement(int id, String name, BufferedImage icon) {
        this.id = id;
        this.name = name;
        this.icon = icon == null ? Assets.bug : icon;
    }


    public void showAchievement(GameAPI gameAPI, Graphics g) {
        final UIBlock block = new UIBlock(gameAPI);
        final UIImage image = new UIImage(gameAPI, getIcon());

        block.setUIDimension(new UIDimension(5, 5, 350, 100));
        block.setRounded(true);

        image.setUIDimension(new UIDimension(5, 18, 64, 64));

        block.addUIComponent(image);
        block.render(g);
    }
}
