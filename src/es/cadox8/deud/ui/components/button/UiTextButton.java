package es.cadox8.deud.ui.components.button;

import es.cadox8.deud.ui.components.text.UiText;
import es.cadox8.deud.ui.helpers.ClickListener;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.awt.*;

@EqualsAndHashCode(callSuper = true)
@ToString
public class UiTextButton extends UiButton {

    @Getter private final UiText text;

    public UiTextButton(String text, ClickListener clickListener) {
        super(clickListener);

        this.text = new UiText(text);
    }

    public UiTextButton setTransparent() {
        super.setTransparent();
        return this;
    }

    @Override
    public void tick() {
        super.tick();
        this.text.setUiDimension(this.getUiDimension());
    }

    @Override
    public void render(Graphics g) {
        super.render(g);
        this.text.render(g);
    }
}
