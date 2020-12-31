package me.cadox8.deud.display;

import lombok.Getter;
import me.cadox8.deud.utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Display {

    @Getter private JFrame frame;
    @Getter private Canvas canvas;

    @Getter private final Toolkit toolkit;

    private final String title;
    private final int width;
    private final int height;

    private final boolean fullScreen;

    public Display(String title, int width, int height, boolean fullScreen) {
        this.title = title;
        this.width = width;
        this.height = height;
        this.fullScreen = fullScreen;

        this.toolkit = Toolkit.getDefaultToolkit();

        createDisplay();
    }

    private void createDisplay() {
        frame = new JFrame(title);
        frame.setSize(new Dimension(width, height));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setUndecorated(fullScreen);
        frame.setVisible(true);

        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(width, height));
        canvas.setFocusable(false);

        frame.setIconImage(Utils.loadImage("/textures/utils/icon.png"));

        frame.add(canvas);
        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    public void changeCursor(BufferedImage image) {
        final Cursor c = toolkit.createCustomCursor(image , new Point(frame.getX(), frame.getY()), "deud");
        frame.setCursor(c);
    }
}
