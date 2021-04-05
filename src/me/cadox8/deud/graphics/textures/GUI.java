package me.cadox8.deud.graphics.textures;

import me.cadox8.deud.graphics.Sprite;
import me.cadox8.deud.utils.Utils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GUI {

    // Menu
    public static BufferedImage[] play;
    public static BufferedImage[] exit;
    public static BufferedImage background;

    public static BufferedImage logo;

    public static BufferedImage[] buttons;

    // Dialog
    public static BufferedImage dialog;

    // Hud
    public static BufferedImage hud;
    public static BufferedImage hud2;

    // Inventory
    public static BufferedImage inventory, invSelector, chest;

    // Editor
    public static BufferedImage backgroundEditor, selectorEditor, editorGUI;

    // Utils
    public static BufferedImage none;

    // Pointers
    public static BufferedImage pointer;

    public static void init() {
        Sprite gui = new Sprite(Utils.loadImage("/textures/gui/gui.png"));

        // Menu
        play = new BufferedImage[2];
        play[0] = gui.crop(0, 0, 93, 35);
        play[1] = gui.crop(0, 35, 93, 35);

        exit = new BufferedImage[2];
        exit[0] = gui.crop(94, 0, 89, 33);
        exit[1] = gui.crop(94, 39, 89, 32);

        buttons = new BufferedImage[2];
        buttons[0] = gui.crop(0, 71, 90, 16);
        buttons[1] = gui.crop(92, 71, 90, 16);

        background = Utils.loadImage("/textures/utils/menu.png");

        logo = Utils.loadImage("/textures/utils/icon.png");
        dialog = Utils.loadTextures("dialog.png");

        hud = Utils.loadTextures("hud.png");
        hud2 = Utils.loadTextures("hud2.png");

        // Inventory
        chest = Utils.loadTextures("chest.png");

        inventory = Utils.loadTextures("inventory.png");
        invSelector = Utils.loadTextures("invSelector.png");

        // Editor
        backgroundEditor = Utils.loadTextures("editor/background.png");
        selectorEditor = Utils.loadTextures("editor/selector.png");
        editorGUI = Utils.loadTextures("editor/editor.png");

        // Utils
        none = Sprite.coloredSprite(32, 32, new Color(0, 0, 0, 255));

        //
        gui = new Sprite(Utils.loadImage("/textures/gui/pointers.png"));
        pointer = gui.crop(1, 0, 30, 30);
    }
}
