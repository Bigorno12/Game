package mu.bigorno.inputs;

import mu.bigorno.game.Panel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static java.awt.event.KeyEvent.*;

public record Keyboard(Panel panel) implements KeyListener {

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case VK_W -> panel.changeYDelta(-5);
            case VK_A -> panel.changeXDelta(-5);
            case VK_S -> panel.changeYDelta(5);
            case VK_D -> panel.changeXDelta(5);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
