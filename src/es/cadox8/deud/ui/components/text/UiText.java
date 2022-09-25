package es.cadox8.deud.ui.components.text;

import es.cadox8.deud.ui.UiComponent;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.awt.*;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class UiText extends UiComponent {

    private Color foreground;

    private String text;

    private boolean centered;

    public UiText(String text) {
        this(Color.WHITE, text);
    }

    public UiText(Color foreground, String text) {
        super();
        this.foreground = foreground;
        this.text = text;

        this.centered = true;
    }

    public UiText setCentered(boolean centered) {
        this.centered = centered;
        return this;
    }

    @Override
    public void tick() {}

    @Override
    public void render(Graphics g) {
        g.setColor(this.getForeground());
        g.setFont(this.getFont());
        int x = this.getUiDimension().getX();
        int y = this.getUiDimension().getY();
        if (this.isCentered()) {
            final FontMetrics fm = g.getFontMetrics(this.getFont());
            x = this.getUiDimension().getX() - fm.stringWidth(text) / 2;
            y = (this.getUiDimension().getY() - fm.getHeight() / 2) + fm.getAscent();
        }
        g.drawString(this.text, x, y);
    }

    @Override
    public void onClick() {}
}
