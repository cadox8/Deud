package me.cadox8.deud.graphics.fonts;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Fonts {

    private static ArrayList<Font> deudFonts;

    public static void init() {
        deudFonts = new ArrayList<>();

        deudFonts.add(loadFont("resources/fonts/deud2.ttf", 34)); //0
        deudFonts.add(loadFont("resources/fonts/deud2.ttf", 14)); //1
        deudFonts.add(loadFont("resources/fonts/deud2.ttf", 20)); //2
        deudFonts.add(loadFont("resources/fonts/deud2.ttf", 108)); //3 (Death Screen)
    }

    public static Font getFont(int id) {
        return deudFonts.get(id);
    }

    public static Font loadFont(String path, float size) {
        try {
            return Font.createFont(Font.TRUETYPE_FONT, new File(path)).deriveFont(Font.PLAIN, size);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }
}
