package me.cadox8.deud.utils;

import me.cadox8.deud.entities.Entity;
import me.cadox8.deud.entities.Location;
import me.cadox8.deud.entities.enums.Direction;
import me.cadox8.deud.entities.statics.Light;
import me.cadox8.deud.tiles.Tile;
import me.cadox8.deud.worlds.World;

import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Utils {

    public static String loadFileAsString(String path) {
        StringBuilder builder = new StringBuilder();

        try {
            final BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            while ((line = br.readLine()) != null) builder.append(line + "\n");

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return builder.toString();
    }

    public static BufferedImage loadTextures(String path) {
        return loadImage("/textures/gui/" + path);
    }

    public static BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(Objects.requireNonNull(Utils.class.getResource(path)));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }

    public static int parseInt(String number) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static int directionToDegrees(Direction direction) {
        switch (direction) {
            case SOUTH:
                return 90;
            case NORTH:
                return 270;
            case EAST:
                return 180;

            default:
                return 0;
        }
    }

    public static double round(int places, double value) {
        return new BigDecimal(value).setScale(places, RoundingMode.HALF_UP).doubleValue();
    }

    public static BufferedImage rotateImage(double degrees, BufferedImage texture) {
        final AffineTransform tx = new AffineTransform();

        tx.translate(texture.getHeight() / 2, texture.getWidth() / 2);
        tx.rotate(Math.toRadians(degrees));
        tx.translate(-texture.getWidth() / 2, -texture.getHeight() / 2);

        return new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR).filter(texture, new BufferedImage(texture.getHeight(), texture.getWidth(), texture.getType()));
    }

    public static int[] locationToTile(float x, float y) {
        final int[] tiles = new int[2];
        tiles[0] = (int) (x / Tile.TILEWIDTH);
        tiles[1] = (int) (y / Tile.TILEHEIGHT);
        return tiles;
    }

    public static float tileToLocation(int i) {
        return i * Tile.TILEWIDTH;
    }

    public static List<Entity> getNearbyEntities(Location center, double radius) {
        final double radius2 = radius + Tile.TILEHEIGHT;
        final World world = center.getWorld();
        return world.getEntityManager().getEntities().stream().filter(e -> !(e instanceof Light)).filter(e -> center.distance(e.getLocation()) <= radius2 && center.distance(e.getLocation()) != 0).collect(Collectors.toCollection(ArrayList::new));
    }
}
