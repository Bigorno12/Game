package mu.bigorno.utils;

import lombok.experimental.UtilityClass;
import mu.bigorno.exception.ImageException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

@UtilityClass
public class ReadFile {

    private static final int RECTANGLE_WIDTH = 64;
    private static final int RECTANGLE_HEIGHT = 40;

    public static BufferedImage importSpecificImageRegion(String path, int x, int y) {
        try {
            return ImageIO.read(new File(path))
                    .getSubimage(x, y, RECTANGLE_WIDTH, RECTANGLE_HEIGHT);
        } catch (Exception e) {
            throw new ImageException("Error while importing image");
        }
    }

    public static BufferedImage importImage(String path) {
        try {
            return ImageIO.read(new File(path));
        } catch (Exception e) {
            throw new ImageException("Error while importing image");
        }
    }
}
