package mu.bigorno.game;

import lombok.extern.slf4j.Slf4j;
import mu.bigorno.entity.Player;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@Slf4j
public class Platform {

    private static final int FPS = 120;
    private static final int UPS_SET = 200;

    private final Window window;
    private final Panel panel;
    private final Player player;

    public Platform() {
        this.player = new Player(200, 200);
        this.panel = new Panel(this.player);
        this.window = new Window(panel);
        initializePlatform();
    }

    public void update() {
        player.updateGame();
    }

    private void initializePlatform() {
        panel.requestFocus();
        startGameLoop();
    }

    private void startGameLoop() {
        double framePeriod = 1000000000.0 / FPS;
        double updatePeriod = 1000000000.0 / UPS_SET;

        final long[] previousTime = {System.nanoTime()};

        final int[] frames = {0};
        final int[] updates = {0};
        final long[] lastCheck = {System.currentTimeMillis()};

        final double[] deltaU = {0};
        final double[] deltaF = {0};

        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            executor.submit(() -> {
                while (true) {
                    long currentTime = System.nanoTime();

                    deltaU[0] += (currentTime - previousTime[0]) / framePeriod;
                    deltaF[0] += (currentTime - previousTime[0]) / updatePeriod;
                    previousTime[0] = currentTime;

                    if (deltaU[0] >= 1) {
                        update();
                        updates[0]++;
                        deltaU[0]--;
                    }

                    if (deltaF[0] >= 1) {
                        panel.repaint();
                        frames[0]++;
                        deltaF[0]--;
                    }

                    if (System.currentTimeMillis() - lastCheck[0] >= 1000) {
                        lastCheck[0] = System.currentTimeMillis();
                        log.info("FPS: {} | UPS: {}", frames[0], updates[0]);
                        frames[0] = 0;
                        updates[0] = 0;

                    }
                }
            });

        }
    }
}