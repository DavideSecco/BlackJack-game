package Panels;

import javax.swing.*;

import java.io.IOException;

import static Main.TestApp.*;

public class MainFrame extends JFrame {
    public static GamePanel gamePanel;
    public MainFrame() throws IOException {
        JFrame frame = new JFrame("Pong");

        gamePanel = new GamePanel(player, dealer, cardsDeck);
        frame.setContentPane(gamePanel);

        frame.setSize(dimension.width, dimension.height);   // nota che penso comprenda anche la barra di sistema: da me toglie 40px
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setVisible(true);
    }
}
