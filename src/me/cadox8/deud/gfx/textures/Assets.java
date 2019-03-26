package me.cadox8.deud.gfx.textures;

import me.cadox8.deud.gfx.Sprites;
import me.cadox8.deud.utils.Utils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Assets {

    public static final int WIDTH = 32, HEIGHT = 32;
    private static Sprites sprites;

    //Tiles
    public static BufferedImage dirt, grass, grass_dirt1, grass_dirt2, sand, brick, door, door2, sign, sign2;
    public static BufferedImage wooden_path;

    //Static Entities
    public static BufferedImage stone, tree, tree2;

    //Items
    public static BufferedImage bug, voidImg;
    public static BufferedImage wood, mp3,  sushi, chest;
    public static BufferedImage key, save;

    public static BufferedImage food, hearth, shield, xp, coin, sword, hand;

    public static BufferedImage fire;

    public static BufferedImage[] post_Shop, house1, house2, house3;

    // Particles
    public static BufferedImage[] explosion;

    public static void init() {
        sprites = new Sprites(Utils.loadImage("/textures/sprites/terrain.png"));
        dirt = getImage(7, 1);
        grass = getImage(1, 11);
        grass_dirt1 = getImage(7, 0);
        grass_dirt2 = getImage(6, 0);
        wooden_path = getImage(10, 4);

        post_Shop = new BufferedImage[12];
        int y_shop = 0;
        int x_shop = 12;
        for (int i = 0; i < post_Shop.length; i++) {
            post_Shop[i] = getImage(x_shop, y_shop);
            x_shop += 1;
            if (i == 3 || i == 7) {
                x_shop = 12;
                y_shop += 1;
            }
        }

        sprites = new Sprites(Utils.loadImage("/textures/sprites/buildings.png"));

        house1 = getHouse(9, 0, 12);
        house2 = getHouse(12, 9, 12);

        door = getImage(5, 9);
        door2 = getImage(5, 10);

        sprites = new Sprites(Utils.loadImage("/textures/sprites/castle.png"));

        brick = getImage(1, 1);

        sprites = new Sprites(Utils.loadImage("/textures/sprites/basic.png"));

        sand = getImage(3, 0);
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
        voidImg = sprites.coloredSprite(WIDTH, HEIGHT, Color.BLACK);

        save = getImage(2, 0);
        key = getImage(3, 0);

        xp = getImage(4, 0);
        sword = getImage(2, 1);
        hand = getImage(3, 1);
        food = getImage(1, 1);
        hearth = getImage(0, 1);
        shield = getImage(1, 0);
        coin = getImage(8, 0);

        sprites = new Sprites(Utils.loadImage("/textures/sprites/particles.png"));
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

    private static BufferedImage[] getHouse(int size, int xStart, int yStart) {
        final int x = xStart;
        final BufferedImage[] textures = new BufferedImage[size];
        for (int i = 0; i < textures.length; i++) {
            textures[i] = getImage(xStart, yStart);
            xStart += 1;
            if (i == 2 || i == 5) {
                xStart = x;
                yStart += 1;
            }
        }
        return textures;
    }
}
