package es.cadox8.deud.ui.helpers;

import lombok.RequiredArgsConstructor;

import java.awt.*;

@RequiredArgsConstructor
public enum UiColor {

    DARK_GRAY(52, 73, 94),
    PURPLE(142, 68, 173),
    BLUE(41, 128, 185),
    GREEN(39, 174, 96),
    TURQUOISE(22, 160, 133),
    WHITE(236, 240, 241),
    BLACK(0, 0 ,0),
    RED(192, 57, 43),
    ORANGE(211, 84, 0),
    YELLOW(241, 196, 15);

    private final int red;
    private final int green;
    private final int blue;


    public Color TRANSPARENT() {
        return new Color(0, 0 ,0, 0);
    }

    public Color color() {
        return this.color(0);
    }

    public Color color(int alpha) {
        return new Color(this.red, this.green, this.blue, alpha);
    }
}
