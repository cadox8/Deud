package me.cadox8.deud.gfx.fonts;

import me.cadox8.deud.utils.DeudColor;

import java.awt.*;

public class Text {

    public static void drawString(Graphics g, String text, int xPos, int yPos, boolean center, int font) {
        drawString(g, text, xPos, yPos, center, DeudColor.WHITE, font);
    }

    public static void drawString(Graphics g, String text, int xPos, int yPos, boolean center) {
        drawString(g, text, xPos, yPos, center, 0);
    }

    public static void drawString(Graphics g, String text, int xPos, int yPos, DeudColor c, int font) {
        drawString(g, text, xPos, yPos, false, c, font);
    }

    public static void drawString(Graphics g, String text, int xPos, int yPos, int font) {
        drawString(g, text, xPos, yPos, false, DeudColor.WHITE, font);
    }


    public static void drawString(Graphics g, String text, int xPos, int yPos, boolean center, DeudColor c, int font) {
        g.setColor(c.toColor());
        g.setFont(Fonts.getFont(font));
        int x = xPos;
        int y = yPos;
        if (center) {
            FontMetrics fm = g.getFontMetrics(Fonts.getFont(font));
            x = xPos - fm.stringWidth(text) / 2;
            y = (yPos - fm.getHeight() / 2) + fm.getAscent();
        }
        g.drawString(text, x, y);
    }
}
