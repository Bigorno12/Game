package mu.bigorno.game;

public record Platform(Window window, Panel panel) implements Runnable {

    private static final int FPS = 120;

    public Platform {
        panel.requestFocus();
        startGameLoop();
    }

    public Platform() {
        this(new Window(new Panel()), new Panel());
    }

    private void startGameLoop() {
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        double timePerFrame = 1000000000.0 / FPS;
        long lastFrame = System.nanoTime();
        long now = System.nanoTime();

        int frames = 0;
        long lastCheck = System.currentTimeMillis();

        while (true) {

            now = System.nanoTime();
            if (now - lastFrame >= timePerFrame) {
                panel.repaint();
                lastFrame = now;
                frames++;
            }

            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
    }
}