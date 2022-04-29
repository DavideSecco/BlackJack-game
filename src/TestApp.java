import Panels.GamePanel;

import javax.swing.*;
import java.io.IOException;

public class TestApp {
    public static void main(String[] args) throws IOException {
        JFrame frame = new JFrame("Pong");

        GamePanel gamePanel = new GamePanel();
        frame.setContentPane(gamePanel);
        // frame.setSize(508, 339);
        frame.setSize(1280, 720);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
        gamePanel.init();
    }
}
