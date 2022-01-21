package es.cadox8.deud.ui;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;

public class Appearance {

    @Getter @Setter private Color foregroundColor;
    @Getter @Setter private Color backgroundColor;
    @Getter @Setter private Color borderColor;
    @Getter @Setter private Stroke borderStyle;
    @Getter @Setter private float borderRadius;
    @Getter @Setter private boolean transparentBackground;

    public Appearance() {}

    public Appearance(Color foregroundColor) {
        this();
        this.foregroundColor = foregroundColor;
        this.setTransparentBackground(true);
    }

    public Appearance(Color foregroundColor, Color backgroundColor) {
        this();
        this.foregroundColor = foregroundColor;
        this.backgroundColor = backgroundColor;
    }

    public Paint getBackgroundPaint(double width, double height) {
        if (this.isTransparentBackground()) return null;
        return this.backgroundColor;
    }

    public void update(Appearance updateAppearance) {
        this.setBackgroundColor(updateAppearance.getBackgroundColor());
        this.setForegroundColor(updateAppearance.getForegroundColor());
        this.setBorderColor(updateAppearance.getBorderColor());
        this.setBorderRadius(updateAppearance.getBorderRadius());
        this.setBorderStyle(updateAppearance.getBorderStyle());
        this.setTransparentBackground(updateAppearance.isTransparentBackground());
    }
}
