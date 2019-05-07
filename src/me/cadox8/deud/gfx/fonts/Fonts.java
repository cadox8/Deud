package me.cadox8.deud.gfx.fonts;

import java.awt.*;
import java.util.ArrayList;

public class Fonts {

    private static ArrayList<Font> deudFonts;

    public static void init() {
        deudFonts = new ArrayList<>();

        deudFonts.add(FontLoader.loadFont("resources/fonts/deud.ttf", 34)); //0
        deudFonts.add(FontLoader.loadFont("resources/fonts/deud.ttf", 14)); //1
        deudFonts.add(FontLoader.loadFont("resources/fonts/deud.ttf", 20)); //2
        deudFonts.add(FontLoader.loadFont("resources/fonts/deud.ttf", 108)); //3 (Death Screen)
    }

    public static Font getFont(int id) {
        return deudFonts.get(id);
    }
}
