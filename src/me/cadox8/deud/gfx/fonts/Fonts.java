package me.cadox8.deud.gfx.fonts;

import java.awt.*;
import java.util.ArrayList;

public class Fonts {

    private static ArrayList<Font> deudFonts;

    public static void init() {
        deudFonts = new ArrayList<>();

        deudFonts.add(FontLoader.loadFont("resources/fonts/deud.ttf", 28)); //0
        deudFonts.add(FontLoader.loadFont("resources/fonts/deud.ttf", 10)); //1
        deudFonts.add(FontLoader.loadFont("resources/fonts/deud.ttf", 16)); //2
        deudFonts.add(FontLoader.loadFont("resources/fonts/deud.ttf", 102)); //3 (Death Screen)
    }

    public static Font getFont(int id) {
        return deudFonts.get(id);
    }
}
