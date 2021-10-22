package es.cadox8.deud.ui.components.button;

import es.cadox8.deud.api.GameAPI;
import es.cadox8.deud.ui.helpers.AarinColor;
import es.cadox8.deud.ui.helpers.ClickListener;
import es.cadox8.deud.ui.components.base.UIBlock;

public abstract class UIButton extends UIBlock {

    protected final ClickListener clicker;

    public UIButton(GameAPI gameAPI, AarinColor color, boolean filled, ClickListener clicker) {
        super(gameAPI);
        this.clicker = clicker;

        this.setHoverable(true);
        this.setClickable(true);
    }
}
