package me.cadox8.deud.ui.components.button;

import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.ui.components.image.UIImage;
import me.cadox8.deud.ui.helpers.AarinArea;
import me.cadox8.deud.ui.helpers.AarinColor;
import me.cadox8.deud.ui.helpers.ClickListener;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UIImageButton extends UIButton {

    private final UIImage uiImage;

    public UIImageButton(GameAPI gameAPI, BufferedImage image, ClickListener clicker) {
        this(gameAPI, AarinColor.TRANSPARENT, false, image, clicker);
    }
    public UIImageButton(GameAPI gameAPI, AarinColor color, boolean filled, BufferedImage image, ClickListener clicker) {
        super(gameAPI, color, filled, clicker);
        this.uiImage = new UIImage(gameAPI, image);
    }

    @Override
    public void tick() {}

    @Override
    public void render(Graphics g) {
        this.uiImage.render(g);
    }

    @Override
    public void onClick() {
        this.clicker.onClick();
    }

    @Override
    public void setArea(AarinArea area) {
        super.setArea(area);
        final AarinArea textArea = area;
        textArea.addMargins(10, area.getPolygon().getBounds().height / 3);
        this.uiImage.setArea(textArea);
    }
}
