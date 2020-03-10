package me.cadox8.deud.nysvaui.components.text;

import lombok.Getter;
import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.graphics.textures.Assets;
import me.cadox8.deud.nysvaui.ClickListener;
import me.cadox8.deud.nysvaui.components.base.UIBlock;
import me.cadox8.deud.nysvaui.helpers.NysvaColor;
import me.cadox8.deud.nysvaui.helpers.UIDimension;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;

public class UISelectedTextButton extends UITextButton {

    private boolean changed = false;

    @Getter private final UIBlock baseBlock;

    private int end;

    public UISelectedTextButton(GameAPI gameAPI, String text, ClickListener clicker) {
        super(gameAPI, text, clicker);

        baseBlock = new UIBlock(gameAPI, NysvaColor.DARK_GRAY);
    }

    public void tick() {
        if (hovering) {
            if (changed) return;
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
            g.drawImage(Assets.optionsSelector, getUiDimension().getX() + end, getUiDimension().getY() + 4, null);
        }
        drawString(g);
    }

    public void adjust() {
        final AffineTransform affinetransform = new AffineTransform();
        final FontRenderContext frc = new FontRenderContext(affinetransform,true,true);
        final int width = (int)getFont().getStringBounds(getText(), frc).getWidth() + 8;
        final UIDimension uid = UIDimension.fromUIDimension(getUiDimension()).addY(2).setWidth(width);
        final int mid = (int)gameAPI.getGame().getDisplay().getToolkit().getScreenSize().getWidth() / 2;

        uid.setX(mid - (width / 2));
        getUiDimension().setX(mid - (width / 2));

        end = width;

        baseBlock.setUiDimension(uid);
    }
}
