package me.cadox8.deud.graphics.fonts;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Fonts {

    private static ArrayList<Font> deudFonts;

    public static void init() {
        deudFonts = new ArrayList<>();

        deudFonts.add(loadFont("deud2", 34)); //0
        deudFonts.add(loadFont("deud", 14)); //1
        deudFonts.add(loadFont("deud2", 20)); //2
        deudFonts.add(loadFont("deud2", 108)); //3 (Death Screen)

        deudFonts.add(loadFont("Kylarzio", 14));
        deudFonts.add(loadFont("KylarzioTall", 14));
    }

    public static Font getFont(int id) {
        return deudFonts.get(id);
    }

    private static Font loadFont(String path, float size) {
        try {
            return Font.createFont(Font.TRUETYPE_FONT, new File("resources/fonts/" + path + ".ttf")).deriveFont(Font.PLAIN, size);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }
}
