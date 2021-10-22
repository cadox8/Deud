package me.cadox8.deud.ui.components.button;

import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.ui.components.text.UIText;
import me.cadox8.deud.ui.helpers.AarinArea;
import me.cadox8.deud.ui.helpers.AarinColor;
import me.cadox8.deud.ui.helpers.ClickListener;

import java.awt.*;

public class UITextButton extends UIButton {

    private final UIText uiText;

    public UITextButton(GameAPI gameAPI, AarinColor color, boolean filled, String text, ClickListener clicker) {
        super(gameAPI, color, filled, clicker);
        this.uiText = new UIText(gameAPI, text);
    }

    @Override
    public void tick() {}

    @Override
    public void render(Graphics g) {
        super.render(g);
        this.uiText.render(g);
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
        this.uiText.setArea(textArea);
    }
}
