package mu.bigorno.game;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static java.util.concurrent.TimeUnit.NANOSECONDS;

public class Platform {

    private static final int FPS = 120;
    private static final int UPS_SET = 200;

    private final Window window;
    private final Panel panel;
    private final ScheduledExecutorService executorService;

    public Platform() {
        this.panel = new Panel();
        this.window = new Window(panel);
        this.executorService = Executors.newScheduledThreadPool(2);
        initializePlatform();
    }

    private void initializePlatform() {
        panel.requestFocus();
        startGameLoop();
    }

    private void startGameLoop() {
        long framePeriod = 1000000000L / FPS;
        long updatePeriod = 1000000000L / UPS_SET;

        executorService.scheduleAtFixedRate(this::update, 0, updatePeriod, NANOSECONDS);
        executorService.scheduleAtFixedRate(panel::repaint, 0, framePeriod, NANOSECONDS);
    }

    public void update() {
        panel.updateGame();
    }

}