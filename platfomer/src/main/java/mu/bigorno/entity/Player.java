package mu.bigorno.entity;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.stream.IntStream;

import static mu.bigorno.enumeration.Paths.CHARACTER;
import static mu.bigorno.utils.PlayerConstants.ATTACK_1;
import static mu.bigorno.utils.PlayerConstants.IDLE;
import static mu.bigorno.utils.PlayerConstants.RUNNING;
import static mu.bigorno.utils.PlayerConstants.sprintingAmount;
import static mu.bigorno.utils.ReadFile.importSpecificImageRegion;

@Setter
@Getter
public class Player extends Pair {

    private static final int RECTANGLE_X = 64;
    private static final int RECTANGLE_Y = 40;

    private boolean left;
    private boolean up;
    private boolean right;
    private boolean down;

    private float playerSpeed = 2.0f;
    private boolean moving = false;
    private boolean attacking = false;
    private int playerAction = IDLE;
    private int aniTick, aniIndex, aniSpeed = 25;

    private BufferedImage[][] animations;

    public Player(float x, float y, int width, int height) {
        super(x, y, width, height);
        loadAnimations();
    }

    public void renderGraphic(Graphics graphics) {
        graphics.drawImage(animations[playerAction][aniIndex], (int) getX(), (int) getY(), width, height, null);
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

        if (left && !right) {
            x -= playerSpeed;
            moving = true;
        } else if (right && !left) {
            x += playerSpeed;
            moving = true;
        }

        if (up && !down) {
            y -= playerSpeed;
            moving = true;
        } else if (down && !up) {
            y += playerSpeed;
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
}
