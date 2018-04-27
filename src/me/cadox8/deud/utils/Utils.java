package me.cadox8.deud.utils;

import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;

public class Utils {

    public static String loadFileAsString(String path) {
        StringBuilder builder = new StringBuilder();

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            while ((line = br.readLine()) != null) {
                builder.append(line + "\n");
            }

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
        return new BigDecimal(value).setScale(places, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static BufferedImage rotateImage(double degrees, BufferedImage texture) {
        AffineTransform tx = new AffineTransform();

        tx.translate(texture.getHeight() / 2, texture.getWidth() / 2);
        tx.rotate(Math.toRadians(degrees));
        tx.translate(-texture.getWidth() / 2, -texture.getHeight() / 2);

        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

        BufferedImage newImage = new BufferedImage(texture.getHeight(), texture.getWidth(), texture.getType());

        return op.filter(texture, newImage);
    }
}
