package me.cadox8.deud.ui.components.text;

import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.ui.components.button.UITextButton;
import me.cadox8.deud.ui.helpers.AarinColor;
import me.cadox8.deud.ui.helpers.ClickListener;

public class UISelectedText extends UITextButton {

    public UISelectedText(GameAPI gameAPI, AarinColor color, boolean filled, String text, ClickListener clicker) {
        super(gameAPI, color, filled, text, clicker);

        this.setColor(AarinColor.TRANSPARENT);
    }

    @Override
    public void tick() {
        if (this.isHovering()) {
            this.setColor(AarinColor.DARK_GRAY);
        } else {
            this.setColor(AarinColor.TRANSPARENT);
        }
    }
}
