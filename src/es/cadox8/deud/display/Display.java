package es.cadox8.deud.display;

import lombok.Getter;
import es.cadox8.deud.utils.Utils;

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

        this.createDisplay();
    }

    private void createDisplay() {
        this.frame = new JFrame(this.title);
        this.frame.setSize(new Dimension(this.width, this.height));
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.frame.setResizable(true);
        this.frame.setUndecorated(this.fullScreen);
        this.frame.setVisible(true);

        this.canvas = new Canvas();
        this.canvas.setPreferredSize(new Dimension(this.width, this.height));
        this.canvas.setMaximumSize(new Dimension(this.width, this.height));
        this.canvas.setMinimumSize(new Dimension(this.width, this.height));
        this.canvas.setFocusable(false);

        this.frame.setIconImage(Utils.loadImage("/textures/utils/icon.png"));

        this.frame.add(this.canvas);
        this.frame.pack();
        this.frame.setLocationRelativeTo(null);
    }

    public void changeCursor(BufferedImage image) {
        final Cursor c = this.toolkit.createCustomCursor(image , new Point(frame.getX(), frame.getY()), "deud");
        this.frame.setCursor(c);
    }
}
