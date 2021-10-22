package me.cadox8.deud.graphics.fonts;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.awt.*;

@RequiredArgsConstructor
@Getter
public enum Fonts {

    DEUD(0, "Deud", 14),
    DEUD_TALL(1, "DeudTall", 14),

    DEUD_DEATH_SCREEN(2, DEUD_TALL.getFont(), 100);

    private final int id;
    private final String font;
    private final int size;

    public Font font() {
        return FontUtils.getDeudFonts().get(this.id);
    }
    public Font font(int size) {
        return FontUtils.getDeudFonts().get(this.id).deriveFont(size);
    }
}
