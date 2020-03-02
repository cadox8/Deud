package me.cadox8.deud.ui.nysvaui.components.text;

import lombok.Getter;
import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.gfx.textures.Assets;
import me.cadox8.deud.ui.nysvaui.ClickListener;
import me.cadox8.deud.ui.nysvaui.components.base.UIBlock;
import me.cadox8.deud.ui.nysvaui.helpers.NysvaColor;
import me.cadox8.deud.ui.nysvaui.helpers.UIDimension;

import java.awt.*;

public class UISelectedTextButton extends UITextButton {

    private boolean changed = false;

    @Getter private final UIBlock baseBlock;

    public UISelectedTextButton(GameAPI gameAPI, String text, ClickListener clicker) {
        super(gameAPI, text, clicker);

        baseBlock = new UIBlock(gameAPI, NysvaColor.DARK_GRAY);
    }

    public void tick() {
        if (hovering) {
            if (changed) return;
            baseBlock.setUiDimension(UIDimension.fromUIDimension(getUiDimension()).addY(2));
            changed = true;
        } else {
            if (!changed) return;
            changed = false;
        }
    }

    public void render(Graphics g) {
        if (changed) {
            baseBlock.render(g);
            g.drawImage(Assets.optionsSelector, getUiDimension().getX() - 32, getUiDimension().getY() + 4, null);
            g.drawImage(Assets.optionsSelector, getUiDimension().getX() + getUiDimension().getWidth(), getUiDimension().getY() + 4, null);
        }
        drawString(g);
    }
}
