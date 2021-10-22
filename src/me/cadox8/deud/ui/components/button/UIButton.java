package me.cadox8.deud.ui.components.button;

import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.ui.components.base.UIBlock;
import me.cadox8.deud.ui.helpers.AarinColor;
import me.cadox8.deud.ui.helpers.ClickListener;

public abstract class UIButton extends UIBlock {

    protected final ClickListener clicker;

    public UIButton(GameAPI gameAPI, AarinColor color, boolean filled, ClickListener clicker) {
        super(gameAPI);
        this.clicker = clicker;

        this.setHoverable(true);
        this.setClickable(true);
    }
}
