package me.cadox8.deud.ui.components.base;

import lombok.Getter;
import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.ui.AarinUI;
import me.cadox8.deud.ui.helpers.AarinColor;

import java.awt.*;

public class UIBlock extends AarinUI {

    @Getter private AarinColor color;

    @Getter private boolean filled;

    public UIBlock(GameAPI gameAPI) {
        this(gameAPI, true);
    }
    public UIBlock(GameAPI gameAPI, boolean filled) {
        this(gameAPI, AarinColor.DARK_GRAY, filled);
    }
    public UIBlock(GameAPI gameAPI, AarinColor color, boolean filled) {
        super(gameAPI);
        this.color = color;
        this.filled = filled;

        this.setHoverable(false);
        this.setClickable(false);
    }

    @Override
    public void tick() {}

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(color.getColor());
        graphics.drawPolygon(this.getArea().getPolygon());
        if (this.isFilled()) graphics.fillPolygon(this.getArea().getPolygon());
    }

    @Override
    public void onClick() {}

    public UIBlock setColor(int alpha) {
        this.setColor(this.getColor().transparent(alpha));
        return this;
    }
    public UIBlock setColor(AarinColor background) {
        this.setColor(background, 255);
        return this;
    }
    public UIBlock setColor(AarinColor background, int alpha) {
        this.color = background.transparent(alpha);
        return this;
    }
    public UIBlock setFilled(boolean filled) {
        this.filled = filled;
        return this;
    }
}
