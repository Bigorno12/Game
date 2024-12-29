package mu.bigorno.game;

import lombok.extern.slf4j.Slf4j;

import javax.swing.*;

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
    }

    public Window(){
        this(new Panel());
    }
}
