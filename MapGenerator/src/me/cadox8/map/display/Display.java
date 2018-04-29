package me.cadox8.map.display;

import lombok.Getter;

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
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(width, height));
        canvas.setFocusable(false);

        JTextField t = new JTextField();
        t.setPreferredSize(new Dimension(20, 20));
        canvas.setMaximumSize(new Dimension(20, 20));
        canvas.setMinimumSize(new Dimension(20, 20));

        frame.add(canvas);
        frame.pack();
    }
}
