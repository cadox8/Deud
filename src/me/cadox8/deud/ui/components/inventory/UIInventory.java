package me.cadox8.deud.ui.components.inventory;

import lombok.Getter;
import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.items.Item;
import me.cadox8.deud.ui.AarinUI;
import me.cadox8.deud.ui.components.image.UIImage;
import me.cadox8.deud.ui.components.text.UIText;
import me.cadox8.deud.ui.helpers.AarinArea;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UIInventory extends AarinUI {

    private final Point start;

    private final UIImage base;
    private final UIText itemInfo;

    public UIInventory(GameAPI gameAPI, BufferedImage image) {
        this(gameAPI, new Point(0, 0), image);
    }
    public UIInventory(GameAPI gameAPI, Point start, BufferedImage image) {
        super(gameAPI);
        this.start = start;

        final AarinArea area = new AarinArea();
        area.addPoints(start, new Point(image.getWidth(), image.getHeight()));

        this.setClickable(false);
        this.setHoverable(false);

        this.setArea(area);

        this.itemInfo = new UIText(gameAPI, "---------");
        this.base = new UIImage(gameAPI, image);
        this.base.setArea(area);
        this.base.setResizable(false);
        this.base.setClickable(false);
        this.base.setHoverable(false);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        this.base.render(g);
        this.itemInfo.render(g);
    }

    @Override
    public void onClick() {}

    protected void displayItemInfo(String item, int x, int y) {
        if (item == null) {
            this.itemInfo.setText("---------");
        } else {
            this.itemInfo.setText(item);
        }
    }
}
