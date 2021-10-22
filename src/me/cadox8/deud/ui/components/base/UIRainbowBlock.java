package me.cadox8.deud.ui.components.base;

import me.cadox8.deud.api.GameAPI;
import me.cadox8.deud.ui.helpers.AarinColor;
import me.cadox8.deud.ui.helpers.AarinUtils;

import java.util.Arrays;
import java.util.List;

public class UIRainbowBlock extends UIBlock {

    private final float speed;
    private long lastTime, timer = 0;

    private final List<AarinColor> colors;
    private int alpha;

    public UIRainbowBlock(GameAPI gameAPI, float speedInSeconds) {
        super(gameAPI);
        this.speed = 1000 * speedInSeconds;
        this.colors = Arrays.asList(AarinColor.allColors());
    }

    @Override
    public void tick() {
        timer += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();

        if (timer > speed) {
            timer = 0;
            this.setColor(finalColor(AarinUtils.randomColor(colors)));
        }
    }

    private AarinColor finalColor(AarinColor color) {
        return color.transparent(alpha);
    }
}
