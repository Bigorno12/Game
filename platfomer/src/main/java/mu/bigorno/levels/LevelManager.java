package mu.bigorno.levels;

import mu.bigorno.game.Platform;
import mu.bigorno.utils.ReadFile;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.function.Function;
import java.util.stream.IntStream;

import static mu.bigorno.enumeration.Paths.LEVEL;
import static mu.bigorno.enumeration.Paths.LEVEL_ONE;
import static mu.bigorno.game.Panel.TILES_IN_HEIGHT;
import static mu.bigorno.game.Panel.TILES_IN_WIDTH;
import static mu.bigorno.game.Panel.TILES_SIZE;

public record LevelManager(Platform platform, Level levelOne, BufferedImage[] levelSprite) {

    private static final int LEVEL_RECTANGLE_WIDTH = 32;

    public LevelManager(Platform platform) {
        this(platform, new Level(importLevelData()), importOutsideSprites());
    }

    private static int[][] importLevelData() {
        BufferedImage image = ReadFile.importImage(LEVEL_ONE.getPath());
        return IntStream.range(0, image.getHeight())
                .mapToObj(j -> IntStream.range(0, image.getWidth())
                        .map(i -> {
                            Color color = new Color(image.getRGB(i, j));
                            int value = color.getRed();
                            return value >= 48 ? 0 : value;
                        })
                        .toArray())
                .toArray(int[][]::new);
    }

    private static BufferedImage[] importOutsideSprites() {
        BufferedImage bufferedImage = ReadFile.importImage(LEVEL.getPath());
        return IntStream.range(0, 4)
                .mapToObj(j -> IntStream.range(0, 12)
                        .mapToObj(i -> bufferedImage.getSubimage(i * LEVEL_RECTANGLE_WIDTH, j * LEVEL_RECTANGLE_WIDTH, LEVEL_RECTANGLE_WIDTH, LEVEL_RECTANGLE_WIDTH))
                )
                .flatMap(Function.identity())
                .toArray(BufferedImage[]::new);
    }

    public void draw(Graphics g) {
        IntStream.range(0, TILES_IN_HEIGHT)
                .forEach(j -> IntStream.range(0, TILES_IN_WIDTH)
                        .forEach(i -> {
                            int index = levelOne.getSpriteIndex(i, j);
                            g.drawImage(levelSprite[index], TILES_SIZE * i, TILES_SIZE * j, TILES_SIZE, TILES_SIZE, null);
                        }));
    }
}