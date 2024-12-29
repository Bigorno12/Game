package mu.bigorno.game;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import mu.bigorno.inputs.Keyboard;
import mu.bigorno.inputs.Mouse;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.stream.IntStream;

import static mu.bigorno.enumeration.Player.IDLE;
import static mu.bigorno.enumeration.Player.RUNNING;
import static mu.bigorno.utils.Directions.DOWN;
import static mu.bigorno.utils.Directions.LEFT;
import static mu.bigorno.utils.Directions.RIGHT;
import static mu.bigorno.utils.Directions.UP;
import static mu.bigorno.utils.ReadFile.importSpecificImageRegion;

@Slf4j
public class Panel extends JPanel {

    private static final int WIDTH = 128;
    private static final int HEIGHT = 80;
    private static final int DIMENSION_WIDTH = 1280;
    private static final int DIMENSION_HEIGHT = 800;
    private static final int RECTANGLE_X = 64;
    private static final int RECTANGLE_Y = 40;
    private static final String path = "C:/Workspace/game/platfomer/src/main/resources/picture/player_sprites.png";

    private float xDelta = 100;
    private float yDelta = 100;

    @Setter
    private boolean moving = false;

    private BufferedImage[][] animations;
    private int playerDirection = -1;
    private int playerAction = IDLE.getValue();
    private int animationTick, animationSpeed, animationIndex = 15;


    public Panel() {
        Mouse mouse = new Mouse(this);
        loadAnimation();

        setPanelSize();
        addKeyListener(new Keyboard(this));
        addMouseListener(mouse);
        addMouseMotionListener(mouse);
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        updateAnimation();
        setAnimation();
        updatePost();

        graphics.drawImage(animations[playerAction][animationIndex], (int) xDelta, (int) yDelta, WIDTH, HEIGHT, null);
    }

    public void setRectPos(int x, int y) {
        this.xDelta = x;
        this.yDelta = y;
    }

    public void setDirection(int direction) {
        this.playerDirection = direction;
        this.moving = true;
    }

    private void setPanelSize() {
        Dimension dimension = new Dimension(DIMENSION_WIDTH, DIMENSION_HEIGHT);
        setPreferredSize(dimension);
    }

    private void loadAnimation() {
        animations = IntStream.range(0, 9)
                .mapToObj(j -> IntStream.range(0, 6)
                        .mapToObj(i -> importSpecificImageRegion(path, i * RECTANGLE_X, j * RECTANGLE_Y))
                        .toArray(BufferedImage[]::new))
                .toArray(BufferedImage[][]::new);
    }

    private void setAnimation() {
        if (moving) {
            playerAction = RUNNING.getValue();
        } else {
            playerAction = IDLE.getValue();
        }
    }

    private void updateAnimation() {
        animationTick++;
        if (animationTick >= animationSpeed) {
            animationTick = 0;
            animationIndex++;
            if (animationIndex >= animations[playerAction].length) {
                animationIndex = 0;
            }
        }
    }

    private void updatePost() {
        if (moving) {
            switch (playerDirection) {
                case LEFT -> xDelta -= 5;
                case UP -> yDelta -= 5;
                case RIGHT -> xDelta += 5;
                case DOWN -> yDelta += 5;
            }
        }
    }

}
