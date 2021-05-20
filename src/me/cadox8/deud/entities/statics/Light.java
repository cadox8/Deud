package me.cadox8.deud.entities.statics;

import lombok.Getter;
import lombok.Setter;
import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.entities.enums.EntityType;

import java.awt.*;

public class Light extends StaticEntity {

    @Getter @Setter private int radius;
    @Getter @Setter private Color[] colors;

    @Getter @Setter private float luminosity;

    private boolean staticLight = true;

    private final float[] dist = { 0.2f, 1.0f };

    public Light(GameAPI gameAPI, int x, int y, int radius, float luminosity) {
        this(gameAPI, x, y, radius, luminosity, new Color(255, 255, 255, (int)(luminosity * 100)));
    }

    public Light(GameAPI gameAPI, int x, int y, int radius, float luminosity, Color color) {
        super("16c2f2a2-af1e-46f7-9374-27a9dc3dbe74", "Light", EntityType.LIGHT, gameAPI, x, y, 0, 0);

        this.luminosity = luminosity;
        this.radius = radius;
        this.colors = new Color[]{new Color(color.getRed(), color.getGreen(), color.getBlue(), (int) (luminosity * 100)), new Color(0, 0, 0, 0)};
    }

    @Override
    public void tick() {
    }

    @Override
    public void postRender(Graphics g) {
        final Graphics2D g2 = (Graphics2D)g;
        Point point = new Point((int)x, (int)y);
        if (isStatic()) point = new Point((int)x - (int)gameAPI.getGameCamera().getXOffset(), (int)y - (int)gameAPI.getGameCamera().getYOffset());
        final RadialGradientPaint p = new RadialGradientPaint(point, radius, dist, colors);

        g2.setPaint(p);
        g2.fillRect((int)x - radius, (int)y - radius, radius * 2, radius * 2);
    }

    @Override
    public void render(Graphics g) {
    }

    public boolean isStatic() {
        return staticLight;
    }

    public Light setStatic(boolean staticLight) {
        this.staticLight = staticLight;
        return this;
    }
}
