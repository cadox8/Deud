package me.cadox8.deud.gfx.textures;

import me.cadox8.deud.gfx.Sprites;
import me.cadox8.deud.utils.Utils;

import java.awt.image.BufferedImage;
import java.util.Random;

public class Models {

    private static final int width = 32, height = 32;

    private static Sprites model;

    public static BufferedImage[] player_down, player_up, player_left, player_right;

    public static BufferedImage[] fairy_down, fairy_up, fairy_left, fairy_right;

    public static BufferedImage[] zombie_down, zombie_up, zombie_left, zombie_right;
    public static BufferedImage[] ghost_down, ghost_up, ghost_left, ghost_right;

    //Direction: 0 = South, 1 = North, 2 = East, 3 = West

    public static void init() {
        //Player
        model = new Sprites(Utils.loadImage("/textures/models/" + randomGender() + ".png"));

        player_down = new BufferedImage[3];
        player_up = new BufferedImage[3];
        player_right = new BufferedImage[3];
        player_left = new BufferedImage[3];

        setImage(model, player_down, 0);
        setImage(model, player_up, 1);
        setImage(model, player_right, 2);
        setImage(model, player_left, 3);

        //Fairy
        model = new Sprites(Utils.loadImage("/textures/models/fairy.png"));

        fairy_down = new BufferedImage[3];
        fairy_up = new BufferedImage[3];
        fairy_right = new BufferedImage[3];
        fairy_left = new BufferedImage[3];

        setImage(model, fairy_down, 0);
        setImage(model, fairy_up, 1);
        setImage(model, fairy_right, 2);
        setImage(model, fairy_left, 3);

        //Zombie
        model = new Sprites(Utils.loadImage("/textures/models/zombie.png"));

        zombie_down = new BufferedImage[3];
        zombie_up = new BufferedImage[3];
        zombie_right = new BufferedImage[3];
        zombie_left = new BufferedImage[3];

        setImage(model, zombie_down, 0);
        setImage(model, zombie_up, 1);
        setImage(model, zombie_right, 2);
        setImage(model, zombie_left, 3);

        //Ghost
        model = new Sprites(Utils.loadImage("/textures/models/ghost.png"));

        ghost_down = new BufferedImage[3];
        ghost_up = new BufferedImage[3];
        ghost_right = new BufferedImage[3];
        ghost_left = new BufferedImage[3];

        setImage(model, ghost_down, 0);
        setImage(model, ghost_up, 1);
        setImage(model, ghost_right, 2);
        setImage(model, ghost_left, 3);
    }

    private static String randomGender() {
        switch (new Random().nextInt(2)) {
            case 0:
                return "male";
            case 1:
                return "female";
            default:
                return randomGender();
        }
    }

    private static void setImage(Sprites model, BufferedImage[] image, int direction) {
        switch (direction) {
            case 0:
                image[0] = model.crop(width, 0, width, height);
                image[1] = model.crop(0, 0, width, height);
                image[2] = model.crop(width * 2, 0, width, height);
                return;
            case 1:
                image[0] = model.crop(width, height * 3, width, height);
                image[1] = model.crop(0, height * 3, width, height);
                image[2] = model.crop(width * 2, height * 3, width, height);
                return;
            case 2:
                image[0] = model.crop(width, height * 2, width, height);
                image[1] = model.crop(0, height * 2, width, height);
                image[2] = model.crop(width * 2, height * 2, width, height);
                return;
            case 3:
                image[0] = model.crop(width, height, width, height);
                image[1] = model.crop(0, height, width, height);
                image[2] = model.crop(width * 2, height, width, height);
                return;
            default:
                break;
        }
    }
}
