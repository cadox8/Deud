package es.cadox8.deud.ui.components.button;

import es.cadox8.deud.ui.UiComponent;
import es.cadox8.deud.ui.components.block.UiBlock;
import es.cadox8.deud.ui.helpers.ClickListener;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.awt.*;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class UiButton extends UiComponent {

    private ClickListener clickListener;

    private final UiBlock base;

    public UiButton(ClickListener clickListener) {
        this.clickListener = clickListener;

        this.base = new UiBlock(Color.YELLOW);
        this.setHoverable(true);
    }

    public UiButton setTransparent() {
        this.base.setBackground(new Color(0,0,0, 0));
        return this;
    }

    @Override
    public void tick() {
        this.base.setUiDimension(this.getUiDimension());
    }

    @Override
    public void render(Graphics g) {
        this.base.render(g);
    }

    @Override
    public void onClick() {
        this.clickListener.onClick();
    }
}
