package mu.bigorno.utils;

import lombok.experimental.UtilityClass;

import static mu.bigorno.game.Panel.GAME_HEIGHT;
import static mu.bigorno.game.Panel.GAME_WIDTH;
import static mu.bigorno.game.Panel.TILES_SIZE;

@UtilityClass
public class HelpMethod {

    public static boolean canMoveHere(float x, float y, float width, float height, int[][] lvlData) {
        return isSolid(x, y, lvlData) && isSolid(x + width, y + height, lvlData)
                && isSolid(x + width, y, lvlData) && isSolid(x, y + height, lvlData);
    }

    private static boolean isSolid(float x, float y, int[][] lvlData) {
        if (x < 0 || x >= GAME_WIDTH || y < 0 || y >= GAME_HEIGHT) {
            return true;
        }

        int xIndex = (int) (x / TILES_SIZE);
        int yIndex = (int) (y / TILES_SIZE);

        return lvlData[yIndex][xIndex] == 11;

    }
}