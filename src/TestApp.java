import Panels.GamePanel;

import javax.swing.*;
import java.io.IOException;

public class TestApp {
    public static void main(String[] args) throws IOException {
        JFrame frame = new JFrame("Pong");

        GamePanel gamePanel = new GamePanel();
        frame.setContentPane(gamePanel);

        frame.setSize(1280, 800);   // nota che penso comprenda anche la barra di sistema: da me toglie 40px
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setVisible(true);
        gamePanel.init();
    }
}
