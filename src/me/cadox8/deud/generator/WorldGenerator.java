package me.cadox8.deud.generator;

import me.cadox8.deud.display.Display;
import me.cadox8.deud.states.State;
import me.cadox8.deud.tiles.Tile;
import me.cadox8.deud.tiles.Tiles;
import me.cadox8.deud.utils.Log;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class WorldGenerator {

    private final Display display = new Display("World Generator", (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(), (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight(), false);
    private final AtomicInteger indexX = new AtomicInteger(0);
    private final AtomicInteger indexY = new AtomicInteger(0);

    public WorldGenerator() {
        while (true) {
            render();
        }
    }

    private void render() {
        final BufferStrategy bs = display.getCanvas().getBufferStrategy();
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        final Graphics g = bs.getDrawGraphics();
        g.clearRect(0, 0, 1000, 800);

        Arrays.asList(Tiles.values()).forEach(t -> {
            g.drawImage(t.build().getTexture(), indexX.getAndIncrement() * Tile.TILEHEIGHT, indexY.get() * Tile.TILEHEIGHT, Tile.TILEHEIGHT, Tile.TILEWIDTH, null);

            if (indexX.get() > 40) {
                indexX.set(0);
                indexY.incrementAndGet();
            }
        });

        bs.show();
        g.dispose();
    }
}
