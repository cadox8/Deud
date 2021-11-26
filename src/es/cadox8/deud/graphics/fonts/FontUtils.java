package es.cadox8.deud.graphics.fonts;

import lombok.Getter;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class FontUtils {

    @Getter private static ArrayList<Font> deudFonts;

    public static void init() {
        deudFonts = new ArrayList<>();

        Arrays.asList(Fonts.values()).forEach(f -> {
            deudFonts.add(null);
            deudFonts.set(f.getId(), loadFont(f.getFont(), f.getSize()));
        });
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
