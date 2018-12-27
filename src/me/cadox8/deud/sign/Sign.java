package me.cadox8.deud.sign;

import me.cadox8.deud.gfx.fonts.Text;
import me.cadox8.deud.gfx.textures.Assets;

import java.awt.*;
import java.util.List;


public class Sign {

    private final int signX = 64, signY = 48, signWidth = 512, signHeight = 384;

    private final int textX = signX + 45, textY = signY + 45;

    private final List<String> text;

    public Sign(List<String> text) {
        this.text = text;
    }


    public void render(Graphics g) {
        g.drawImage(Assets.sign, signX, signY, signWidth, signHeight, null);

        int p = 0;
        for (String s : text) {
            Text.drawString(g, s, textX, textY + (p * 20), 2);
            p++;
        }
    }
}
