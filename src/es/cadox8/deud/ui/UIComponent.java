package es.cadox8.deud.ui;

import es.cadox8.deud.api.GameAPI;
import es.cadox8.deud.audio.SoundType;
import es.cadox8.deud.graphics.fonts.Fonts;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

@Getter
@Setter
public abstract class UIComponent {

    private final long componentId;

    private final Appearance appearance;
    private final Appearance hoveredAppearance;
    private final Appearance disabledAppearance;

    private final List<UIComponent> components;

    private boolean enabled;
    private Font font;
    private double width;
    private double height;

    private SoundType hoverSound;
    private boolean textAntialiasing;
    private boolean textShadow;

    private Color textShadowColor;
    private float textShadowRadius;

    private boolean hovered;
    private boolean pressed;
    private boolean selected;
    private String name;
    private boolean suspended;
    private Object tag;
    private String text;
    private boolean automaticLineBreaks;
    private int textAngle = 0;

    private double textX;
    private double textY;
    private boolean visible;
    private Point2D location;
    private Rectangle2D boundingBox;

    protected UIComponent(final double x, final double y){
        this(x, y, 0, 0);
    }

    protected UIComponent(final double x, final double y, final double width, final double height) {
        this.componentId = new Random().nextLong();

        this.components = new CopyOnWriteArrayList<>();

        this.appearance = new Appearance();
        this.hoveredAppearance = new Appearance();
        this.disabledAppearance = new Appearance();

        this.location = new Point2D.Double(x, y);

        this.width = width;
        this.height = height;
        this.font = Fonts.DEUD.font();

        this.selected = false;
        this.enabled = true;
    }

    public Rectangle2D getBoundingBox() {
        if (boundingBox != null) {
            return boundingBox;
        }

        this.boundingBox = new Rectangle2D.Double(getX(), getY(), getWidth(), getHeight());
        return boundingBox;
    }

    public String getTextToRender(final Graphics2D g) {
        if (this.getText() == null) {
            return "";
        } else if (hasAutomaticLineBreaks()) {
            return getText();
        }
        final FontMetrics fm = g.getFontMetrics();
        String newText = getText();

        while (newText.length() > 1 && fm.stringWidth(newText) >= getWidth()) {
            newText = newText.substring(1);
        }
        return newText;
    }

    /**
     * Gets the x coordinate of this GuiComponent.
     *
     * @return the x coordinate
     */
    public double getX() {
        return getLocation().getX();
    }

    /**
     * Gets the y coordinate of this GuiComponent.
     *
     * @return the y coordinate
     */
    public double getY() {
        return getLocation().getY();
    }
}
