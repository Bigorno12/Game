package mu.bigorno.game;

import lombok.extern.slf4j.Slf4j;

import javax.swing.*;

import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

@Slf4j
public record Window(JFrame jFrame) {


    public Window {
        jFrame = new JFrame();
        jFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.setResizable(false);
        jFrame.setVisible(true);
    }

    public Window(Panel panel) {
        this(new JFrame());
        jFrame.add(panel);
        jFrame.pack();
        jFrame.addWindowFocusListener(new WindowFocusListener() {
            @Override
            public void windowLostFocus(WindowEvent e) {
                panel.getPlayer().resetDirection();
            }

            @Override
            public void windowGainedFocus(WindowEvent e) {
                log.info("Window gained focus");
            }
        });
    }
}
