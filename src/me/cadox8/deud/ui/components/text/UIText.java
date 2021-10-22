package me.cadox8.deud.ui.components.text;

import lombok.Getter;
import lombok.Setter;
import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.graphics.fonts.Fonts;
import me.cadox8.deud.graphics.fonts.Text;
import me.cadox8.deud.ui.AarinUI;
import me.cadox8.deud.ui.helpers.AarinColor;

import java.awt.*;

public class UIText extends AarinUI {

    protected AarinColor textColor = AarinColor.WHITE;
    @Getter @Setter protected String text;

    public UIText(GameAPI gameAPI, String text) {
        super(gameAPI);
        this.text = text;

        this.setFont(Fonts.DEUD);

        this.setClickable(false);
        this.setHoverable(false);
    }

    @Override
    public void tick() {}

    @Override
    public void render(Graphics g) {
        final Point location = this.getArea().getFirstPoint();
        Text.drawString(g, this.text, (int)location.getX(), (int)location.getY(), true, this.textColor.getColor(), this.getFont());
    }

    @Override
    public void onClick() {}
}
