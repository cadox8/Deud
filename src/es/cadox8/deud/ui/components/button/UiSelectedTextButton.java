package es.cadox8.deud.ui.components.button;

import es.cadox8.deud.ui.components.text.UiSelectedText;
import es.cadox8.deud.ui.helpers.ClickListener;

import java.awt.*;

public class UiSelectedTextButton extends UiButton {

    private final UiSelectedText selectedText;

    public UiSelectedTextButton(String text, ClickListener clickListener) {
        super(clickListener);

        this.selectedText = new UiSelectedText(text);
    }

    @Override
    public void tick() {
        super.tick();
        this.selectedText.setUiDimension(this.getUiDimension());
        this.selectedText.tick();
    }

    @Override
    public void render(Graphics g) {
        super.render(g);
        this.selectedText.render(g);
    }
}
