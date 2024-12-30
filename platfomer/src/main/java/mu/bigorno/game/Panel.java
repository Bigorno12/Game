package mu.bigorno.game;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import mu.bigorno.entity.Player;
import mu.bigorno.inputs.Keyboard;
import mu.bigorno.inputs.Mouse;
import mu.bigorno.levels.LevelManager;

import javax.swing.*;
import java.awt.*;

@Slf4j
@Getter
public class Panel extends JPanel {

    public final static float SCALE = 2f;
    public final static int TILES_IN_WIDTH = 26;
    public final static int TILES_IN_HEIGHT = 14;
    public final static int TILES_DEFAULT_SIZE = 32;
    public final static int TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE);
    public final static int GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
    public final static int GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;

    private final Player player;
    private final LevelManager levelManager;

    public Panel(Player player, LevelManager levelManager) {
        this.player = player;
        this.levelManager = levelManager;
        Mouse mouse = new Mouse(this);

        setPanelSize();
        addKeyListener(new Keyboard(this));
        addMouseListener(mouse);
        addMouseMotionListener(mouse);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        levelManager.draw(graphics);
        player.renderGraphic(graphics);
    }

    private void setPanelSize() {
        Dimension size = new Dimension(GAME_WIDTH, GAME_HEIGHT);
        setPreferredSize(size);
    }
}