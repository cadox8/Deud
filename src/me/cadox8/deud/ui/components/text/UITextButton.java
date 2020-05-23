package me.cadox8.deud.ui.components.text;

import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.ui.ClickListener;

public class UITextButton extends UIText {

    private ClickListener clicker;

    public UITextButton(GameAPI gameAPI, String text, ClickListener clicker) {
        super(gameAPI, text);

        this.clicker = clicker;
    }

    @Override
    public void onClick() {
        clicker.onClick();
    }
}
