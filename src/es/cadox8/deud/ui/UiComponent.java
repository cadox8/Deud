package es.cadox8.deud.ui;

import es.cadox8.deud.api.GameAPI;
import es.cadox8.deud.graphics.fonts.Fonts;
import es.cadox8.deud.ui.helpers.UiDimension;
import lombok.Data;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Random;

@Data
public abstract class UiComponent {

    protected final GameAPI gameAPI = GameAPI.getInstance();

    protected final long componentId;

    protected final int layer;

    protected boolean enabled;
    protected boolean draggable;
    protected boolean hovering;

    protected Font font;

    protected UiDimension uiDimension;

    public UiComponent() {
        this.componentId = new Random().nextLong();

        this.layer = 0;

        this.enabled = true;
        this.draggable = false;
        this.hovering = false;

        this.font = Fonts.DEUD.font();

        this.uiDimension = new UiDimension();
    }

    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract void onClick();

    public void onMouseMove(MouseEvent e) {
        this.setHovering(this.getUiDimension().getBounds().contains(e.getX(), e.getY()));
    }

    public void onMouseDragged(MouseEvent e) {
        if (hovering && isDraggable()) {
            this.getUiDimension().addX(e.getX() - this.getUiDimension().getX());
            this.getUiDimension().addY(e.getY() - this.getUiDimension().getY());
        }
    }

    public void onMouseClicked(MouseEvent e) {
        if (this.isHovering()) this.onClick();
    }
}
