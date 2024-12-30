package mu.bigorno.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.stream.IntStream;

import static mu.bigorno.enumeration.Paths.CHARACTER;
import static mu.bigorno.game.Platform.SCALE;
import static mu.bigorno.utils.HelpMethod.canMoveHere;
import static mu.bigorno.utils.PlayerConstants.ATTACK_1;
import static mu.bigorno.utils.PlayerConstants.IDLE;
import static mu.bigorno.utils.PlayerConstants.RUNNING;
import static mu.bigorno.utils.PlayerConstants.sprintingAmount;
import static mu.bigorno.utils.ReadFile.importSpecificImageRegion;

@Slf4j
@Setter
@Getter
public class Player extends Character {

    private static final int RECTANGLE_X = 64;
    private static final int RECTANGLE_Y = 40;

    private boolean left;
    private boolean up;
    private boolean right;
    private boolean down;
    private boolean moving = false;
    private boolean attacking = false;

    private int aniTick, aniIndex, aniSpeed = 25;
    private int playerAction = IDLE;

    private float playerSpeed = 2.0f;
    private float yDrawOffset = 4 * SCALE;
    private float xDrawOffset = 21 * SCALE;

    private int[][] levelData;
    private BufferedImage[][] animations;

    public Player(float x, float y, int width, int height) {
        super(x, y, width, height);
        loadAnimations();
        super.initHitBox(x, y, 20 * SCALE, 28 * SCALE);
    }

    public void renderGraphic(Graphics graphics) {
        graphics.drawImage(animations[playerAction][aniIndex], (int) (hitBox.x - xDrawOffset), (int) (hitBox.y - yDrawOffset), width, height, null);
        super.drawHitBox(graphics);
    }

    public void updateGame() {
        updatePosition();
        updateAnimation();
        setAnimation();
    }

    public void resetDirection() {
        left = false;
        right = false;
        up = false;
        down = false;
    }

    private void loadAnimations() {
        animations = IntStream.range(0, 9)
                .mapToObj(j -> IntStream.range(0, 6)
                        .mapToObj(i -> importSpecificImageRegion(CHARACTER.getPath(), i * RECTANGLE_X, j * RECTANGLE_Y))
                        .toArray(BufferedImage[]::new))
                .toArray(BufferedImage[][]::new);
    }

    private void updatePosition() {
        moving = false;
        if (!left && !right && !up && !down)
            return;

        float xSpeed = 0, ySpeed = 0;

        if (left && !right)
            xSpeed = -playerSpeed;
        else if (right && !left)
            xSpeed = playerSpeed;

        if (up && !down)
            ySpeed = -playerSpeed;
        else if (down && !up)
            ySpeed = playerSpeed;

        if (canMoveHere(hitBox.x + xSpeed, hitBox.y + ySpeed, hitBox.width, hitBox.height, levelData)) {
            hitBox.x += xSpeed;
            hitBox.y += ySpeed;
            moving = true;
        }
    }

    private void updateAnimation() {
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= sprintingAmount(playerAction)) {
                aniIndex = 0;
            }
        }
    }

    private void setAnimation() {
        int startAnimation = playerAction;

        if (moving) {
            playerAction = RUNNING;
        } else {
            playerAction = IDLE;
        }

        if (attacking) {
            playerAction = ATTACK_1;
        }

        if (startAnimation != playerAction) {
            aniTick = 0;
            aniIndex = 0;
        }
    }

    public void loadLevelData(int[][] levelData) {
        this.levelData = levelData;
    }
}
