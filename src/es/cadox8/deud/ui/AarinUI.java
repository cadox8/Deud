package es.cadox8.deud.ui;

import es.cadox8.deud.api.GameAPI;
import es.cadox8.deud.ui.helpers.AarinArea;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import es.cadox8.deud.graphics.fonts.Fonts;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Random;

public abstract class AarinUI {

    @Getter private final long componentId;

    protected final GameAPI gameAPI;

    @Getter @Setter private boolean hoverable;
    @Getter @Setter private boolean clickable;

    @Getter @Setter private AarinArea area;

    @Getter @Setter protected boolean hovering;
    @Getter @Setter protected boolean enabled;


    @Getter @Setter protected HashMap<String, Object> metadata;
    //

    @Getter @Setter private Fonts font = GameAPI.getGameFont();

    public AarinUI(@NonNull final GameAPI gameAPI) {
        this.componentId = new Random().nextLong();
        this.gameAPI = gameAPI;

        this.hoverable = true;
        this.clickable = true;

        this.area = new AarinArea();

        this.hovering = false;
        this.enabled = true;

        this.metadata = new HashMap<>();
    }

    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract void onClick();

    public void onMouseMove(MouseEvent e) {
        if (this.isHoverable()) this.hovering = this.area.isInside(e.getPoint());
    }
    public void onMouseClicked(MouseEvent e) {
        if (this.isHoverable() && this.isHovering() && this.isClickable()) onClick();
    }
}
