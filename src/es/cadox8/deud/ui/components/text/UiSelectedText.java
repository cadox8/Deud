package es.cadox8.deud.ui.components.text;

import es.cadox8.deud.ui.helpers.UiColor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.awt.*;

@EqualsAndHashCode(callSuper = true)
@ToString
public class UiSelectedText extends UiText {

    private Color defaultForeground;

    public UiSelectedText(String text) {
        super(text);
    }

    @Override
    public void tick() {
        if (this.defaultForeground == null) this.defaultForeground = this.getForeground();
        this.setForeground(this.isHovering() ? UiColor.GREEN.color() : this.defaultForeground);
    }
}
