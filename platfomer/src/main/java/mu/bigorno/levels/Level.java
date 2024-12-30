package mu.bigorno.levels;

public record Level(int[][] levelData) {

    public int getSpriteIndex(int x, int y) {
        return levelData[y][x];
    }
}
