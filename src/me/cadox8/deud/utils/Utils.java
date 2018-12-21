package me.cadox8.deud.utils;

import me.cadox8.deud.entities.Entity;
import me.cadox8.deud.entities.Location;
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

    public static BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(Utils.class.getResource(path));
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

    public static ArrayList<Entity> getNearbyEntities(Location center, double radius, int amount) {
        World world = center.getWorld();
        double increment = (2 * Math.PI) / amount;
        ArrayList<Entity> entities = new ArrayList<>();

        for (int i = 0; i < amount; i++) {
            double angle = i * increment;
            double x = center.getX() + (radius * Math.cos(angle));
            double y = center.getY() + (radius * Math.sin(angle));

/*            world.getEntityManager().getEntities().stream().filter(e -> e instanceof Creature).forEach(e -> System.out.println("Entity: " + e.getLocation().toString()));
            System.out.println(new Location(world, (float) x, (float) y).toString());*/

            //entities.add(world.getEntityManager().getEntities().stream().filter(e -> e.getLocation().equals(new Location(world, (float) x, (float) y))).findFirst().get());
        }
        return entities;
    }
}
