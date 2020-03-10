package me.cadox8.deud.gfx.fonts;

import java.awt.*;

public class Text {

    public static void drawString(Graphics g, String text, int xPos, int yPos, boolean center, int font) {
        drawString(g, text, xPos, yPos, center, Color.WHITE, font);
    }

    public static void drawString(Graphics g, String text, int xPos, int yPos, boolean center) {
        drawString(g, text, xPos, yPos, center, 0);
    }

    public static void drawString(Graphics g, String text, int xPos, int yPos, Color c, int font) {
        drawString(g, text, xPos, yPos, false, c, font);
    }

    public static void drawString(Graphics g, String text, int xPos, int yPos, int font) {
        drawString(g, text, xPos, yPos, false, Color.WHITE, font);
    }


    public static void drawString(Graphics g, String text, int xPos, int yPos, boolean center, Color c, int font) {
        g.setColor(c);
        g.setFont(Fonts.getFont(font));
        int x = xPos;
        int y = yPos;
        if (center) {
            final FontMetrics fm = g.getFontMetrics(Fonts.getFont(font));
            x = xPos - fm.stringWidth(text) / 2;
            y = (yPos - fm.getHeight() / 2) + fm.getAscent();
        }
        g.drawString(text, x, y);
    }
}
