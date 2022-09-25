package es.cadox8.deud.graphics.fonts;

import java.awt.*;

@Deprecated
public class Text {

    @Deprecated
    public static void drawString(Graphics g, String text, int xPos, int yPos, boolean center, Fonts font) {
        drawString(g, text, xPos, yPos, center, Color.WHITE, font);
    }

    @Deprecated
    public static void drawString(Graphics g, String text, int xPos, int yPos, boolean center) {
        drawString(g, text, xPos, yPos, center, Fonts.DEUD);
    }

    @Deprecated
    public static void drawString(Graphics g, String text, int xPos, int yPos, Color c, Fonts font) {
        drawString(g, text, xPos, yPos, false, c, font);
    }

    @Deprecated
    public static void drawString(Graphics g, String text, int xPos, int yPos, Fonts font) {
        drawString(g, text, xPos, yPos, false, Color.WHITE, font);
    }

    @Deprecated
    public static void drawString(Graphics g, String text, int xPos, int yPos, boolean center, Color color, Fonts font) {
        g.setColor(color);
        g.setFont(font.font());
        int x = xPos;
        int y = yPos;
        if (center) {
            final FontMetrics fm = g.getFontMetrics(font.font());
            x = xPos - fm.stringWidth(text) / 2;
            y = (yPos - fm.getHeight() / 2) + fm.getAscent();
        }
        g.drawString(text, x, y);
    }
}
