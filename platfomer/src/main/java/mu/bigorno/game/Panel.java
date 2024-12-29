package mu.bigorno.game;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import mu.bigorno.inputs.Keyboard;
import mu.bigorno.inputs.Mouse;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.stream.IntStream;

import static mu.bigorno.utils.Directions.*;
import static mu.bigorno.utils.PlayerConstants.*;
import static mu.bigorno.utils.ReadFile.importSpecificImageRegion;

@Slf4j
public class Panel extends JPanel {

    private static final int WIDTH = 256;
    private static final int HEIGHT = 160;
    private static final int DIMENSION_WIDTH = 1280;
    private static final int DIMENSION_HEIGHT = 800;
    private static final int RECTANGLE_X = 64;
    private static final int RECTANGLE_Y = 40;
    private static final String PATH = "C:/Workspace/game/platfomer/src/main/resources/picture/player_sprites.png";

    private float xDelta = 100;
    private float yDelta = 100;

    @Setter
    private boolean moving = false;

    private BufferedImage[][] animations;
    private int playerDir = -1;
    private int playerAction = IDLE;
    private int aniTick, aniIndex, aniSpeed = 25;

    public Panel() {
        initializePanel();
    }

    private void initializePanel() {
        Mouse mouse = new Mouse(this);
        loadAnimations();
        setPanelSize();
        addKeyListener(new Keyboard(this));
        addMouseListener(mouse);
        addMouseMotionListener(mouse);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.drawImage(animations[playerAction][aniIndex], (int) xDelta, (int) yDelta, WIDTH, HEIGHT, null);
    }

    public void updateGame() {
        updateAnimation();
        setAnimation();
        updatePosition();
    }

    public void setDirection(int direction) {
        this.playerDir = direction;
        moving = true;
    }

    private void setPanelSize() {
        Dimension dimension = new Dimension(DIMENSION_WIDTH, DIMENSION_HEIGHT);
        setPreferredSize(dimension);
    }

    private void loadAnimations() {
        animations = IntStream.range(0, 9)
                .mapToObj(j -> IntStream.range(0, 6)
                        .mapToObj(i -> importSpecificImageRegion(PATH, i * RECTANGLE_X, j * RECTANGLE_Y))
                        .toArray(BufferedImage[]::new))
                .toArray(BufferedImage[][]::new);
    }

    private void setAnimation() {
        playerAction = moving ? RUNNING : IDLE;
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

    private void updatePosition() {
        if (moving) {
            xDelta += switch (playerDir) {
                case LEFT -> -5;
                case RIGHT -> 5;
                default -> 0;
            };
            yDelta += switch (playerDir) {
                case UP -> -5;
                case DOWN -> 5;
                default -> 0;
            };
        }
    }
}