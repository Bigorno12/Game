package mu.bigorno.inputs;

import lombok.extern.slf4j.Slf4j;
import mu.bigorno.game.Panel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static java.awt.event.KeyEvent.VK_A;
import static java.awt.event.KeyEvent.VK_D;
import static java.awt.event.KeyEvent.VK_S;
import static java.awt.event.KeyEvent.VK_W;

@Slf4j
public record Keyboard(Panel panel) implements KeyListener {

    @Override
    public void keyTyped(KeyEvent e) {
        log.info("Key typed: {}", e.getKeyChar());
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case VK_W -> panel.getPlayer().setUp(true);
            case VK_A -> panel.getPlayer().setLeft(true);
            case VK_S -> panel.getPlayer().setDown(true);
            case VK_D -> panel.getPlayer().setRight(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case VK_W -> panel.getPlayer().setUp(false);
            case VK_A -> panel.getPlayer().setLeft(false);
            case VK_S -> panel.getPlayer().setDown(false);
            case VK_D -> panel.getPlayer().setRight(false);
        }
    }
}
