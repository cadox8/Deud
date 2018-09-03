package me.cadox8.deud.gfx.textures;

import me.cadox8.deud.gfx.Sprites;
import me.cadox8.deud.utils.Utils;

import java.awt.image.BufferedImage;

public class Assets {

    public static final int WIDTH = 32, HEIGHT = 32;
    private static Sprites sprites;

    //Tiles
    public static BufferedImage dirt, grass, road, sand, road2, brick, door, door2, sign, sign2;

    //Static Entities
    public static BufferedImage stone, tree, tree2;

    //Items
    public static BufferedImage bug;
    public static BufferedImage wood, mp3,  sushi, chest;
    public static BufferedImage key, save;

    public static BufferedImage food, hearth, shield, xp, coin, sword;

    public static BufferedImage fire;

    // Particles
    public static BufferedImage[] explosion;

    public static void init() {
        sprites = new Sprites(Utils.loadImage("/textures/sprites/basic.png"));

        dirt = getImage(0, 0);
        grass = getImage(1, 0);
        road = getImage(2, 0);
        sand = getImage(3, 0);
        road2 = getImage(4, 0);
        brick = getImage(5, 0);
        door = getImage(9, 0);
        door2 = getImage(9, 1);
        sign = getImage(0, 1);
        sign2 = getImage(1, 1);

        tree = getImage(7, 0);
        tree2 = getImage(8, 0);
        stone = getImage(6, 0);

        sprites = new Sprites(Utils.loadImage("/textures/sprites/items.png"));

        wood = getImage(0, 0);
        mp3 = getImage(5, 0);
        chest = getImage(6, 0);
        sushi = getImage(9, 0);

        fire = getImage(7, 0);

        bug = sprites.randomImage(WIDTH, HEIGHT);

        save = getImage(2, 0);
        key = getImage(3, 0);

        xp = getImage(4, 0);
        sword = getImage(2, 1);
        food = getImage(1, 1);
        hearth = getImage(0, 1);
        shield = getImage(1, 0);
        coin = getImage(8, 0);

        sprites = new Sprites(Utils.loadImage("/textures/sprites/exp.png"));
        explosion = getParticles(96, 96, 7);
    }

    private static BufferedImage getImage(int x, int y) {
        return getImage(x, y, WIDTH, HEIGHT);
    }

    private static BufferedImage getImage(int x, int y, int width, int height) {
        return sprites.crop(width * x, height * y, width, height);
    }

    private static BufferedImage[] getParticles(int width, int height, int image) {
        BufferedImage[] images = new BufferedImage[image];

        for (int i = 0; i < image; i++) images[i] = getImage(i, 0, width, height);
        return images;
    }
}
