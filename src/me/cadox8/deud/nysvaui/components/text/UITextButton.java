package me.cadox8.deud.nysvaui.components.text;

import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.nysvaui.ClickListener;

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
