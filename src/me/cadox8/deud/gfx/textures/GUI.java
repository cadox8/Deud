package me.cadox8.deud.gfx.textures;

import me.cadox8.deud.gfx.Sprites;
import me.cadox8.deud.utils.Utils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GUI {

    public static BufferedImage[] play;
    public static BufferedImage[] exit;
    public static BufferedImage[] background;

    public static BufferedImage[] buttons;

    public static BufferedImage dialog;

    public static BufferedImage inventory, invSelector, chest;

    public static BufferedImage logo;

    public static BufferedImage none;

    public static void init() {
        Sprites gui = new Sprites(Utils.loadImage("/textures/gui/gui.png"));

        play = new BufferedImage[2];
        play[0] = gui.crop(0, 0, 93, 35);
        play[1] = gui.crop(0, 35, 93, 35);

        exit = new BufferedImage[2];
        exit[0] = gui.crop(94, 0, 89, 33);
        exit[1] = gui.crop(94, 39, 89, 32);

        buttons = new BufferedImage[2];
        buttons[0] = gui.crop(0, 71, 90, 16);
        buttons[1] = gui.crop(92, 71, 90, 16);

        background = new BufferedImage[1];
        background[0] = Utils.loadImage("/utils/menu.png");

        logo = Utils.loadImage("/utils/icon.png");
        dialog = Utils.loadImage("/textures/gui/dialog.png");

        chest = Utils.loadImage("/textures/gui/chest.png");

        inventory = Utils.loadImage("/textures/gui/inventory.png");
        invSelector = Utils.loadImage("/textures/gui/invSelector.png");

        none = gui.coloredSprite(32, 32, new Color(0, 0, 0, 255));
    }
}
