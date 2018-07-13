package me.cadox8.deud.display;

import lombok.Getter;
import me.cadox8.deud.utils.Utils;

import javax.swing.*;
import java.awt.*;

public class Display {

    @Getter private JFrame frame;
    @Getter private Canvas canvas;

    private String title;
    private int width, height;

    public Display(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;

        createDisplay();
    }

    private void createDisplay() {
        frame = new JFrame(title);
        //frame.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        //frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);

        canvas = new Canvas();
        canvas.setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
        canvas.setMaximumSize(Toolkit.getDefaultToolkit().getScreenSize());
        canvas.setMinimumSize(Toolkit.getDefaultToolkit().getScreenSize());
        canvas.setFocusable(false);

        frame.setIconImage(Utils.loadImage("/utils/icon.png")); //Icon

        frame.add(canvas);
        frame.pack();
    }
}
