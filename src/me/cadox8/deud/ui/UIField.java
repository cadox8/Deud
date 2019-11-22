package me.cadox8.deud.ui;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import me.cadox8.deud.api.GameAPI;

import java.awt.*;

public class UIField extends UIObject {

    private ClickListener clicker;

    @Getter @Setter private String text = "";

    @Getter @Setter private Color baseColor = new Color(217, 217, 217);

    public UIField(float x, float y, int width, int height, @NonNull GameAPI gameAPI) {
        super(x, y - height, width, height);

        this.clicker = () -> gameAPI.getKeyManager().setWritingTo(this);
    }

    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics g) {
        String drawText = "";

        if (!canWrite(g, text)) drawText = text;

        g.setColor(Color.BLACK);
        g.drawRect((int)getX(), (int)getY(), getWidth(), getHeight());

        g.setColor(baseColor);
        g.fillRect((int)getX(), (int)getY(), getWidth(), getHeight());
        new UIText(x + 2, y + height - (height / 5f) - 2, Color.BLACK, drawText, () -> {}).render(g);
    }

    @Override
    public void onClick() {
        clicker.onClick();
    }

    private boolean canWrite(Graphics g, String text) {
        return g.getFontMetrics(g.getFont()).stringWidth(text) > width;
    }
}
