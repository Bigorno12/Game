package mu.bigorno.game;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import mu.bigorno.entity.Player;
import mu.bigorno.inputs.Keyboard;
import mu.bigorno.inputs.Mouse;

import javax.swing.*;
import java.awt.*;
import java.util.stream.IntStream;

@Slf4j
@Getter
public class Panel extends JPanel {

    private static final int WIDTH = 20;
    private static final int HEIGHT = 20;
    private static final int DIMENSION_WIDTH = 1280;
    private static final int DIMENSION_HEIGHT = 800;
    private static final int RECTANGLE_X = 64;
    private static final int RECTANGLE_Y = 40;

    private final Player player;

    public Panel(Player player) {
        this.player = player;
        Mouse mouse = new Mouse(this);

        setPanelSize();
        addKeyListener(new Keyboard(this));
        addMouseListener(mouse);
        addMouseMotionListener(mouse);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        graphics.setColor(Color.white);
        IntStream.range(0, RECTANGLE_X)
                .forEach(i -> IntStream.range(0, RECTANGLE_Y)
                        .forEach(j -> graphics.fillRect(i * WIDTH, j * HEIGHT, WIDTH, HEIGHT))
                );
        player.renderGraphic(graphics);
    }

    private void setPanelSize() {
        Dimension size = new Dimension(DIMENSION_WIDTH, DIMENSION_HEIGHT);
        setPreferredSize(size);
    }
}