package mu.bigorno.game;

import lombok.extern.slf4j.Slf4j;
import mu.bigorno.exception.ImageException;
import mu.bigorno.inputs.Keyboard;
import mu.bigorno.inputs.Mouse;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

@Slf4j
public class Panel extends JPanel {

    private static final int WIDTH = 128;
    private static final int HEIGHT = 80;
    private static final int DIMENSION_WIDTH = 1280;
    private static final int DIMENSION_HEIGHT = 800;
    private static final int RECTANGLE_WIDTH = 64;
    private static final int RECTANGLE_HEIGHT = 40;
    private static final int RECTANGLE_X = 64;
    private static final int RECTANGLE_Y = 8 * 40;

    private float xDelta = 100;
    private float yDelta = 100;


    public Panel() {
        Mouse mouse = new Mouse(this);
        setPanelSize();
        addKeyListener(new Keyboard(this));
        addMouseListener(mouse);
        addMouseMotionListener(mouse);
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.drawImage(importSpecificImageRegion(), (int) xDelta, (int) yDelta, WIDTH, HEIGHT, null);
    }

    public void changeXDelta(int xDelta) {
        this.xDelta += xDelta;
    }

    public void changeYDelta(int yDelta) {
        this.yDelta += yDelta;
    }

    public void setRectPos(int x, int y) {
        this.xDelta = x;
        this.yDelta = y;
    }

    private void setPanelSize() {
        Dimension dimension = new Dimension(DIMENSION_WIDTH, DIMENSION_HEIGHT);
        setMaximumSize(dimension);
        setPreferredSize(dimension);
        setMaximumSize(dimension);
    }

    private BufferedImage importSpecificImageRegion() {
        try {
            return ImageIO.read(new File("C:/Workspace/game/platfomer/src/main/resources/picture/player_sprites.png"))
                    .getSubimage(RECTANGLE_X, RECTANGLE_Y, RECTANGLE_WIDTH, RECTANGLE_HEIGHT);
        } catch (Exception e) {
            throw new ImageException("Error while importing image");
        }
    }


}
