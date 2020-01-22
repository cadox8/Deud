package me.cadox8.deud.nysvaui.components.text;

import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.gfx.textures.Assets;
import me.cadox8.deud.nysvaui.ClickListener;
import me.cadox8.deud.nysvaui.components.base.UIBlock;
import me.cadox8.deud.nysvaui.helpers.NysvaColor;
import me.cadox8.deud.nysvaui.helpers.UIDimension;

import java.awt.*;

public class UISelectedTextButton extends UITextButton {

    private boolean changed = false;

    private final UIBlock block;

    public UISelectedTextButton(GameAPI gameAPI, String text, ClickListener clicker) {
        super(gameAPI, text, clicker);

        block = new UIBlock(gameAPI, NysvaColor.DARK_GRAY);
    }

    public void tick() {
        if (hovering) {
            if (changed) return;
            block.setUiDimension(UIDimension.fromUIDimension(getUiDimension()).addY(2));
            changed = true;
        } else {
            if (!changed) return;
            changed = false;
        }
    }

    public void render(Graphics g) {
        if (changed) {
            block.render(g);
            g.drawImage(Assets.optionsSelector, getUiDimension().getX() - 32, getUiDimension().getY() + 4, null);
            g.drawImage(Assets.optionsSelector, getUiDimension().getX() + getUiDimension().getWidth(), getUiDimension().getY() + 4, null);
        }
        drawString(g);
    }
}
