package mu.bigorno.inputs;

import mu.bigorno.game.Panel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static java.awt.event.KeyEvent.*;
import static mu.bigorno.utils.Directions.DOWN;
import static mu.bigorno.utils.Directions.LEFT;
import static mu.bigorno.utils.Directions.RIGHT;
import static mu.bigorno.utils.Directions.UP;

public record Keyboard(Panel panel) implements KeyListener {

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case VK_W -> panel.setDirection(UP);
            case VK_A -> panel.setDirection(LEFT);
            case VK_S -> panel.setDirection(DOWN);
            case VK_D -> panel.setDirection(RIGHT);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case VK_W, VK_A, VK_S , VK_D -> panel.setMoving(false);
        }
    }
}
